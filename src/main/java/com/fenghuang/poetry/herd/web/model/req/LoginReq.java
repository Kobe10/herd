package com.fenghuang.poetry.herd.web.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

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
 * @Desc 后台登录入参
 * @date Created in 2020年05月10日 18:15
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "后台登录", description = "后台登录")
public class LoginReq {
    @ApiModelProperty(notes = "用户名", value = "admin", required = true)
    @NotNull(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(notes = "密码", value = "root", required = true)
    @NotNull(message = "密码不能为空")
    private String userPassword;
}
