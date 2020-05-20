package com.fenghuang.poetry.herd.dao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * competition
 *
 * @author
 */
@Data
public class CompetitionEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 报名码
     */
    private String competitionCode;

    /**
     * 使用次数
     */
    private String competitionStatus;

    /**
     * 场景展示策略
     */
    private Integer useTimes;

    /**
     * 逻辑删除  0：未删除  1：删除
     */
    private Integer isDel;

    private String createId;

    private String lastModifyId;

    private Date createTime;

    private Date lastModifyTime;

    private static final long serialVersionUID = 1L;
}