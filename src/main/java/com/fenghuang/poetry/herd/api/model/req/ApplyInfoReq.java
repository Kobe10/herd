package com.fenghuang.poetry.herd.api.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
 * @Desc 报名信息请求实体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "提交报名信息入参", description = "提交报名信息入参")
public class ApplyInfoReq implements Serializable {
    private static final long serialVersionUID = 6749847801877685314L;
    @ApiModelProperty(notes = "用户姓名", value = "韦小宝", required = true)
    @NotNull(message = "请填写姓名")
    private String userName;

    @ApiModelProperty(notes = "用户电话", value = "17611221957  限制位数 11位并且有效", required = true)
    @NotBlank(message = "请填写手机号")
    private String phone;

    @ApiModelProperty(notes = "微信号", value = "蓝色的填空", required = true)
    @NotBlank(message = "请填写微信号")
    private String wxNum;

    @ApiModelProperty(notes = "地区编码", value = "330200   这是宁波是的编码", required = true)
    @NotBlank(message = "请填写地区号")
    private String areaCode;

    @ApiModelProperty(notes = "学校", value = "杭州附小  手动填写", required = true)
    @NotBlank(message = "请填写学校")
    private String school;

    @ApiModelProperty(notes = "年级", value = "一年级  手动填写", required = true)
    @NotBlank(message = "请填写年级")
    private String grade;
}
