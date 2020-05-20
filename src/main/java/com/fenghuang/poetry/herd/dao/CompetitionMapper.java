package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.CompetitionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface CompetitionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompetitionEntity record);

    int insertSelective(CompetitionEntity record);

    CompetitionEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompetitionEntity record);

    int updateByPrimaryKey(CompetitionEntity record);

    /**
     * 查询报名码
     *
     * @param competitionCode 报名码
     * @return
     */
    String findByCompetitionCode(@Param("competitionCode") String competitionCode);

    /**
     * 查询参赛码
     *
     * @param uid       用户uid
     * @param sceneCode 场景编码
     * @return
     */
    CompetitionEntity findByUidAndSceneCode(@Param("uid") String uid, @Param("sceneCode") String sceneCode);
}