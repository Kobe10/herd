package com.fenghuang.poetry.herd.common.util;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class NumberUtil {

    /**
     * 判断一个字符串是否是数字。
     *
     * @param string
     * @return
     */
    public static boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        Pattern pattern = compile("\\d+");
        return pattern.matcher(string).matches();
    }

}
