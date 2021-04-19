package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.api.model.req.question.RandomQuestionReq;
import com.fenghuang.poetry.herd.api.model.req.question.SubmitAnswerReq;
import com.fenghuang.poetry.herd.api.model.resp.question.OptionDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.QuestionAnswerDetailVo;
import com.fenghuang.poetry.herd.api.model.resp.question.SubmitAnswerVo;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
 * @Desc 问题服务接口
 * @date Created in 2020年07月07日 20:20
 * @since 1.0
 */
public interface QuestionService {
    /**
     * 通过参赛码获取场景配置
     *
     * @param randomQuestionReq 参赛码获取问题
     * @return
     */
    SceneConfigEntity getSceneConfig(RandomQuestionReq randomQuestionReq);

    /**
     * 获取问题  通过随机策略和题库编码
     *
     * @param strategyCode     随机策略
     * @param questionBankCode 题库编码
     * @return
     */
    @Cacheable(value = "QuestionStrategy", key = "#p0 + '_' + #p1", unless = "#result==null")
    List<QuestionEntity> getQuestionByStrategy(String strategyCode, String questionBankCode);

    /**
     * 查询选项
     *
     * @param questionCode 问题编码
     * @return
     */
    List<OptionDetailVo> getQuestionOption(String questionCode);

    /**
     * 批量查询问题
     *
     * @param questionCodeList 问题编码
     */
    List<QuestionAnswerDetailVo> selectQuestionByQuestionCodeList(List<String> questionCodeList);

    /**
     * 保存答题
     *
     * @param submitAnswerReq
     * @param questionAnswerDetailVoList
     * @param submitAnswerTime
     * @param times
     * @return
     */
    @Transactional
    SubmitAnswerVo savePageAnswer(SubmitAnswerReq submitAnswerReq, List<QuestionAnswerDetailVo> questionAnswerDetailVoList, LocalDateTime submitAnswerTime, Integer times);


    /**
     * 获取问题选项 map
     *
     * @param questionCodeList 问题编码List
     * @return
     */
    Map<String, List<OptionDetailVo>> getQuestionOptionMap(List<String> questionCodeList);
}
