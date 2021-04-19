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
 * @Desc 随机策略枚举类型
 * @date Created in 2020年05月12日 14:30
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum RandomStrategyEnum {
    Default("default", "默认随机规则"),
    HX_STRATEGY("hx-random", "海选随机规则 9易1难"),
    FS_ALL("fs-all", "复赛all查询"),
    CJ_STRATEGY("cj-random", "初级赛随机规则");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    public static RandomStrategyEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) return null;
        for (RandomStrategyEnum canShowStatusEnum : RandomStrategyEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return null;
    }
}
