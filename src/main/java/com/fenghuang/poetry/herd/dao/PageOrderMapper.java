package com.fenghuang.poetry.herd.dao;

import com.fenghuang.poetry.herd.dao.entity.PageOrderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PageOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PageOrderEntity record);

    int insertSelective(PageOrderEntity record);

    PageOrderEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PageOrderEntity record);

    int updateByPrimaryKey(PageOrderEntity record);

    /**
     * 查询答卷单
     *
     * @param pageOrderCode 答卷单号
     * @return
     */
    PageOrderEntity selectByPageOrderCode(@Param("pageOrderCode") String pageOrderCode);

    /**
     *
     * @param competitionCode
     * @return
     */
    List<PageOrderEntity> selectByCompetitionCode(@Param("competitionCode") String competitionCode);

    /**
     * 查询是否存在唯一的答卷单
     * @param competitionCode
     * @return
     */
    PageOrderEntity selectValidPageOrderByCompetitionCode(@Param("competitionCode") String competitionCode);

    /**
     *
     * @param competitionCode
     * @param sceneCode
     * @return
     */
    List<PageOrderEntity> selectByCompetitionCodeAndSceneCode(@Param("competitionCode") String competitionCode, @Param("sceneCode") String sceneCode);
}