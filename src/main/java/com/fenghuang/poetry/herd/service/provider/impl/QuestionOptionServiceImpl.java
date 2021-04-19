package com.fenghuang.poetry.herd.service.provider.impl;

import com.fenghuang.poetry.herd.api.model.resp.question.OptionDetailVo;
import com.fenghuang.poetry.herd.dao.QuestionMapper;
import com.fenghuang.poetry.herd.dao.QuestionOptionMapper;
import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import com.fenghuang.poetry.herd.dao.entity.QuestionOptionEntity;
import com.fenghuang.poetry.herd.service.provider.QuestionOptionService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
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
 * @date Created in 2020年07月18日 18:55
 * @since 1.0
 */
@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    @Resource
    private QuestionOptionMapper questionOptionMapper;

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public List<QuestionOptionEntity> getQuestionOptionBatch(List<String> questionCodeList) {
        // 查询当前问题的选项
        return questionOptionMapper.selectOptionByCodeList(questionCodeList);
    }

    @Override
    public List<QuestionEntity> selectQuestionByCodeList(List<String> questionCodeList) {
        return questionMapper.selectQuestionByCodeList(questionCodeList);
    }

    @Override
    public List<QuestionEntity> selectQuestionByIdList(List<Integer> questionCodeList) {
        return questionMapper.getQuestionById(questionCodeList);
    }
}
