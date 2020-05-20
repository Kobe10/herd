package com.fenghuang.poetry.herd.common.enums;

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
 * @Desc 时间间隔枚举
 * @date Created in 2020年01月14日 14:34
 * @since 1.0
 */
public enum TimeIntervalEnum {
    Day("day","日"),
    Week("week","周"),
    Month("month","月"),
    Year("year","年");

    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    TimeIntervalEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
