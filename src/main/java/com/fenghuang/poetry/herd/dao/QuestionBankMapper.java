package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.QuestionBankEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionBankEntity record);

    int insertSelective(QuestionBankEntity record);

    QuestionBankEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionBankEntity record);

    int updateByPrimaryKey(QuestionBankEntity record);
}