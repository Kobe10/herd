package com.fenghuang.poetry.herd.web.model.resp.user;

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
 * @Desc 用户信息VO对象
 * @date Created in 2020年05月10日 17:55
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "单个用户详细信息")
public class UserDetailInfoVo {
    @ApiModelProperty(notes = "用户姓名")
    private String userName;

    @ApiModelProperty(notes = "用户电话")
    private String phone;

    @ApiModelProperty(notes = "微信号")
    private String wxNum;

    @ApiModelProperty(notes = "地区编码")
    private String areaCode;

    @ApiModelProperty(notes = "地区中文名称")
    private String areaName;

    @ApiModelProperty(notes = "学校")
    private String school;

    @ApiModelProperty(notes = "年级")
    private String grade;
}
