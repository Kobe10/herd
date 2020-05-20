package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.SceneEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneEntity record);

    int insertSelective(SceneEntity record);

    SceneEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneEntity record);

    int updateByPrimaryKey(SceneEntity record);
}