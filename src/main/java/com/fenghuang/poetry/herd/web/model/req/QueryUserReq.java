package com.fenghuang.poetry.herd.web.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
 * @Desc 查询用户入参
 * @date Created in 2020年05月10日 17:52
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "查询用户列表入参", description = "查询用户列表入参")
public class QueryUserReq {
    @ApiModelProperty(notes = "用户姓名", value = "韦小宝", required = true)
    private String userName;

    @ApiModelProperty(notes = "用户电话", value = "17611221957  限制位数 11位并且有效", required = true)
    private String phone;

    @ApiModelProperty(notes = "地区编码", value = "330200   这是宁波是的编码")
    private String areaCode;

    @ApiModelProperty(notes = "当前页", value = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(notes = "每页数量", value = "10")
    private int pageSize = 10;
}
