package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.QuestionOptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuestionOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionOptionEntity record);

    int insertSelective(QuestionOptionEntity record);

    QuestionOptionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionOptionEntity record);

    int updateByPrimaryKey(QuestionOptionEntity record);

    List<QuestionOptionEntity> selectByQuestionCode(@Param("questionCode") String questionCode);

    List<QuestionOptionEntity> selectOptionByCodeList(@Param("questionCodeList") List<String> questionCodeList);

    /**
     * 查询
     * @param questionCodeList
     * @return
     */
    List<QuestionOptionEntity> selectByQuestionCodeBatch(@Param("questionCodeList") List<String> questionCodeList);
}