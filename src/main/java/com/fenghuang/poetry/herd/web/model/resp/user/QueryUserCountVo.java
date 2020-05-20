package com.fenghuang.poetry.herd.web.model.resp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
 * @Desc 全部参赛数量统计
 * @date Created in 2020年05月10日 18:10
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "全部参数数量统计")
public class QueryUserCountVo {
    @ApiModelProperty(notes = "各个地区参赛人数")
    private List<UserCountVo> userCountList;

    @ApiModelProperty(notes = "总的参赛任务")
    private Integer totalUserNum;
}
