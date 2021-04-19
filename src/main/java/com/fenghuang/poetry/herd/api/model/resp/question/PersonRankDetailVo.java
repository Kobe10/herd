package com.fenghuang.poetry.herd.api.model.resp.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@ApiModel(value = "个人成绩详细信息")
public class PersonRankDetailVo implements Serializable {

    private static final long serialVersionUID = -3189057521870744736L;

    @ApiModelProperty(notes = "用户uid")
    private String uid;

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "参赛码")
    private String competitionCode;

    @ApiModelProperty(notes = "省名称")
    private String rankProvinceName;

    @ApiModelProperty(notes = "市名称")
    private String rankAreaName;

    @ApiModelProperty(notes = "省级排名", value = "1000")
    private Integer provinceRankNum;

    @ApiModelProperty(notes = "省级排名中打败多少的人  %", value = "1000")
    private String provinceBeatRate;

    @ApiModelProperty(notes = "地区排名", value = "1000")
    private Integer areaNum;

    @ApiModelProperty(notes = "省级排名中打败多少的人  %", value = "1000")
    private String areaBeatRate;

    @ApiModelProperty(notes = "统计截止时间", value = "xx月xx日xx时xx分")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String countEndTime;

    @ApiModelProperty(notes = "答对题目个数", value = "20")
    private Integer rightQuestionNum;

    @ApiModelProperty(notes = "答题花费时长", value = "xx分xx秒")
    private String userTime;

    @ApiModelProperty(notes = "答题称谓", value = "惊才绝绝---------------")
    private String rankName;

    @ApiModelProperty(notes = "答题称谓", value = "惊才绝绝")
    private String appellation;

    private String areaCode;
}
