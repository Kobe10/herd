//package com.fenghuang.poetry.herd.common.util;
//
//import javax.validation.constraints.NotNull;
//import java.security.InvalidParameterException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * <p></p>
// * <p>
// * <PRE>
// * <BR>    修改记录
// * <BR>-----------------------------------------------
// * <BR>    修改日期         修改人          修改内容
// * </PRE>
// *
// * @author fuzq
// * @version 1.0
// * @Desc
// * @date Created in 2020年07月13日 20:19
// * @since 1.0
// */
//public class VedioExtractSpeechUtil {
//    public static void main(String[] args) {
//        System.out.println("toChinese：" + Chinese2Arab("一十九"));
////        System.out.println("toChinese2：" + toChinese2("19"));
//    }
//
//
//
//    //读音与汉字对应的表
//    private static final Map<Character, Integer> Arab2Chinese = new HashMap<Character, Integer>() {
//        {
//            put('零', 0);
//            put('一', 1);
//            put('二', 2);
//            put('三', 3);
//            put('四', 4);
//            put('五', 5);
//            put('六', 6);
//            put('七', 7);
//            put('八', 8);
//            put('九', 9);
//            put('十', 10);
//
//        }
//    };
//
//    /**
//     * 单位对应数量级的表
//     */
//    private static final Map<Character, Integer> UnitMap = new HashMap<Character, Integer>() {
//        {
//            put('十', 10);
//            put('百', 100);
//            put('千', 1000);
//            put('万', 10000);
//            put('亿', 10000 * 10000);
//
//        }
//    };
//
//    //正则提取
//    private static Pattern pattern = Pattern.compile("[零一二三四五六七八九十]?[十百千万亿]?");
//
//
//
//    /**
//     * 处理思路：
//     * <p>
//     * 可能的类型：
//     * 一千三百万  130000000
//     * 十一       11
//     * 一万       10000
//     * 一百二十五  125
//     * <p>
//     * 通过正则将字符串处理为 数字+单位的情况
//     * 然后处理的时候通过数字*单位+数字*单位...得到最后结果
//     *
//     * @param chinese 汉字输入，比如一千三百二十八
//     * @return 阿拉伯输入，比如 1328
//     */
//    @NotNull
//    public static Integer Chinese2Arab(String chinese) {
//
//        Objects.requireNonNull(chinese);
//
//        //判断参数合法性
//        if (!pattern.matcher(chinese).replaceAll("").trim().equals("")) {
//            throw new InvalidParameterException();
//        }
//
//        Integer result = 0;
//
//        Matcher matcher = pattern.matcher(chinese);
//
//        //正则提取所有数字
//        while (matcher.find()) {
//            String res = matcher.group(0);
//            if (res.length() == 2) {
//                result += Arab2Chinese.get(res.charAt(0)) * UnitMap.get(res.charAt(1));
//            } else if (res.length() == 1) {
//
//                //处理三十、一百万的情况
//                if (UnitMap.containsKey(res.charAt(0))) {
//                    result *= UnitMap.get(res.charAt(0));
//                } else if (Arab2Chinese.containsKey(res.charAt(0))) {
//                    result += Arab2Chinese.get(res.charAt(0));
//                }
//            }
//        }
//        return result;
//    }
//}
//
