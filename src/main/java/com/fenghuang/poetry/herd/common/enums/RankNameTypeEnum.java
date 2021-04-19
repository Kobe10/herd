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
public enum RankNameTypeEnum {
    XSND("xsnd", "小试牛刀", "小试牛刀"),
    ZLTJ("zltj", "崭露头角", "崭露头角"),
    FYSS("fyss", "腹有诗书", "腹有诗书"),
    ZEBQ("zebq", "卓尔不群", "卓尔不群"),
    JCJJ("jcjj", "惊才绝艳", "惊才绝艳");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;
    private String fenxiang;

    public static RankNameTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) return null;
        for (RankNameTypeEnum canShowStatusEnum : RankNameTypeEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return null;
    }
}
