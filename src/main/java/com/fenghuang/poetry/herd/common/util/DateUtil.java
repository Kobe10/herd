package com.fenghuang.poetry.herd.common.util;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateUtil {

    /**
     * 日志输出
     */
    private static final Log log = LogFactory.getLog(DateUtil.class);

    /**
     * 主机时间与数据库时间的差值
     */
    private static Long hostDbTimeMinus = 0l;


    /**
     * 年
     */
    public static int YEAR = 1;

    /**
     * 月
     */
    public static int MONTH = 2;

    /**
     * 周
     */
    public static int WEEK = 3;

    /**
     * 日
     */
    public static int DAY = 5;

    /**
     * 小时
     */
    public static int HOUR = 10;

    /**
     * 分钟
     */
    public final static int MINUTE = 12;

    /**
     * 秒
     */
    public final static int SECOND = 13;

    /**
     * 毫秒
     */
    public final static int MILLISECOND = 14;

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy年MM月dd日",
            "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
            "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMdd"};

    /**
     *
     * @param string
     * @return
     */
    public static Date parseDate(String string) {
        if (string == null) {
            return null;
        }
        try {
            return DateUtils.parseDate(string, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 创建一个新的实例 DateUtil.
     */
    private DateUtil() {

    }

    /**
     * 将Date型转换为Timestamp类型
     *
     * @param date 日期
     * @return 时间撮
     */
    public static java.sql.Timestamp convertToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return new java.sql.Timestamp(date.getTime());
        }
    }

    /**
     * 将日期对象转换为yyyy-MM-dd HH:mm:ss格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static String toStringYmdHmsWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
    }

    /**
     * 将日期对象转换为yyyy-MM-dd HH:mm:ss SSS格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd HH:mm:ss SSS格式字符串
     */
    public static String toStringYmdHmsWthHS(Date date) {
        return (new SimpleDateFormat("yyyyMMddHHmmssSSS")).format(date);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd HH:mm:ss格式字符串
     */
    public static Date toDateYmdHmsWthH(String dateStr) throws ParseException {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateStr);
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式字符串转换为日期对象
     *
     * @param dateStr yyyy/MM/dd HH:mm:ss格式字符串
     */
    public static Date toDateYmdHmsWthH2(String dateStr) throws ParseException {
        return (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).parse(dateStr);
    }


    /**
     * 将日期对象转换为yyyy-MM-dd格式字符串
     *
     * @param date 时间对象
     * @return yyyy-MM-dd格式字符串
     */
    public static String toStringYmdWthH(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
    }

    /**
     * 将日期对象转换为yyyy/MM/dd格式字符串
     *
     * @param date 时间对象
     * @return yyyy/MM/dd格式字符串
     */
    public static String toStringYmdWthB(Date date) {
        return (new SimpleDateFormat("yyyy/MM/dd")).format(date);
    }

    /**
     * 将yyyy-MM-dd格式字符串转换为日期对象
     *
     * @param dateStr yyyy-MM-dd格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdWthH(String dateStr) throws ParseException {
        return (new SimpleDateFormat("yyyy-MM-dd")).parse(dateStr);

    }

    /**
     * 将Long类型时间-->日期对象
     *
     * @param dateLong Long类型时间
     * @return 日期对象
     */
    public static Date toDateLong(long dateLong) {
        if (dateLong == 0L) {
            return getSysDate();
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateLong);
        return c.getTime();
    }

    /**
     * 将日期对象转换为yyyyMMddHHmmss格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMddHHmmss格式字符串
     */
    public static String toStringYmdHms(Date date) {
        return (new SimpleDateFormat("yyyyMMddHHmmss")).format(date);
    }

    /**
     * 将yyyyMMddHHmmss格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMddHHmmss格式字符串
     * @return 日期对象
     */
    public static Date toDateYmdHms(String dateStr) throws ParseException {

        SimpleDateFormat dfm = new SimpleDateFormat("yyyyMMddHHmmss");
        return dfm.parse(dateStr);

    }

    /**
     * 将日期对象转换为yyyyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyyyMMdd格式字符串
     */
    public static String toStringYmd(Date date) {
        return (new SimpleDateFormat("yyyyMMdd")).format(date);
    }

    /**
     * 将日期对象转换为yyMMdd格式字符串
     *
     * @param date 时间对象
     * @return yyMMdd格式字符串
     */
    public static String toStringYYmd(Date date) {
        return (new SimpleDateFormat("yyMMdd")).format(date);
    }

    /**
     * 将yyyyMMdd格式字符串转换为日期对象
     *
     * @param dateStr yyyyMMdd格式字符串
     * @return 日期对象
     */
    public static Date toDateYmd(String dateStr) throws ParseException {

        return (new SimpleDateFormat("yyyyMMdd")).parse(dateStr);

    }

    /**
     * 将日期对象转换为yyyyMM格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYm(Date date) {
        return (new SimpleDateFormat("yyyyMM")).format(date);
    }

    /**
     * 将yyyyMM格式字符串转换为日期对象
     *
     * @param dateStr yyyyMM格式字符串
     * @return 日期对象
     */
    public static Date toDateYm(String dateStr) throws ParseException {

        return (new SimpleDateFormat("yyyyMM")).parse(dateStr);

    }

    /**
     * 将yyyyMM格式字符串转换为日期对象
     *
     * @param dateStr yyyyMM格式字符串
     * @return 日期对象
     */
    public static Date toDateHHmm(String dateStr) throws ParseException {

        return (new SimpleDateFormat("HHmm")).parse(dateStr);

    }

    /**
     * 将日期对象转换为yyyy格式字符串
     *
     * @param date 时间对象
     * @return yyyy格式字符串
     */
    public static String toStringY(Date date) {
        return (new SimpleDateFormat("yyyy")).format(date);
    }

    /**
     * 将yyyy格式字符串转换为日期对象
     *
     * @param dateStr yyyy格式字符串
     * @return 日期对象
     */
    public static Date toDateY(String dateStr) throws ParseException {

        return (new SimpleDateFormat("yyyy")).parse(dateStr);

    }

    /**
     * 将日期对象转换为yyyy年MM月dd日格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYmdwithChinese(Date date) {
        return (new SimpleDateFormat("yyyy年MM月dd日")).format(date);
    }

    /**
     * 将日期对象转换为yyyy年MM月dd日HH24时MI分SS秒格式字符串
     *
     * @param date 时间对象
     * @return yyyyMM格式字符串
     */
    public static String toStringYmdwithsfm(Date date) {
        return (new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")).format(date);
    }

    /**
     * 比较当前日期(数据库日期)是否大于指定日期
     *
     * @param str 待比较日期参数
     * @return boolean
     * @throws ParseException
     */
    public static boolean dateCompare(Object str) throws ParseException {
        boolean bea = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String isDate = simpleDateFormat.format(getSysDate());

        Date date1 = null;
        Date date0;

        if (str instanceof Date) {
            date1 = simpleDateFormat.parse(DateUtil.toStringYmdWthH((Date) str));
        }
        if (str instanceof String) {
            date1 = simpleDateFormat.parse((String) str);
        }
        date0 = simpleDateFormat.parse(isDate);
        if (date0.after(date1)) {
            bea = true;
        }

        return bea;
    }

    /**
     * 比较两个日期的大小
     * <p/>
     * <pre>
     * 1、日期参数为空表示无穷小
     * </pre>
     *
     * @param inDate1 第一个日期参数
     * @param inDate2 第二个日期参数
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     * @throws
     */
    public static int dateCompare(Date inDate1, Date inDate2) {
        return dateCompare(inDate1, inDate2, SECOND);
    }

    /**
     * 比较日期大小
     *
     * @param inDate1 日期1
     * @param inDate2 日期2
     * @param unit    比较精度 年：1 ;月：2; 周：3; 日：5; 时：10; 分：12;秒：13
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     */
    public static int dateCompare(Date inDate1, Date inDate2, int unit) {
        // 字符空验证
        if (inDate1 == null && inDate2 == null) {
            // 两个日期都为空返回相等
            return 0;
        } else if (inDate1 == null) {
            // 日期1为空，日期2不为空返回-1
            return -1;
        } else if (inDate2 == null) {
            // 日期1不为空，日期为空返回2
            return 1;
        }
        return DateUtil.truncate(inDate1, unit).compareTo(DateUtil.truncate(inDate2, unit));
    }

    /**
     * 调整时间, 按照需要调整的单位调整时间
     * <p/>
     * <pre>
     * 例如:保留到日期的年change("20120511154440", DateUtil.YEAE, 2);返回：20140511154440<br/>
     * </pre>
     *
     * @param inDate 传入日志
     * @param unit   调整单位 年：1 ;月：2; 周：3; 日：5; 时：10; 分：12;秒：13
     * @param amount 调整数量
     * @return 调整后的日期
     */
    public static Date change(Date inDate, int unit, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inDate);
        calendar.add(unit, amount);
        return calendar.getTime();
    }

    /**
     * 按照精度要求对日期进行舍取
     * <p/>
     * <pre>
     * 例如:保留到日期的年truncate("20120511154440", DateUtil.YEAE);返回：20120101000000<br/>
     * </pre>
     *
     * @param inDate 输入日期
     * @param unit   单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理后的日期
     */
    public static Date truncate(Date inDate, int unit) {
        return DateUtils.truncate(inDate, unit);
    }

    /**
     * 按照指定的党委获取指定日志的最后值
     * <p/>
     * <pre>
     * 例如:保留到日期的年last("20120511154440", DateUtil.YEAE);返回：20121231235959<br/>
     * </pre>
     *
     * @param inDate 输入日期
     * @param unit   单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理后的日期
     */
    public static Date last(Date inDate, int unit) {
        Date tempDate = truncate(inDate, unit);
        tempDate = change(tempDate, unit, 1);
        return change(tempDate, DateUtil.MILLISECOND, -1);

    }

    /**
     * 比较两个日期的大小
     * <p/>
     * <pre>
     * 1、日期参数为空表示无穷小
     * </pre>
     *
     * @param inDate1 第一个日期参数
     * @param inDate2 第二个日期参数
     * @param unit    单位 年：1 ;月：2;日：5; 时：10; 分：12;秒：13
     * @return 处理结果 0:相等, -1:inDate1<inDate2, 1:inDate1>inDate2
     * @throws
     */
    public static int truncateComare(Date inDate1, Date inDate2, int unit) {
        if (inDate1 == null && inDate2 == null) {
            // 两个日期都为空返回相等
            return 0;
        } else if (inDate1 == null) {
            // 日期1为空，日期2不为空返回-1
            return -1;
        } else if (inDate2 == null) {
            // 日期1不为空，日期为空返回2
            return 1;
        } else {
            Date tempDate1 = truncate(inDate1, unit);
            Date tempDate2 = truncate(inDate2, unit);
            return tempDate1.compareTo(tempDate2);
        }

    }

    /**
     * 将日期格式化为指定的格式
     *
     * @param date    日期
     * @param pattern 格式化模式
     * @return
     */
    public static String format(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 将日期格式化为指定的格式
     *
     * @param dateStr 日期
     * @param pattern 格式化模式
     * @return
     */
    public static Date parse(String dateStr, String pattern) throws ParseException {

        return (new SimpleDateFormat(pattern)).parse(dateStr);

    }

    /**
     * 获取数据库当前时间
     * 根据主机时间与主机时间与系统时间的差值获取统一时间
     *
     * @return 数据库时间
     */
    public static Date getSysDate() {
        // 根据主机时间及及
        return new Date(new Date().getTime() + hostDbTimeMinus);
    }

    /**
     * 更新主机时间与数据库统一时间的差值
     *
     * @param minusTime
     */
    private static void updateHostDbTimeMinus(long minusTime) {
        synchronized (hostDbTimeMinus) {
            hostDbTimeMinus = minusTime;
        }
    }

    /**
     * 同步系统时间
     */
    public static void refreshSysDate(Date nowdate) {
        Date hostDate = new Date();
        long minusTime = nowdate.getTime() - hostDate.getTime();
        updateHostDbTimeMinus(minusTime);
    }

    public static Date getNowDateShort(Date data) throws ParseException {

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);

        SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd"));
        Date date = null;
        date = sdf.parse(dateString);
        java.sql.Date date1 = new java.sql.Date(date.getTime());

        return new Date(date1.getTime());

    }

    /**
     * 获取指定时间
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date getDate(int year, int month, int day) {
        Calendar c = GregorianCalendar.getInstance();
        c.clear();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return new Date(c.getTimeInMillis());
    }


    /**
     * @param
     * @return
     * @author lbs
     * @description 将String类型的日期转换为Long类型的时间
     * @date 2019/8/26 10:02
     */
    public static Long convertStringDateToLong(String date) {
        try {
            if (date == null) {
                return null;
            }
            Date convertDate = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
            return convertDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * long 类型转换
     *
     * @param
     * @return
     * @author lbs
     * @description
     * @date 2019/9/24 11:00
     */
    public static String millisLong2Date(long millis) {
        if (millis > 0) {

            String format = DateFormatUtils.format(millis, "yyyy-MM-dd HH:mm:ss", TimeZone.getDefault());
            return format;
        }
        return "";
    }

    public static String millisLong2Date(long millis, String formatType) {
        if (millis > 0) {

            String format = DateFormatUtils.format(millis, formatType, TimeZone.getDefault());
            return format;
        }
        return "";
    }

    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " 天 " + hours + " 小时 " + minutes + " 分 "
                + seconds + " 秒 ";
    }
}

