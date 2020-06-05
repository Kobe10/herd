package com.fenghuang.poetry.herd.api.model.resp.question;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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
 * @Desc 参赛码详细信息
 * @date Created in 2020年05月10日 15:00
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "排名详细信息对象")
public class RankDetailVo implements Serializable {

    private static final long serialVersionUID = -3189057521870744736L;
    @ApiModelProperty(notes = "排名类型", value = "province：浙江省排名；  area：地区排名")
    private String rankType;

    @ApiModelProperty(notes = "排名类型名称  用于前端展示", value = "例如：浙江省个人当前排名、 杭州市个人当前排名")
    private String rankTypeName;

    @ApiModelProperty(notes = "排名类型名称  用于前端展示", value = "例如：浙江省个人当前排名、 杭州市个人当前排名")
    private Integer rank;

    @ApiModelProperty(notes = "序号  省级排名序号在前，地区排名在后，前端取值的时候按照这个序号排序来取", value = "例如：浙江省个人当前排名、 杭州市个人当前排名")
    private Integer orderValue;
}
