package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.UserStageEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserStageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserStageEntity record);

    int insertSelective(UserStageEntity record);

    UserStageEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserStageEntity record);

    int updateByPrimaryKey(UserStageEntity record);
}