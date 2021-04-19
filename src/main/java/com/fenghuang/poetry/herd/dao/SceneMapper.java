package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.SceneEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface SceneMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneEntity record);

    int insertSelective(SceneEntity record);

    SceneEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneEntity record);

    int updateByPrimaryKey(SceneEntity record);

    List<SceneEntity> selectAllList();
}