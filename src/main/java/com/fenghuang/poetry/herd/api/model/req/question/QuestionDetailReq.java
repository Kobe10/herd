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
@ApiModel(value = "查看问题详情入参", description = "查看问题详情入参`")
public class QuestionDetailReq implements Serializable {

    private static final long serialVersionUID = -7592777220968777235L;
    @ApiModelProperty(notes = "问题id", value = "111112s", required = true)
//    @NotBlank(message = "问题id不能为空")
    private String questionId;
}
