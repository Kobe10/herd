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
 * @Desc
 * @date Created in 2020年05月12日 13:49
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionDto implements Serializable {
    private static final long serialVersionUID = -7748578355221115118L;

    // 报名码
    private String competitionCode;

    // 报名码状态
    private String competitionCodeStatus;
}
