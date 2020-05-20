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
 * @Desc 登录结果
 * @date Created in 2020年05月10日 18:17
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "后台登录结果")
public class LoginResultVo {
    @ApiModelProperty(notes = "登录结果 -先简单一点 'login'代表登录--跳到主页 'out'-退出 跳到登录页面")
    private String loginResult;
}
