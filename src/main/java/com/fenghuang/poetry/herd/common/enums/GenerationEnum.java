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
public enum GenerationEnum {
    primary("primary", "幼儿园-五年级"),
    junior("junior", "六年级-初二"),
    high("high", "初三-高二"),
    colleague("colleague", "高三-大学"),
    other("colleague", "默认");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    public static GenerationEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return other;
        }
        for (GenerationEnum canShowStatusEnum : GenerationEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return other;
    }
}
