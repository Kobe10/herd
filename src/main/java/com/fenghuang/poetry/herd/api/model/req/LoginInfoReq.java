package com.fenghuang.poetry.herd.api.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

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
 * @Desc 登录请求实体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录入参", description = "登录入参`")
public class LoginInfoReq {
    @ApiModelProperty(notes = "海选赛参赛码 (海选赛必须)", value = "sdx-asdfw1234", required = true)
    @NotBlank(message = "参赛码为空")
    private String competitionCode;

    @ApiModelProperty(notes = "手机号 (除海选赛之外的其他阶段必须)", value = "17611221957", required = true)
    @NotBlank(message = "手机号不能为空")
    private String phone;


    @ApiModelProperty(notes = "参赛者姓名  (除海选赛之外的其他阶段必须)", value = "付xx", required = true)
    @NotBlank(message = "参赛者姓名不能为空")
    private String userName;
}
