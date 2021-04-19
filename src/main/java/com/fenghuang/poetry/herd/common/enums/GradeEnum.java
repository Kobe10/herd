package com.fenghuang.poetry.herd.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

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
 * @date Created in 2020年05月12日 14:30
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum GradeEnum {
    one("one", "幼儿园", "primary"),
    two("two", "一年级", "primary"),
    three("three", "二年级", "primary"),
    four("four", "三年级", "primary"),
    five("five", "四年级", "primary"),
    six("six", "五年级", "primary"),
    seven("seven", "六年级", "junior"),
    eight("eight", "七年级", "junior"),
    nine("nine", "八年级", "junior"),
    ten("ten", "九年级", "high"),
    eleven("eleven", "高一", "high"),
    twelve("twelve", "高二", "high"),
    thirteen("thirteen", "高三", "colleague"),
    fourteen("fourteen", "大学", "colleague"),
    other("fourteen", "大学", "colleague");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 赛段编码
     */
    private String generaCode;

    public static GradeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return other;
        }
        for (GradeEnum canShowStatusEnum : GradeEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return other;
    }

    public static GradeEnum getByCodeForUpdate(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (GradeEnum canShowStatusEnum : GradeEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return null;
    }
}
