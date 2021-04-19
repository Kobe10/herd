package com.fenghuang.poetry.herd.api.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value = "用户详细信息")
public class LoginUserInfoVo implements Serializable {

    private static final long serialVersionUID = 5884720811382830075L;

    @ApiModelProperty(notes = "用户uid")
    private String uid;

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户电话")
    private String phone;

    @ApiModelProperty(notes = "微信号")
    private String wxNum;

    @ApiModelProperty(notes = "赛区编码")
    private String areaCode;

    @ApiModelProperty(notes = "学校")
    private String school;

    @ApiModelProperty(notes = "年级")
    private String grade;
}
