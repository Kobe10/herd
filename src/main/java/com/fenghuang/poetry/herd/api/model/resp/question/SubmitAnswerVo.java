package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
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
 * @Desc 参赛码详细信息
 * @date Created in 2020年05月10日 15:00
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "提交答案返回信息")
public class SubmitAnswerVo {

    @ApiModelProperty(notes = "排名信息  包含省级，地区级  已经排序")
    private List<RankDetailVo> rankDetailVoList;

    @ApiModelProperty(notes = "答题结果详细信息")
    private AnswerDetailVo answerDetailVo;
}
