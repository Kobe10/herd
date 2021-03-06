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
 * @Desc 阶段枚举
 * @date Created in 2020年05月12日 14:30
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum ApplyStatusEnum {
    YBM("ybm", "已报名"),
    WBM("wbm", "未报名");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    public static ApplyStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) return null;
        for (ApplyStatusEnum canShowStatusEnum : ApplyStatusEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return null;
    }
}
