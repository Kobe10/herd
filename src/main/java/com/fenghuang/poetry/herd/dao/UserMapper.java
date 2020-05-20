package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.UserEntity;
import com.fenghuang.poetry.herd.service.model.dto.UserCountResultDto;
import com.fenghuang.poetry.herd.service.model.dto.UserInfoDto;
import com.fenghuang.poetry.herd.web.model.req.QueryUserReq;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    /**
     * 通过手机号和用户名查询用户
     *
     * @param userInfoDto
     * @return
     */
    UserEntity findUserByNameAndPhone(UserInfoDto userInfoDto);

    /**
     * 查询用户列表
     *
     * @param queryUserReq
     * @return
     */
    List<UserEntity> findUserListByCondition(QueryUserReq queryUserReq);

    /**
     * 按照areaCode 分组
     * @return
     */
    List<UserCountResultDto> countUser();

    int countAll();
}