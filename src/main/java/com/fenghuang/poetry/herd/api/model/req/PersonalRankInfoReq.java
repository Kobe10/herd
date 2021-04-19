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
 * @Desc 参赛码请求实体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "个人成绩排行榜", description = "个人成绩入参`")
public class PersonalRankInfoReq {
    @ApiModelProperty(notes = "参赛码", value = "sdx-asdfw1234", required = true)
    @NotBlank(message = "参赛码不能为空")
    private String competitionCode;

    @ApiModelProperty(notes = "排行榜类型", value = "all 包含全省和个人的        area：地区个人排行榜", required = true)
    @NotBlank(message = "排行榜类型不能为空")
    private String rankType;

//    @ApiModelProperty(notes = "地区编码", value = "如果只是个人成绩单，不传；  全省传  浙江省编码         地区传  地区编码", required = false)
//    private String rankCode;
}
