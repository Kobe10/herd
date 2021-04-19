package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.SceneConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SceneConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneConfigEntity record);

    int insertSelective(SceneConfigEntity record);

    SceneConfigEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneConfigEntity record);

    int updateByPrimaryKey(SceneConfigEntity record);

    /**
     * 根据场景编码查询参数
     *
     * @param sceneCode 场景编码
     * @return
     */
    SceneConfigEntity selectBySceneCode(@Param("sceneCode") String sceneCode);
}