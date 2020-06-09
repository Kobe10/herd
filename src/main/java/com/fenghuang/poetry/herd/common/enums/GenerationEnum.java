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
    de("other", "小学段"),
    primary("primary", "小学段"),
    junior("junior", "初中段"),
    high("high", "高中段"),
    other("other", "高中段");

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
            return de;
        }
        for (GenerationEnum canShowStatusEnum : GenerationEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return de;
    }
}
