package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.QuestionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Mapper
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionEntity record);

    int insertSelective(QuestionEntity record);

    QuestionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionEntity record);

    int updateByPrimaryKey(QuestionEntity record);

    /**
     * 通过策略获取问题
     *
     * @param questionBankCode
     * @param difficult
     * @param pageSize
     * @return
     */
    List<QuestionEntity> getQuestionByStrategy(@Param("questionBankCode") String questionBankCode, @Param("difficult")
            String difficult, @Param("pageSize") int pageSize);

    /**
     * 批量查询问题
     *
     * @param questionCodeList 问题编码
     * @return
     */
    List<QuestionEntity> selectQuestionByCodeList(@Param("questionCodeList") List<String> questionCodeList);

    /**
     * 查询所有问题列表
     *
     * @return
     */
    List<String> selectAll();

    List<QuestionEntity> getQuestionById(@Param("questionIdList") List<Integer> questionIdList);

    /**
     * 题库编码
     *
     * @param questionBankCode
     * @return
     */
    List<QuestionEntity> getQuestionByStrategyAll(@Param("questionBankCode") String questionBankCode);
}