package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.ProvinceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProvinceEntity record);

    int insertSelective(ProvinceEntity record);

    ProvinceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProvinceEntity record);

    int updateByPrimaryKey(ProvinceEntity record);
}