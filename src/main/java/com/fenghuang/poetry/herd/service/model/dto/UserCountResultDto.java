package com.fenghuang.poetry.herd.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
 * @Desc
 * @date Created in 2020年05月12日 23:12
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCountResultDto implements Serializable {
    private static final long serialVersionUID = -4995725816183440936L;

    private String areaCode;

    private String areaName;

    private Integer userNum;
}
