package com.fenghuang.poetry.herd.dao.entity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 *
 * @author
 */
@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 8617802944823543216L;


    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户uid
     */
    private String uid;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户手机号 -- 11位限制
     */
    private String phone;

    /**
     * 微信号
     */
    private String wxNum;

    /**
     * 省份编码
     */
    private String provinceCode;

    /**
     * 所属地区
     */
    private String areaCode;

    /**
     * 赛段编码
     */
    private String generaCode;

    /**
     * 学校
     */
    private String school;

    /**
     * 年级
     */
    private String grade;

    /**
     * 是否验证个人信息   0 未验证   1 已经验证
     */
    private Integer isVerify;

    /**
     * 逻辑删除 0 未删除  1  删除
     */
    private Integer isDel;

    private String createId;

    private String lastModifyId;

    private Date createTime;

    private Date lastModifyTime;

    /**
     * 用户用户token
     *
     * @param user
     * @return
     */
    public String getToken(UserEntity user) {
        String token = "";
        token = JWT.create()
                .withAudience(user.getUid())
                .withAudience(user.getGeneraCode())
                .sign(Algorithm.HMAC256(user.getGeneraCode()));
        return token;
    }
}