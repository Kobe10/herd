package com.fenghuang.poetry.herd.api.model.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @Desc
 * @date Created in 2020年05月10日 14:56
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "报名详细信息")
public class ApplyInfoVo implements Serializable {

    private static final long serialVersionUID = -8475401027021887426L;

    @ApiModelProperty(notes = "参赛状态  ybm-已报名  wbm-未报名")
    private String applyStatus;

    @ApiModelProperty(notes = "报名用户信息")
    private UserInfoVo userInfoVo;

    @ApiModelProperty(notes = "报名码信息")
    private CompetitionCodeInfoVo competitionCodeInfoVo;
}
