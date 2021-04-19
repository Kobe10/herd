package com.fenghuang.poetry.herd.api.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @Desc 用户登录返回信息
 * @date Created in 2020年05月10日 14:58
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "验证码登录返回信息")
public class LoginInfoVo implements Serializable {

    private static final long serialVersionUID = 849423459822000573L;
    @ApiModelProperty(notes = "是否验证过用户信息  0:未验证，跳转用户信息修改界面    1：已经验证   直接跳转答题规则页面即可", value = "0")
    private Integer isCheckUserInfo;

    @ApiModelProperty(notes = "参赛码")
    private String competitionCode;

    @ApiModelProperty(notes = "用户详细信息 ")
    private LoginUserInfoVo loginUserInfoVo;
}
