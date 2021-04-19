package com.fenghuang.poetry.herd.service.provider;

import com.fenghuang.poetry.herd.api.model.resp.question.OptionDetailVo;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.QuestionOptionEntity;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

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
 * @date Created in 2020年07月18日 18:54
 * @since 1.0
 */
public interface QuestionOptionService {
    /**
     * 通过问题编码查询库
     *
     * @param questionCodeList 问题编码集合
     * @return
     */
    @Cacheable(value = "Question_Option", key = "#p0", unless = "#result==null")
    List<QuestionOptionEntity> getQuestionOptionBatch(List<String> questionCodeList);

    @Cacheable(value = "Question", key = "#p0", unless = "#result==null")
    List<QuestionEntity> selectQuestionByCodeList(List<String> questionCodeList);

    @Cacheable(value = "Question_Id", key = "#p0", unless = "#result==null")
    List<QuestionEntity> selectQuestionByIdList(List<Integer> questionCodeList);
}
