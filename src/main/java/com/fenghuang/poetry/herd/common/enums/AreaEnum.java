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
public enum AreaEnum {

    HZ("330100", "杭州市"),
    NB("330200", "宁波市"),
    WZ("330300", "温州市"),
    SX("330600", "绍兴市"),
    HZS("330500", "湖州市"),
    JX("330400", "嘉兴市"),
    JH("330700", "金华市"),
    QZ("330800", "衢州市"),
    TZ("331000", "台州市"),
    LS("331100", "丽水市"),
    ZS("330900", "舟山市");


    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    public static String getAreaNameByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return "未知地区";
        }
        for (AreaEnum canShowStatusEnum : AreaEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum.getName();
            }
        }
        return "未知地区";
    }
}
