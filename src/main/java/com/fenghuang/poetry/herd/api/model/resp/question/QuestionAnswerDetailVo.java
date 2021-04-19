package com.fenghuang.poetry.herd.api.model.resp.question;

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
 * @Desc 答案详情请求体
 * @date Created in 2020年05月10日 14:33
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "问题答案详情", description = "问题答案详情`")
public class QuestionAnswerDetailVo {

    @ApiModelProperty(notes = "是否为答案", value = "0 不是答案   1 是提交上来的答案")
    @NotBlank(message = "标记为空")
    private Integer isAnswer;

    @ApiModelProperty(notes = "问题id", value = "asdfadf")
    @NotBlank(message = "问题id不能为空")
    private String questionCode;

    @ApiModelProperty(notes = "问题类型", value = "radio:单选 Multiple:多选 blank:填空")
    @NotBlank(message = "问题类型不能为空")
    private String questionType;

    @ApiModelProperty(notes = "用户填写答案  如果是填空题  当前就是答案；  如果是单选，  答案在option里面", value = "填空:xxxx   单选不在此答案")
    private String userAnswer;

    @ApiModelProperty(notes = "正确答案", value = "当前题目正确答案")
    private String rightAnswer;

    @ApiModelProperty(notes = "问题顺序")
    private Integer orderValue;

    @ApiModelProperty(notes = "问题名称")
    private String questionTitle;

    @ApiModelProperty(notes = "问题难度")
    private String difficult;

    @ApiModelProperty(notes = "选项数量")
    private Integer optionCount;

    @ApiModelProperty(notes = "逻辑顺序  从1开始")
    private Integer logicOrderValue;

    @ApiModelProperty(notes = "是否正确 0 错误  1 正确")
    private Integer right;

    @ApiModelProperty(notes = "选项内容")
    private List<OptionDetailVo> optionDetailVoList;
}
