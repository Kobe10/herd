package com.fenghuang.poetry.herd.api.model.req.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
 * @Desc 答案详情请求体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "答案详情入参", description = "答案详情入参`")
public class AnswerInfoReq implements Serializable {

    private static final long serialVersionUID = 4209569127767728022L;
    @ApiModelProperty(notes = "是否为答案", value = "0 不是答案   1 用户提交上来的答案", required = true)
    @NotBlank(message = "答案标记不能为空")
    private Integer isAnswer;

    @ApiModelProperty(notes = "问题id", value = "quxxxxxxx", required = true)
    @NotBlank(message = "问题编码不能为空")
    private String questionCode;

    @ApiModelProperty(notes = "选项编码", value = "如果是选择题就传   填空题传null")
    private String optionCode;

    @ApiModelProperty(notes = "问题类型", value = "radio:单选 Multiple:多选 blank:填空", required = true)
    @NotBlank(message = "问题类型不能为空")
    private String questionType;

    @ApiModelProperty(notes = "用户填写答案", value = "单选:A   多选:ABC  填空:xxxx", required = true)
    private String userAnswer;

    @ApiModelProperty(notes = "问题的顺序  必须提交后端返回的顺序id", value = "1", required = true)
    private Integer questionOrderValue;
}
