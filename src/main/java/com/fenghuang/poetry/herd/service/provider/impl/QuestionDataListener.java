package com.fenghuang.poetry.herd.service.provider.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fenghuang.poetry.herd.common.util.SpringContextUtil;
import com.fenghuang.poetry.herd.common.util.StringUtils;
import com.fenghuang.poetry.herd.dao.QuestionMapper;
import com.fenghuang.poetry.herd.dao.QuestionOptionMapper;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.QuestionOptionEntity;
import com.fenghuang.poetry.herd.service.provider.CompetitionService;
import com.google.common.collect.Maps;
import com.xuanner.seq.SnowflakeSeqBuilder;
import com.xuanner.seq.sequence.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 直接用map接收数据
 * 问卷星历史数据导入，数据处理 (这里可以抽离，可以采用策略模式)
 */
@Order(50)
@Slf4j
//@Component
public class QuestionDataListener extends AnalysisEventListener<Map<Integer, String>> {
    public static Sequence sequence = SnowflakeSeqBuilder.create().datacenterId(1).workerId(5).build();


//    private CompetitionService competitionService = (CompetitionService) SpringContextUtil.getBean("competitionService");

    private QuestionOptionMapper questionOptionMapper = (QuestionOptionMapper) SpringContextUtil.getBean("questionOptionMapper");

    private QuestionMapper questionMapper = (QuestionMapper) SpringContextUtil.getBean("questionMapper");

    AtomicInteger questionOrderValue = new AtomicInteger(0);

    /**
     * 表头数据
     */
    private Map<Integer, String> headMaps = Maps.newConcurrentMap();

    /**
     * 一条答案数据拼装
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {

        //1、判断题目类型  单选OR填空

        //2、答案数据处理
        try {
            this.handleQuestion(data);

            //插入问题
        } catch (Exception e) {
            log.error("[QuestionnaireAnswerDataListener-invoke-问卷数据导入-数据解析异常- 异常信息: ]", e);
        }
    }

    /**
     * 存储答卷数据
     *
     * @param data 每一题的数据
     * @return
     */
    private QuestionEntity handleQuestion(Map<Integer, String> data) {
        Date date = new Date();
        AtomicInteger optionOrderValue = new AtomicInteger(0);
        questionOrderValue.incrementAndGet();
        QuestionEntity questionEntity = new QuestionEntity();
        String bankCode = data.get(0);
        String questionTitle = data.get(2);
        String questionType = data.get(3);
        String difficult = data.get(8);
        String answer = data.get(9);
        String questionCode = this.getCode();
        Integer questionIndex = questionOrderValue.get();
        if (StringUtils.equals(questionType, "选择")) {
            //遍历选项
            for (int i = 4; i < 8; i++) {
                optionOrderValue.incrementAndGet();
                String optionTitle = data.get(i);
                String optionCode = this.getCode();
                QuestionOptionEntity questionOptionEntity = new QuestionOptionEntity();
                questionOptionEntity.setQuestionCode(questionCode);
                questionOptionEntity.setOptionTitle(optionTitle);
                questionOptionEntity.setOrderValue(optionOrderValue.get());
                questionOptionEntity.setOptionCode(optionCode);
                questionOptionEntity.setIsDel(0);
                questionOptionEntity.setCreateTime(date);

                questionOptionMapper.insertSelective(questionOptionEntity);
            }
        }
        questionEntity.setQuestionCode(questionCode);
        questionEntity.setDifficult(difficult);
        questionEntity.setOptionCount(optionOrderValue.get());
        questionEntity.setOrderValue(questionIndex);
        questionEntity.setAnswer(answer);
        questionEntity.setQuestionTitle(questionTitle);
        questionEntity.setQuestionType(questionType);
        questionEntity.setBankCode(bankCode);
        questionEntity.setIsDel(0);
        questionEntity.setCreateTime(date);
        questionMapper.insertSelective(questionEntity);
        return null;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        // 表头赋值
        headMaps = headMap;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        log.info("题库数据导入-importQuestion- 所有数据解析完成！");
    }


    public String getCode() {
        long uid = sequence.nextValue();
        return String.valueOf(uid);
    }
}
