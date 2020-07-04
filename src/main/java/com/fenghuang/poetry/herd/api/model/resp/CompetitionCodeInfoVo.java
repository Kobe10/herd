package com.fenghuang.poetry.herd.api.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @Desc 参赛码详细信息
 * @date Created in 2020年05月10日 15:00
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "参赛码详细信息")
public class CompetitionCodeInfoVo {

    @ApiModelProperty(notes = "参赛码", value = "xxxxxx", required = true)
    private String competitionCode;

    @ApiModelProperty(notes = "参赛码状态", value = "sxz: 生效中  ygq:已过期 yfh:已复活", required = true)
    private String competitionCodeStatus;
}
