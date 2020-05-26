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
@AllArgsConstructor
public enum AreaWxChatEnum {
    Default("", "默认.png"),
    HZ("330100", "杭州.png"),
    NB("330200", "宁波.png"),
    WZ("330300", "温州.png"),
    SX("330600", "默认.png"),
    HZS("330500", "默认.png"),
    JX("330400", "默认.png"),
    JH("330700", "默认.png"),
    QZ("330800", "默认.png"),
    TZ("331000", "默认.png"),
    LS("331100", "默认.png"),
    ZS("330900", "默认.png");

    /**
     * 上线之后修改 todo
     */
    private static final String pngPath = "http://211.159.176.138:8085/herd/images/";
    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String chatPng;

    public static String getPngPathByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return Default.getChatPng();
        }
        for (AreaWxChatEnum canShowStatusEnum : AreaWxChatEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum.getChatPng();
            }
        }
        return Default.getChatPng();
    }

    public String getChatPng() {
        return pngPath + chatPng;
    }

    public String getCode() {
        return code;
    }
}
