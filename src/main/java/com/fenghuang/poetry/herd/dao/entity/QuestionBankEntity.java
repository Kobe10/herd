package com.fenghuang.poetry.herd.dao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * question_bank
 * @author 
 */
public class QuestionBankEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 题库编码
     */
    private String bankCode;

    /**
     * 题库名称
     */
    private String bankName;

    /**
     * 题库类型
     */
    private String bankType;

    /**
     * 题库描述
     */
    private String bankDesc;

    /**
     * 题库状态
     */
    private String publishStatus;

    /**
     * 题库数量
     */
    private Integer questionCount;

    /**
     * 逻辑删除 0 未删除  1  删除
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankDesc() {
        return bankDesc;
    }

    public void setBankDesc(String bankDesc) {
        this.bankDesc = bankDesc;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
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