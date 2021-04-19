package com.fenghuang.poetry.herd.service.facade.impl;

import com.alibaba.fastjson.JSONObject;
import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.question.*;
import com.fenghuang.poetry.herd.common.enums.PageOrderStatusEnum;
import com.fenghuang.poetry.herd.common.enums.QuestionTypeEnum;
import com.fenghuang.poetry.herd.common.enums.RandomStrategyEnum;
import com.fenghuang.poetry.herd.common.util.DateUtil;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.common.web.Resp;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.dao.CompetitionMapper;
import com.fenghuang.poetry.herd.dao.PageOrderMapper;
import com.fenghuang.poetry.herd.dao.QuestionMapper;
import com.fenghuang.poetry.herd.dao.entity.CompetitionEntity;
import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import com.fenghuang.poetry.herd.service.facade.QuestionFacade;
import com.fenghuang.poetry.herd.service.provider.PageOrderService;
import com.fenghuang.poetry.herd.service.provider.QuestionService;
import com.fenghuang.poetry.herd.service.provider.SceneConfigService;
import com.google.common.base.Splitter;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author fuzq
 * @version 1.0
 * @Desc
 * @date Created in 2020年07月07日 20:41
 * @since 1.0
 */
@Slf4j
@Service
public class QuestionFacadeImpl implements QuestionFacade {

    /**
     * 使用guava 对字符串加锁
     */
    private static final Interner<String> competitionCodePool = Interners.newWeakInterner();


    @Autowired
    private QuestionService questionService;

    @Autowired
    private PageOrderService pageOrderService;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private SceneConfigService sceneConfigService;

    @Resource
    private PageOrderMapper pageOrderMapper;

    @Resource
    private QuestionMapper questionMapper;

    /**
     * 获取随机问题
     *
     * @param randomQuestionReq
     * @return
     */
    @Override
    public Resp getQuestion(RandomQuestionReq randomQuestionReq) {

        SceneConfigEntity sceneConfig = new SceneConfigEntity();
        // 获取当前激活码的场景配置
        try {
            sceneConfig = questionService.getSceneConfig(randomQuestionReq);
        } catch (Exception e) {
            log.error("获取考卷问题，场景未配置, 参赛码:{}", JSONObject.toJSONString(randomQuestionReq));
            return new Resp(500, e.getMessage());
        }

        // 获取随机策略、题库编码
        String questionBankCode = sceneConfig.getQuestionBankCode();
        String strategyCode = sceneConfig.getRandomStrategy();
        RandomStrategyEnum randomStrategyEnum = RandomStrategyEnum.getByCode(strategyCode);
        if (Objects.isNull(randomStrategyEnum)) {
            log.error("获取当前场景随机规则失败, 场景编码，随机码:{}, {}", sceneConfig.getSceneCode(), strategyCode);
            return new Resp(500, "服务器异常，请稍后再试");
        }
        // 找到随机规则编码,随机查询问题  通过mysql
        PageOrderEntity pageOrderEntity = pageOrderMapper.selectValidPageOrderByCompetitionCode(randomQuestionReq.getCompetitionCode());

        List<String> questionCodeList = Lists.newArrayList();
        List<QuestionEntity> questionEntities = Lists.newArrayList();
        String pageOrderCode = null;
        // 对同一个参赛码进行锁定， 同一时间只能有一个进行访问
        synchronized (competitionCodePool.intern(randomQuestionReq.getCompetitionCode())) {
            if (!Objects.isNull(pageOrderEntity)) {
                String questionCodeStr = pageOrderEntity.getPageQuestion();
                //去掉逗号，转list
                Splitter split = Splitter.on(',').trimResults().omitEmptyStrings(); // 去前后空格&&去空string
                questionCodeList = split.splitToList(questionCodeStr);
                if (CollectionUtils.isEmpty(questionCodeList)) {
                    return new Resp(500, "服务器异常，请稍后再试");
                }

                questionEntities = questionMapper.selectQuestionByCodeList(questionCodeList);

                pageOrderCode = pageOrderEntity.getPageCode();
            } else {
                long start = System.currentTimeMillis();
                questionEntities = questionService.getQuestionByStrategy(strategyCode, questionBankCode);
                log.info("查询问题花费时间: {}", System.currentTimeMillis() - start);
                if (Objects.isNull(questionEntities)) {
                    return new Resp(500, "服务器异常，请稍后再试");
                }
                // 获取问题的编码
                questionCodeList = questionEntities.stream().map(QuestionEntity::getQuestionCode).collect(Collectors.toList());
                //生成答卷单  判断当前答卷单是否存在、判断是否有未答的答卷单存在，如果有，作废之前的答卷单，重新生成一个答卷单
                try {
                    long start1 = System.currentTimeMillis();
                    pageOrderCode = pageOrderService.createPageOrder(questionCodeList, randomQuestionReq);
                    log.info("保存答卷花费时间: {}", System.currentTimeMillis() - start1);
                } catch (Exception e) {
                    return new Resp(500, e.getMessage());
                }
            }
        }

        // 数据结果转换出参  问题参数按照顺序传出
        CompetitionInfoVo competitionInfoVo = CompetitionInfoVo.builder()
                .competitionDuration(sceneConfig.getCompetingTime())
                .startAnswerTime(DateUtil.toStringYmdHmsWthHS(sceneConfig.getCompetingStartTime()))
                .endAnswerTime(DateUtil.toStringYmdHmsWthHS(sceneConfig.getCompetingEndTime()))
                .sceneCode(sceneConfig.getSceneCode())
                .build();

        // 批量查询选项 内存处理选项对应的问题
        long start1 = System.currentTimeMillis();
        AtomicInteger orderValue = new AtomicInteger(0);
        Map<String, List<OptionDetailVo>> questionOptionMap = questionService.getQuestionOptionMap(questionCodeList);
        List<QuestionDetailVo> questionDetailVoList = questionEntities.stream().map(questionEntity -> {
            List<OptionDetailVo> optionDetailVos = Lists.newArrayList();
            if (StringUtils.equals(questionEntity.getQuestionType(), QuestionTypeEnum.RADIO.getCode())) {
                if (questionOptionMap.containsKey(questionEntity.getQuestionCode())) {
                    optionDetailVos = questionOptionMap.get(questionEntity.getQuestionCode());
                }
            }
            orderValue.incrementAndGet();
            return QuestionDetailVo.builder()
                    .questionTitle(questionEntity.getQuestionTitle())
                    .questionType(questionEntity.getQuestionType())
                    .questionOrderValue(questionEntity.getOrderValue())
                    .logicOrderValue(orderValue.get())
                    .difficult(questionEntity.getDifficult())
                    .questionCode(questionEntity.getQuestionCode())
                    .optionCount(optionDetailVos.size())
                    .optionDetailVoList(optionDetailVos)
                    .build();
        }).collect(Collectors.toList()).stream().sorted(Comparator.comparing(QuestionDetailVo::getQuestionOrderValue))
                .collect(Collectors.toList());
        if (StringUtils.isBlank(pageOrderCode)) {
            return new Resp(500, "当前人数过多，请稍后再试");
        }
        log.info("问题格式转换花费时间: {}", System.currentTimeMillis() - start1);
        long start2 = System.currentTimeMillis();
        //更新消耗次数
        // 激活码使用次数
        CompetitionEntity competitionEntity = competitionMapper.selectByCompetitionCode(randomQuestionReq.getCompetitionCode());
        AtomicInteger userTimes = new AtomicInteger(competitionEntity.getUseTimes());
        //2、满足条件扣除一次答题机会(答题次数加1)
        userTimes.incrementAndGet();
        competitionEntity.setUseTimes(userTimes.get());
        competitionEntity.setLastModifyTime(new Date());
        competitionMapper.updateByPrimaryKeySelective(competitionEntity);

        log.info("更新答题数量花费时间: {}", System.currentTimeMillis() - start2);
        // 返回出参
        return new Resp(RandomQuestionVo.builder()
                .pageOrderCode(pageOrderCode)
                .questionIdVoList(questionDetailVoList)
                .competitionInfoVo(competitionInfoVo)
                .build());
    }

