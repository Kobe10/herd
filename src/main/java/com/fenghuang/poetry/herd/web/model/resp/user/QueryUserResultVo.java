package com.fenghuang.poetry.herd.web.model.resp.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @Desc 查询用户结果出参vo对象
 * @date Created in 2020年05月10日 18:01
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "查询用户结果出参vo对象")
public class QueryUserResultVo {

    @ApiModelProperty(notes = "所有用户信息")
    private List<UserDetailInfoVo> userInfoList;
}
