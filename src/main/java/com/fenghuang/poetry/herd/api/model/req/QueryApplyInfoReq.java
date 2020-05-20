package com.fenghuang.poetry.herd.api.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
 * @Desc 报名信息请求实体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "查询参赛码入参", description = "查询参赛码入参")
public class QueryApplyInfoReq {
    @ApiModelProperty(notes = "用户姓名", value = "韦小宝", required = true)
    @NotBlank(message = "请填写姓名")
    private String userName;

    @ApiModelProperty(notes = "用户电话", value = "17611221957  限制位数 11位并且有效", required = true)
    @NotBlank(message = "请填写手机号")
    private String phone;
}
