package com.fenghuang.poetry.herd.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * user_stage
 * @author 
 */
public class UserStageEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 比赛阶段编码
     */
    private String stageCode;

    /**
     * 比赛阶段顺序
     */
    private Integer stageIndex;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 逻辑删除  0：未删除  1：删除
     */
    private Integer isDel;

    private String createId;

    private String lastModifyId;

    private Date createTime;

    private Date lastModifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public Integer getStageIndex() {
        return stageIndex;
    }

    public void setStageIndex(Integer stageIndex) {
        this.stageIndex = stageIndex;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getLastModifyId() {
        return lastModifyId;
    }

    public void setLastModifyId(String lastModifyId) {
        this.lastModifyId = lastModifyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}