package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.AreaEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AreaEntity record);

    int insertSelective(AreaEntity record);

    AreaEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AreaEntity record);

    int updateByPrimaryKey(AreaEntity record);
}