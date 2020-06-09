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
    Default("", "330100", "以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会杭州交流群“"),
    HZ("330100", "330100","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会杭州交流群“"),
    NB("330200", "330200","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会宁波交流群“"),
    WZ("330300", "330300","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会温州交流群”"),
    SX("330600", "330600","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会绍兴交流群”"),
    HZS("330500", "330500","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会湖州交流群”"),
    JX("330400", "330400","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会嘉兴交流群”"),
    JH("330700", "330700","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会金华交流群”"),
    QZ("330800", "330800","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会衢州交流群”"),
    TZ("331000", "331000","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会台州交流群”"),
    LS("331100", "331100","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会丽水交流群”"),
    ZS("330900", "330900","以诗会友，共诵经典；大赛讲座，最新资讯\n" +
            "欢迎扫码加入“浙江诗词大会舟山交流群”");

    /**
     * 上线之后修改 todo
     */
    private static final String pngPath = "http://211.159.176.138:8085/herd/web/tool/show/wx/";
    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String chatPng;

    /**
     * 文案
     */
    private String copyWriting;

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

    public static String getCopyWritingByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return Default.getCopyWriting();
        }
        for (AreaWxChatEnum canShowStatusEnum : AreaWxChatEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum.getCopyWriting();
            }
        }
        return Default.getCopyWriting();
    }

    public String getChatPng() {
        return pngPath + chatPng;
    }

    public String getCode() {
        return code;
    }

    public String getCopyWriting() {
        return copyWriting;
    }
}
