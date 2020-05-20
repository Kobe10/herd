package com.fenghuang.poetry.herd.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * scene
 * @author 
 */
public class SceneEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 场景编码
     */
    private String sceneCode;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 场景展示名称
     */
    private String sceneShowName;

    /**
     * 场景展示策略
     */
    private String sceneShowStrategy;

    /**
     * 场景状态 0：禁用  1：启用
     */
    private Byte sceneStatus;

    /**
     * 是否有效 0：无效  1：有效
     */
    private Byte isValid;

    /**
     * 逻辑删除  0：未删除  1：删除
     */
    private Byte isDel;

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

    public String getSceneCode() {
        return sceneCode;
    }

    public void setSceneCode(String sceneCode) {
        this.sceneCode = sceneCode;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneShowName() {
        return sceneShowName;
    }

    public void setSceneShowName(String sceneShowName) {
        this.sceneShowName = sceneShowName;
    }

    public String getSceneShowStrategy() {
        return sceneShowStrategy;
    }

    public void setSceneShowStrategy(String sceneShowStrategy) {
        this.sceneShowStrategy = sceneShowStrategy;
    }

    public Byte getSceneStatus() {
        return sceneStatus;
    }

    public void setSceneStatus(Byte sceneStatus) {
        this.sceneStatus = sceneStatus;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
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