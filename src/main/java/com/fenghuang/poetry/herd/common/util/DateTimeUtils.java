package com.fenghuang.poetry.herd.common.util;

import com.fenghuang.poetry.herd.common.enums.TimeIntervalEnum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class DateTimeUtils {

    public static final String SIMPLE_PATTERN = "yyyy-MM-dd";
    public static final String NORMAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得当日零点时间对象
     */
    public static <T extends Date> T getTodayZero(Class<T> type) {
        return getBeforeDayZero(0, type);
    }

    public static <T extends Date> T getYesterdayZero(Class<T> type) {
        return getBeforeDayZero(1, type);
    }

    public static <T extends Date> T getTomorrowZero(Class<T> type) {
        return getBeforeDayZero(-1, type);
    }

    /**
     * 获得几日前零点对象
     */
    public static <T extends Date> T getBeforeDayZero(int d, Class<T> type) {
        ZonedDateTime today = LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault());
        if (d != 0) {
            today = today.minusDays(d);
        }
        try {
            long millis = today.toInstant().toEpochMilli();
            Constructor<T> constructor = type.getConstructor(long.class);
            if (constructor != null) {
                T t = constructor.newInstance(millis);
                return t;
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }

    public static <T extends Date> T resetDayZero(long time, Class<T> type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        try {
            long millis = calendar.getTimeInMillis();
            Constructor<T> constructor = type.getConstructor(long.class);
            if (constructor != null) {
                T t = constructor.newInstance(millis);
                return t;
            }
        } catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException();
    }

    public static <T extends Date> T resetDayZero(T date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        date.setTime(calendar.getTimeInMillis());

        return date;
    }

    public static Date getBeforeDay(Date day, int n) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(day);
        ZonedDateTime datetime = ZonedDateTime.ofInstant(day.toInstant(), ZoneId.systemDefault());
        ZonedDateTime minusDays = datetime.minusDays(n);
        return Date.from(minusDays.toInstant());
    }

    // 01. java.util.Date --> java.time.LocalDateTime
    public static LocalDateTime UDateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    // 02. java.util.Date --> java.time.LocalDate
    public static LocalDate UDateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

    // 03. java.util.Date --> java.time.LocalTime
    public static LocalTime UDateToLocalTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalTime();
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    public static Date LocalDateTimeToUdate(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }


    // 05. java.time.LocalDate --> java.util.Date
    public static Date LocalDateToUdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    // 06. java.time.LocalTime --> java.util.Date
    public static Date LocalTimeToUdate(LocalDate localDate, LocalTime localTime) {
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * @Title: getFirstDayOfWeek
     * @Description 获取本周的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     * @Exception:
     */
    public static LocalDate getStartOrEndDayOfWeek(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        DayOfWeek week = today.getDayOfWeek();
        int value = week.getValue();
        if (isFirst) {
            resDate = today.minusDays(value - 1);
        } else {
            resDate = today.plusDays(7 - value);
        }
        return resDate;
    }

    /**
     * @Title: getStartOrEndDayOfMonth
     * @Description 获取本月的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     * @Exception:
     */
    public static LocalDate getStartOrEndDayOfMonth(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month month = today.getMonth();
        int length = month.length(today.isLeapYear());
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), month, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), month, length);
        }
        return resDate;
    }

    /**
     * @Title: getStartOrEndDayOfQuarter
     * @Description 获取本季度的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     * @Exception:
     */
    public static LocalDate getStartOrEndDayOfQuarter(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month month = today.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(today.isLeapYear()));
        }
        return resDate;
    }

    /**
     * @Title: getStartOrEndOfYear
     * @Description 获取本年的第一天或最后一天
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间]
     * @return: java.lang.String
     * @Exception:
     */
    public static LocalDate getStartOrEndDayOfYear(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), Month.JANUARY, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), Month.DECEMBER, Month.DECEMBER.length(today.isLeapYear()));
        }
        return resDate;
    }

    /**
     * @Title: getStartOrEndDay
     * @Description 获取某天的的开始时间或者结束时间  0点或者23:59:59
     * @Param: [today, isFirst: true 表示开始时间，false表示结束时间; isPlus 表示加 day 表示 数量]
     * @return: java.lang.String
     * @Exception:
     */
    public static Date getStartOrEndDay(LocalDate today, Boolean isFirst) {
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        if (isFirst) {
            LocalDateTime today_start = LocalDateTime.of(today, LocalTime.MIN);//当天零点

            return LocalDateTimeToUdate(today_start);
        } else {
            LocalDateTime today_end = LocalDateTime.of(today, LocalTime.MAX);//当天23点59分59秒
            return LocalDateTimeToUdate(today_end);
        }
    }

    /**
     * @param dateString 必须为yyyy-MM-dd 日期格式的时间
     * @param timeType   时间类型：day、week、month、year
     * @param isFirst    true表示开始时间，false表示结束时间
     * @return
     */
    public static Date getStartDateOrEndDateByTimeType(String dateString, String timeType, boolean isFirst) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //天
        if (Objects.equals(TimeIntervalEnum.Day.getCode(), timeType)) {
            return getStartOrEndDay(localDate, isFirst);
        }
        //周
        if (Objects.equals(TimeIntervalEnum.Week.getCode(), timeType)) {
            return getStartOrEndDay(getStartOrEndDayOfWeek(localDate, isFirst), isFirst);
        }
        //月
        if (Objects.equals(TimeIntervalEnum.Month.getCode(), timeType)) {
            return getStartOrEndDay(getStartOrEndDayOfMonth(localDate, isFirst), isFirst);
        }
        //年
        if (Objects.equals(TimeIntervalEnum.Year.getCode(), timeType)) {
            return getStartOrEndDay(getStartOrEndDayOfYear(localDate, isFirst), isFirst);
        }
        return null;
    }

    /**
     * 获取当前时间的前一天、前一周、前一个月、前一年的日期
     * @param dateString 必须为yyyy-MM-dd 日期格式的时间
     * @param timeType   时间类型：day、week、month、year
     * @return
     */
    public static String getLastDateByLocalDate(String dateString, String timeType, int count) {
        LocalDate localDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //天
        if (Objects.equals(TimeIntervalEnum.Day.getCode(), timeType)) {
            // 前一天
            return localDate.plusDays(count).toString();
        }
        //周
        if (Objects.equals(TimeIntervalEnum.Week.getCode(), timeType)) {
            // 前一个周
            return localDate.plusWeeks(count).toString();
        }
        //月
        if (Objects.equals(TimeIntervalEnum.Month.getCode(), timeType)) {
            // 前一个月
            return localDate.plusMonths(count).toString();
        }
        //年
        if (Objects.equals(TimeIntervalEnum.Year.getCode(), timeType)) {
            // 前一年
            return localDate.plusYears(count).toString();
        }
        return null;
    }


    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        try {
            Instant instant = Instant.ofEpochMilli(timestamp);
            ZoneId zone = ZoneId.systemDefault();
            return LocalDateTime.ofInstant(instant, zone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
