package com.fenghuang.poetry.herd.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * page_order
 * @author 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageOrderEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 答卷单号
     */
    private String pageCode;

    /**
     * 答卷状态
     */
    private String pageStatus;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 学校
     */
    private String school;

    /**
     * 正确题目数量
     */
    private Integer rightSubjectNum;

    private Integer speedTime;

    /**
     * 参赛码
     */
    private String competitionCode;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 地区编码
     */
    private String areaCode;

    /**
     * 当前答卷单的随机的题目编号  question_id  逗号隔开
     */
    private String pageQuestion;

    /**
     * 答卷单开始时间
     */
    private Date startTime;

    /**
     * 答卷单截止时间
     */
    private Date endTime;

    /**
     * 用户开始答题时间
     */
    private Date createPageTime;

    /**
     * 提交答题时间
     */
    private Date submitPageTime;

    /**
     * 逻辑删除 0 未删除  1  删除
     */
    private Integer isDel;

    private String createId;

    private String lastModifyId;

    private Date createTime;

    private Date lastModifyTime;

    private static final long serialVersionUID = 1L;
}