    @Override
    public Resp submitAnswerReq(SubmitAnswerReq submitAnswerReq) {
        LocalDateTime submitAnswerTime = LocalDateTime.now();

        // 校验答卷单的状态   1、是否存在  2、是否已经作答  3、是否无效  4、是否超过答卷单作答时间
        PageOrderEntity pageOrderEntity = pageOrderService.selectByPageOrderCode(submitAnswerReq.getPageOrderCode());
        if (Objects.isNull(pageOrderEntity)) {
            return new Resp(500, "无答卷资格，请重新登录作答！");
        }
        if (StringUtils.equals(pageOrderEntity.getPageStatus(), PageOrderStatusEnum.YJJ.getCode())) {
            return new Resp(500, "您已完成本次答题，无法修改答案!");
        }
        if (StringUtils.equals(pageOrderEntity.getPageStatus(), PageOrderStatusEnum.YQK.getCode())) {
            return new Resp(500, "您已完成本次答题，无法修改答案!");
        }


        //校验是否是第二次作答，  第二次作答取成绩最好那一次
        String competitionCode = pageOrderEntity.getCompetitionCode();
        CompetitionEntity competitionEntity = competitionMapper.selectByCompetitionCode(competitionCode);
        if (Objects.isNull(competitionEntity)) {
            return new Resp(500, "您无参赛资格，请报名后参加!");
        }

        // 查询当前场景的激活码使用配置次数
        SceneConfigEntity sceneConfig = sceneConfigService.findConfigBySceneCode(pageOrderEntity.getSceneCode());
        if (Objects.isNull(sceneConfig)) {
            log.warn("当前场景未配置,请配置: 场景编码:{}", pageOrderEntity.getSceneCode());
            throw new BusinessException("当前场景未配置,请配置: 场景编码:{}");
        }
        Integer sceneAnswerTimes = sceneConfig.getAnswerTimes();
        // 如果当前答题次数大于场景配置次数
        if (competitionEntity.getUseTimes() > sceneAnswerTimes + 1) {
            return new Resp(500, "您已达到本次比赛答题次数上限!");
        }
        // 激活码使用次数 -- 如果是两次，取最好成绩那一次   （次数等于3  代表第二次）
        Integer times = competitionEntity.getUseTimes();


        // 计算正确答题数， 遍历答案，存储(批量存储)
        //1、查询出所有题目
        String questionStr = pageOrderEntity.getPageQuestion();
        Splitter split = Splitter.on(',').trimResults().omitEmptyStrings(); // 去前后空格&&去空string
        List<String> questionCodeList = split.splitToList(questionStr);
        //1.2、查询出所有问题list
        if (CollectionUtils.isEmpty(questionCodeList)) {
            return new Resp(500, "系统异常，请联系客服！");
        }
        List<QuestionAnswerDetailVo> questionAnswerDetailVoList = questionService.selectQuestionByQuestionCodeList(questionCodeList);
        if (CollectionUtils.isEmpty(questionAnswerDetailVoList)) {
            return new Resp(500, "系统异常，请联系客服！");
        }

        //2.1、计算正确答题数，落库
        SubmitAnswerVo submitAnswerVo = questionService.savePageAnswer(submitAnswerReq, questionAnswerDetailVoList, submitAnswerTime, times);

        //2.2、数据存入redis进行统计
        return new Resp(submitAnswerVo);
    }
}
