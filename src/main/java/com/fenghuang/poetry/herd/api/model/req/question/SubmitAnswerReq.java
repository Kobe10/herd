package com.fenghuang.poetry.herd.api.model.req.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
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
 * @Desc 提交答案请求体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "提交答案入参", description = "提交答案入参`")
public class SubmitAnswerReq {

    @ApiModelProperty(notes = "参赛码", value = "shx-sdfasfxxx", required = true)
    @NotBlank(message = "参赛码为空")
    private String competitionCode;

    @ApiModelProperty(notes = "场景编码", value = "HX", required = true)
    @NotBlank(message = "场景编码不能为空")
    private String sceneCode;

    @ApiModelProperty(notes = "提交答案信息", value = "shx-sdfasfxxx", required = true)
    @NotBlank(message = "提交答案信息")
    private List<AnswerInfoReq> answerInfoReqList;
}
