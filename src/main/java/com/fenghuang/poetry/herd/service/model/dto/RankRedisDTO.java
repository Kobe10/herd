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
 * @date Created in 2020年07月17日 17:22
 * @since 1.0
 */
@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankRedisDTO implements Serializable {
    private static final long serialVersionUID = -7322671054047308675L;
    private String uid;
    private String userName;
    private String competitionCode;
    private String areaCode;

    private String provinceCode;
    private String school;
    private Integer rightNum;
    private Integer completeTime;
}
