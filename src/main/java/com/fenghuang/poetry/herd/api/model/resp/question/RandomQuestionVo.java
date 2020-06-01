package com.fenghuang.poetry.herd.api.model.resp.question;

import com.fenghuang.poetry.herd.api.model.resp.CompetitionRuleInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
 * @Desc 随机问题的vo对象
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "随机问题出参")
public class RandomQuestionVo implements Serializable {

    private static final long serialVersionUID = -178967570204061848L;

    @ApiModelProperty(notes = "比赛详细信息")
    private CompetitionInfoVo competitionInfoVo;

    @ApiModelProperty(notes = "问题详情列表")
    private List<QuestionDetailVo> questionIdVoList;

    @ApiModelProperty(notes = "问题规则")
    private CompetitionRuleInfoVo competitionRuleInfoVo;
}
