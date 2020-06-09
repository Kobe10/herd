package com.fenghuang.poetry.herd.service.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author fuzq
 * @version 1.0
 * @Desc
 * @date Created in 2020年05月12日 13:05
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDto implements Serializable {
    private static final long serialVersionUID = -5352103256402741350L;

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
}
