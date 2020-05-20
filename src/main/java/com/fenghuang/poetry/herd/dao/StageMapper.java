package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.StageEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StageEntity record);

    int insertSelective(StageEntity record);

    StageEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StageEntity record);

    int updateByPrimaryKey(StageEntity record);
}