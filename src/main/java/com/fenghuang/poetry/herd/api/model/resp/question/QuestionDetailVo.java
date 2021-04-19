package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
 * @Desc 问题Id的vo对象
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "问题的vo对象")
public class QuestionDetailVo implements Serializable {
    private static final long serialVersionUID = 3305017896655222744L;
    @ApiModelProperty(notes = "问题Id 唯一标识，用来查询问题详情使用")
    private String questionCode;

    @ApiModelProperty(notes = "数据库问题顺序")
    private Integer questionOrderValue;

    @ApiModelProperty(notes = "前端显示问题顺序")
    private Integer logicOrderValue;

    @ApiModelProperty(notes = "问题名称")
    private String questionTitle;

    @ApiModelProperty(notes = "问题类型")
    private String questionType;

    @ApiModelProperty(notes = "问题难度")
    private String difficult;

    @ApiModelProperty(notes = "选项数量")
    private Integer optionCount;

    @ApiModelProperty(notes = "选项内容")
    private List<OptionDetailVo> optionDetailVoList;
}
