package com.fenghuang.poetry.herd.api.model.resp.question;

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
@ApiModel(value = "答题结果详细信息")
public class AnswerDetailVo {

    @ApiModelProperty(notes = "分数", value = "答题最终分数")
    private Integer score;

    @ApiModelProperty(notes = "答题花费时长", value = "10分20秒")
    private String userTime;
}
