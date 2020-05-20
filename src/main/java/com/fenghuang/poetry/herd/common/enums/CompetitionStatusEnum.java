package com.fenghuang.poetry.herd.common.enums;

import com.fenghuang.poetry.herd.config.exception.BusinessException;
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
 * @Desc 报名码状态
 * @date Created in 2020年05月12日 14:30
 * @since 1.0
 */
@Getter
@AllArgsConstructor
public enum CompetitionStatusEnum {
    WSY("wsy", "未使用"),
    SYZ("syz", "使用中"),
    YSY("ysy", "已使用"),
    YGQ("ygq", "已过期"),
    YFH("yfh", "已复活");


    /**
     * 类型编码
     */
    private String code;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 是否已经使用
     *
     * @param competitionStatus
     * @return
     */
    public static boolean isUser(String competitionStatus) {
        CompetitionStatusEnum statusEnum = getByCode(competitionStatus);
        if (statusEnum == null) {
            throw new BusinessException("报名码未知状态!");
        }
        return statusEnum.equals(YSY);
    }

    public static CompetitionStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (CompetitionStatusEnum canShowStatusEnum : CompetitionStatusEnum.values()) {
            if (canShowStatusEnum.getCode().equals(code.trim())) {
                return canShowStatusEnum;
            }
        }
        return null;
    }
}
