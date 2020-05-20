package com.fenghuang.poetry.herd.service.model.dto;

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
 * @Desc 用户额外信息
 * @date Created in 2020年05月12日 13:29
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExtraInfoDto implements Serializable {
    private static final long serialVersionUID = 4706771765336214897L;

    //场景编码
    private String sceneCode;
    //阶段编码
    private String stageCode;
}
