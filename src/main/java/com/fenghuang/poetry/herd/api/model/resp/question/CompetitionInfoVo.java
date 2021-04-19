package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

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
@ApiModel(value = "比赛详细信息")
public class CompetitionInfoVo implements Serializable {

    private static final long serialVersionUID = 3644823689956944698L;
    @ApiModelProperty(notes = "比赛大标题", value = "xxx海选赛")
    private String competitionTitle;

    @ApiModelProperty(notes = "比赛时间时长   单位:秒", value = "600")
    private Integer competitionDuration;

    @ApiModelProperty(notes = "开始答题时间   ", value = "2020-05-12 12:12:12")
    private String startAnswerTime;

    @ApiModelProperty(notes = "截止答题时间   ", value = "2020-05-12 12:12:12")
    private String endAnswerTime;

    @ApiModelProperty(notes = "比赛场景", value = "HX")
    private String sceneCode;

}
