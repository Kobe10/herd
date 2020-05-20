package com.fenghuang.poetry.herd.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class RegexUtil {
    /**
     * @param str 需要过滤的字符串
     * @return
     * @Description: 过滤数字以外的字符
     */
    public static String filterUnNumber(String str) {
        if (StringUtils.isNotBlank(str)) {
            // 只允数字
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            //替换与模式匹配的所有字符（即非数字的字符将被""替换）
            return m.replaceAll("").trim();
        }
        return null;
    }

    /**
     * 判断是不是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        // ^ 匹配输入字符串开始的位置
        // \d 匹配一个或多个数字，其中 \ 要转义，所以是 \\d
        // $ 匹配输入字符串结尾的位置
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])" +
                "|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 去掉字符串后面的小数点和0
     *
     * @param str
     * @return
     */
    public static int removeTrim(String str) {
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");//去掉多余的0
            str = str.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return Integer.parseInt(str);
    }

    /**
     * 过滤掉html标签
     *
     * @param str
     * @return
     */
    public static String filterHtmlTag(String str) {
        if (StringUtils.isNotBlank(str)) {
            String htmlStr = str; //含html标签的字符串
            String textStr = "";
            Pattern p_script;
            Matcher m_script;
            Pattern p_style;
            Matcher m_style;
            Pattern p_html;
            Matcher m_html;
            try {
                String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
                String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
                String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
                p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
                m_script = p_script.matcher(htmlStr);
                htmlStr = m_script.replaceAll(""); //过滤script标签
                p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
                m_style = p_style.matcher(htmlStr);
                htmlStr = m_style.replaceAll(""); //过滤style标签
                p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
                m_html = p_html.matcher(htmlStr);
                htmlStr = m_html.replaceAll(""); //过滤html标签
                textStr = htmlStr;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return textStr;//返回文本字符串
        }
        return str;
    }
}
