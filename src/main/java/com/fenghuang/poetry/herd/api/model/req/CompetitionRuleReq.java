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
@ApiModel(value = "获取参赛规则入参", description = "获取参赛规则入参`")
public class CompetitionRuleReq {
    @ApiModelProperty(notes = "场景编码", value = "HX", required = true)
    @NotBlank(message = "场景编码为空")
    private String sceneCode;

}
