package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.api.model.req.question.AnswerInfoReq;
import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.question.AnswerDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.OptionDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.QuestionAnswerDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.SubmitAnswerVo;
import com.fenghuang.poetry.herd.common.enums.DifficultTypeEnum;
import com.fenghuang.poetry.herd.common.enums.QuestionTypeEnum;
import com.fenghuang.poetry.herd.common.enums.RandomStrategyEnum;
import com.fenghuang.poetry.herd.common.util.DateTimeUtils;
import com.fenghuang.poetry.herd.common.util.DateUtil;
import com.fenghuang.poetry.herd.common.util.RandomUtil;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.config.exception.BusinessException;
import com.fenghuang.poetry.herd.dao.*;
import com.fenghuang.poetry.herd.dao.entity.*;
import com.fenghuang.poetry.herd.service.provider.AnswerService;
import com.fenghuang.poetry.herd.service.provider.QuestionOptionService;
import com.fenghuang.poetry.herd.service.provider.QuestionService;
import com.fenghuang.poetry.herd.service.provider.SceneConfigService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
 * @date Created in 2020年07月07日 20:20
 * @since 1.0
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Autowired
    private SceneConfigService sceneConfigService;

    @Resource
    private PageOrderMapper pageOrderMapper;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionOptionService questionOptionService;

    /**
     * 获取问题
     *
     * @param randomQuestionReq 参赛码获取问题
     * @return
     */
    @Override
    public SceneConfigEntity getSceneConfig(RandomQuestionReq randomQuestionReq) {
        CompetitionEntity competitionEntity = competitionMapper.selectByCompetitionCode(randomQuestionReq.getCompetitionCode());
        if (Objects.isNull(competitionEntity)) {
            return null;
        }

        String sceneCode = competitionEntity.getSceneCode();
        if (StringUtils.isBlank(sceneCode)) {
            throw new BusinessException("场景编码不存在");
        }
        // 查询当前场景的激活码使用配置次数
        SceneConfigEntity sceneConfig = sceneConfigService.findConfigBySceneCode(sceneCode);
        if (Objects.isNull(sceneConfig)) {
            throw new BusinessException("系统异常");
        }
        //判断当前答题次数是否满足场景配置要求   是否大于当前场景配置
        if (competitionEntity.getUseTimes() > sceneConfig.getAnswerTimes()) {
            throw new BusinessException("您的答题次数已达上限!");
        }
        return sceneConfig;
    }

    /**
     * 获取问题  通过随机策略和题库编码
     *
     * @param strategyCode     随机策略
     * @param questionBankCode 题库编码
     * @return
     */
    @Override
    public List<QuestionEntity> getQuestionByStrategy(String strategyCode, String questionBankCode) {
        //随机策略 todo  写死吧（或者用策略模式）
        List<QuestionEntity> allQuestionEntityList = Lists.newArrayList();
        List<QuestionEntity> easyQuestionEntityList = Lists.newArrayList();
        List<QuestionEntity> difficultQuestionEntityList = Lists.newArrayList();
        if (StringUtils.equals(strategyCode, RandomStrategyEnum.HX_STRATEGY.getCode())) {
            // 初级赛事随机策略  随机20道题目  从海选赛的题库  90%容易， 10%困难
            // 容易题目  easy
//            List<Integer> easyQuestionIdList = RandomUtil.randomArray(0, 720, 18);
//            easyQuestionEntityList = questionOptionService.selectQuestionByIdList(easyQuestionIdList);
//            List<Integer> difficultQuestionIdList = RandomUtil.randomArray(721, 800, 2);
//            difficultQuestionEntityList = questionOptionService.selectQuestionByIdList(difficultQuestionIdList);
            while (easyQuestionEntityList.size() != 18) {
                easyQuestionEntityList = questionMapper.getQuestionByStrategy(questionBankCode, DifficultTypeEnum.EASY.getCode(), 18);
            }

            while (difficultQuestionEntityList.size() != 2) {
                Integer temp1 = new Random().nextInt(800 - 760) + 761;
                Integer temp2 = new Random().nextInt(760 - 720) + 721;
                List<Integer> difficultQuestionIdList = Lists.newArrayList();
                difficultQuestionIdList.add(temp1);
                difficultQuestionIdList.add(temp2);
//                List<Integer> difficultQuestionIdList = RandomUtil.randomArray(721, 800, 2);
                difficultQuestionEntityList = questionOptionService.selectQuestionByIdList(difficultQuestionIdList);
//                difficultQuestionEntityList = questionMapper.getQuestionByStrategy(questionBankCode, DifficultTypeEnum.DIFFICULT.getCode(), 2);
            }
        }
        if (StringUtils.equals(strategyCode, RandomStrategyEnum.Default.getCode())) {
            // 默认策略， 直接查询20道题目即可
            // 容易题目  easy
            allQuestionEntityList = questionMapper.getQuestionByStrategy(questionBankCode, DifficultTypeEnum.EASY.getCode(), 20);
        }
        if (StringUtils.equals(strategyCode, RandomStrategyEnum.FS_ALL.getCode())) {
            allQuestionEntityList = questionMapper.getQuestionByStrategyAll(questionBankCode);
        }
        if (CollectionUtils.isNotEmpty(easyQuestionEntityList)) {
            allQuestionEntityList.addAll(easyQuestionEntityList);
        }
        if (CollectionUtils.isNotEmpty(difficultQuestionEntityList)) {
            allQuestionEntityList.addAll(difficultQuestionEntityList);
        }
        return allQuestionEntityList;
    }

    @Override
    public List<OptionDetailVo> getQuestionOption(String questionCode) {
        // 查询当前问题的选项
        List<OptionDetailVo> optionDetailVoList = Lists.newArrayList();
        List<QuestionOptionEntity> questionOptionEntityList = questionOptionMapper.selectByQuestionCode(questionCode);
        if (CollectionUtils.isNotEmpty(questionOptionEntityList)) {
            optionDetailVoList = questionOptionEntityList.stream().map(questionOptionEntity -> OptionDetailVo.builder()
                    .letter(questionOptionEntity.getLetter())
                    .optionId(questionOptionEntity.getOptionCode())
                    .optionOrderValue(questionOptionEntity.getOrderValue())
                    .optionTitle(questionOptionEntity.getOptionTitle())
                    .optionDesc(questionOptionEntity.getOptionDesc())
                    .optionType(null)
                    .build()).collect(Collectors.toList());
            //选项根据顺序排序
            optionDetailVoList = optionDetailVoList.stream().sorted(Comparator.comparing(OptionDetailVo::getOptionOrderValue))
                    .collect(Collectors.toList());

        }
        return optionDetailVoList;
    }

    /**
     * 批量查询问题
     *
     * @param questionCodeList 问题编码
     */
    @Override
    public List<QuestionAnswerDetailVo> selectQuestionByQuestionCodeList(List<String> questionCodeList) {
        List<QuestionAnswerDetailVo> questionAnswerDetailVoList = Lists.newArrayList();
        Map<String, List<QuestionOptionEntity>> questionOptionMaps = Maps.newConcurrentMap();
        //查询所有的问题
        List<QuestionEntity> questionEntityList = questionOptionService.selectQuestionByCodeList(questionCodeList);
        //查询所有的问题对应的选项
        List<QuestionOptionEntity> questionOptionEntityList = questionOptionService.getQuestionOptionBatch(questionCodeList);

        //遍历所有的选项list  抓换成map  questionCode为key  list为当前问题底下的所有选项
        if (CollectionUtils.isNotEmpty(questionOptionEntityList)) {
            questionOptionMaps = questionOptionEntityList.stream()
                    .collect(Collectors.groupingBy(QuestionOptionEntity::getQuestionCode));
        }
        // 获取问题详情
        if (CollectionUtils.isNotEmpty(questionEntityList)) {
            questionEntityList = questionEntityList.stream().sorted(Comparator.comparing(QuestionEntity::getOrderValue)).collect(Collectors.toList());

            AtomicInteger atomicInteger = new AtomicInteger(0);
            //遍历问题
            Map<String, List<QuestionOptionEntity>> finalQuestionOptionMaps = questionOptionMaps;
            questionAnswerDetailVoList = questionEntityList.stream().map(questionEntity -> {
                List<OptionDetailVo> optionDetailVoList = Lists.newArrayList();
                if (finalQuestionOptionMaps.containsKey(questionEntity.getQuestionCode())) {
                    List<QuestionOptionEntity> questionOptionEntities = finalQuestionOptionMaps.get(questionEntity.getQuestionCode());
                    optionDetailVoList = questionOptionEntities.stream().map(questionOptionEntity -> OptionDetailVo.builder()
                            .optionTitle(questionOptionEntity.getOptionTitle())
                            .letter(questionOptionEntity.getLetter())
                            .optionDesc(questionOptionEntity.getOptionDesc())
                            .optionId(questionOptionEntity.getOptionCode())
                            .optionOrderValue(questionOptionEntity.getOrderValue())
                            .isSelected(0)
                            .build()).collect(Collectors.toList());
                }
                atomicInteger.incrementAndGet();
                //遍历选项
                return QuestionAnswerDetailVo.builder()
                        .isAnswer(0)
                        .logicOrderValue(atomicInteger.get())
                        .difficult(questionEntity.getDifficult())
                        .orderValue(questionEntity.getOrderValue())
                        .questionCode(questionEntity.getQuestionCode())
                        .questionTitle(questionEntity.getQuestionTitle())
                        .questionType(questionEntity.getQuestionType())
                        .rightAnswer(questionEntity.getAnswer())
                        .optionCount(optionDetailVoList.size())
                        .optionDetailVoList(optionDetailVoList)
                        .build();
            }).collect(Collectors.toList());
        }
        return questionAnswerDetailVoList;
    }

    /**
     * 保存答题
     *
     * @param submitAnswerReq
     * @param questionAnswerDetailVoList
     * @param submitAnswerTime
     * @param times
     * @return
     */
    @Override
    public SubmitAnswerVo savePageAnswer(SubmitAnswerReq submitAnswerReq, List<QuestionAnswerDetailVo> questionAnswerDetailVoList,
                                         LocalDateTime submitAnswerTime, Integer times) {
        //计算答题数量、答题所花时间
        AtomicInteger atomicInteger = new AtomicInteger(0);

        List<AnswerInfoReq> answerInfoReqList = submitAnswerReq.getAnswerInfoReqList();
        Map<String, AnswerInfoReq> answerInfoReqMap = answerInfoReqList.stream().filter(Objects::nonNull).collect(
                Collectors.toMap(AnswerInfoReq::getQuestionCode, a -> a, (k1, k2) -> k1));

        // 入库答案的list
        List<AnswerEntity> answerEntityList = Lists.newArrayList();
//        List<QuestionAnswerDetailVo> errorQuestionAnswerDetailList = Lists.newArrayList();
        questionAnswerDetailVoList.forEach(questionAnswerDetailVo -> {
            if (answerInfoReqMap.containsKey(questionAnswerDetailVo.getQuestionCode())) {
                AnswerInfoReq answerInfoReq = answerInfoReqMap.get(questionAnswerDetailVo.getQuestionCode());
                questionAnswerDetailVo.setUserAnswer(answerInfoReq.getUserAnswer());

                // 设置答案实体
                AnswerEntity answerEntity = new AnswerEntity();
                answerEntity.setPageCode(submitAnswerReq.getPageOrderCode());
                answerEntity.setOrderValue(questionAnswerDetailVo.getLogicOrderValue());
                answerEntity.setQuestionId(questionAnswerDetailVo.getQuestionCode());
                answerEntity.setUserAnswer(answerInfoReq.getUserAnswer());
                answerEntity.setIsDel(0);

                //答案匹配
                if (StringUtils.equals(QuestionTypeEnum.RADIO.getCode(), questionAnswerDetailVo.getQuestionType())) {
                    //选择题
                    List<OptionDetailVo> optionDetailVoList = questionAnswerDetailVo.getOptionDetailVoList();
                    if (CollectionUtils.isNotEmpty(optionDetailVoList)) {
                        optionDetailVoList.forEach(optionDetailVo -> {
                            if (StringUtils.equals(optionDetailVo.getOptionId(), answerInfoReq.getOptionCode())) {
                                optionDetailVo.setIsSelected(1);
                                answerEntity.setOptionId(optionDetailVo.getOptionId());
                            }
                        });
                    }
                } else {
                    //填空题
                    questionAnswerDetailVo.setUserAnswer(answerInfoReq.getUserAnswer());
                }
                //计算是否答题正确
                if (StringUtils.equals(questionAnswerDetailVo.getRightAnswer(), answerInfoReq.getUserAnswer())) {
                    atomicInteger.incrementAndGet();
                    questionAnswerDetailVo.setRight(1);
                    answerEntity.setAnswerFlag(1);
                } else {
                    questionAnswerDetailVo.setRight(0);
                    answerEntity.setAnswerFlag(0);
//                    errorQuestionAnswerDetailList.add(questionAnswerDetailVo);
                }

                answerEntityList.add(answerEntity);
            } else {
                //用户当前题目未作答
                questionAnswerDetailVo.setUserAnswer(null);
//                errorQuestionAnswerDetailList.add(questionAnswerDetailVo);
            }
        });
        //计算答题时间
        PageOrderEntity pageOrderEntity = pageOrderMapper.selectByPageOrderCode(submitAnswerReq.getPageOrderCode());
        LocalDateTime startTime = DateTimeUtils.UDateToLocalDateTime(pageOrderEntity.getStartTime());
        // 答题花费的时间  秒
        int userUseTime = Math.toIntExact(ChronoUnit.SECONDS.between(startTime, submitAnswerTime));
        //入库  答案入库，排行榜入库
        answerService.handlerAnswer(submitAnswerReq.getPageOrderCode(), atomicInteger, userUseTime, answerEntityList,
                submitAnswerTime, pageOrderEntity, times);

        //存储答题
        AnswerDetailVo answerDetailVo = AnswerDetailVo.builder()
                .rightQuestionNum(atomicInteger.get())
                .userTime(DateUtil.secToTimeChinese(userUseTime))
                .build();
        return SubmitAnswerVo.builder()
                .answerDetailVo(answerDetailVo)
                .areaCode(pageOrderEntity.getAreaCode())
//                .questionAnswerDetailVos(errorQuestionAnswerDetailList)
                .build();
    }

    /**
     * 查询所有问题的选项
     *
     * @param questionCodeList 问题编码
     * @return
     */

    /**
     * 获取问题选项 map
     *
     * @param questionCodeList 问题编码List
     * @return
     */
    @Override
    public Map<String, List<OptionDetailVo>> getQuestionOptionMap(List<String> questionCodeList) {
        Map<String, List<OptionDetailVo>> groupByOptionId = Maps.newConcurrentMap();
        List<QuestionOptionEntity> questionOptionEntityList = questionOptionService.getQuestionOptionBatch(questionCodeList);

        if (CollectionUtils.isNotEmpty(questionOptionEntityList)) {
            groupByOptionId = questionOptionEntityList.stream().map(questionOptionEntity -> {
                        // map集合
                        return OptionDetailVo.builder()
                                .questionId(questionOptionEntity.getQuestionCode())
                                .letter(questionOptionEntity.getLetter())
                                .optionId(questionOptionEntity.getOptionCode())
                                .optionOrderValue(questionOptionEntity.getOrderValue())
                                .optionTitle(questionOptionEntity.getOptionTitle())
                                .optionDesc(questionOptionEntity.getOptionDesc())
                                .optionType(null)
                                .build();
                    }
            ).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(OptionDetailVo::getQuestionId));
        }
        return groupByOptionId;
    }
}
