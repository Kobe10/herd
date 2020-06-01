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
 * @Desc 用户登录返回信息
 * @date Created in 2020年05月10日 14:58
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录用户返回信息")
public class LoginUserInfoVo {

    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户UID")
    private String uid;

    @ApiModelProperty(notes = "参赛码")
    private String competitionCode;

    @ApiModelProperty(notes = "地区编码")
    private String areaCode;

    @ApiModelProperty(notes = "用户处于比赛阶段编码")
    private String stageCode;

    @ApiModelProperty(notes = "用户处于比赛阶段编码")
    private String stageName;

    @ApiModelProperty(notes = "场景编码")
    private String sceneCode;

    @ApiModelProperty(notes = "场景名称")
    private String sceneName;
}
