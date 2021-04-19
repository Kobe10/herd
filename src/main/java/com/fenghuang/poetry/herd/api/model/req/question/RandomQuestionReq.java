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
 * @Desc 登录请求实体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "随机获取问题入参", description = "随机获取问题入参`")
public class RandomQuestionReq implements Serializable {

    private static final long serialVersionUID = 3114624340284335599L;

    @ApiModelProperty(notes = "参赛码", value = "sdx-asdfw1234", required = true)
    @NotBlank(message = "参赛码不能为空")
    private String competitionCode;
}
