package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnswerEntity record);

    int insertSelective(AnswerEntity record);

    AnswerEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnswerEntity record);

    int updateByPrimaryKey(AnswerEntity record);

    /**
     * 批量插入答案
     *
     * @param answerEntityList 答案list
     */
    void insertBatchAnswer(List<AnswerEntity> answerEntityList);
}