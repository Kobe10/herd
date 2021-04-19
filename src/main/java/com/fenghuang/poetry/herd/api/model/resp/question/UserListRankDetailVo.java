package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class UserListRankDetailVo implements Serializable {

    private static final long serialVersionUID = -3189057521870744736L;

    @ApiModelProperty(notes = "用户uid")
    private String uid;

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "排行榜排名", value = "1000")
    private Integer rank;

    @ApiModelProperty(notes = "学校  %", value = "沙发斯蒂芬")
    private String school;

    @ApiModelProperty(notes = "正确答题数", value = "20")
    private Integer rightSubjectNum;

    @ApiModelProperty(notes = "答题花费时间", value = "xx分xx秒")
    private String userTime;
}
