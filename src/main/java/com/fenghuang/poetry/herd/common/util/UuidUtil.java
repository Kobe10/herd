package com.fenghuang.poetry.herd.common.util;

import java.util.UUID;

public class UuidUtil {
    /**
     * 32位UUid 无 -
     *
     * @return
     */
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }
}
