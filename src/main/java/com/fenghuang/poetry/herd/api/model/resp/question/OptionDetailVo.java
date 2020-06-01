package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
 * @Desc
 * @date Created in 2020年06月01日 22:11
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "选项的vo对象")
public class OptionDetailVo implements Serializable {
    private static final long serialVersionUID = -8334672472819036234L;
    @ApiModelProperty(notes = "选项id 唯一标识")
    private Integer optionId;

    @ApiModelProperty(notes = "选择题会有这个选项 A或者B或者C  提交答案的时候把这个属性提交上来；  填空题没有这个选项")
    private String letter;

    @ApiModelProperty(notes = "选项名称")
    private String optionTitle;

    @ApiModelProperty(notes = "选项描述")
    private String questionDesc;

    @ApiModelProperty(notes = "选项类型")
    private String optionType;
}
