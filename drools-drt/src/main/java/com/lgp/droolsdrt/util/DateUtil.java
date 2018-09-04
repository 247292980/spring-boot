package com.lgp.droolsdrt.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String FormatDateTime = "yyyy-MM-dd HH:mm:ss";
    public static final String FormatDate = "yyyy-MM-dd";
    public static final String FormatDateTimeNOSecond = "yyyy-MM-dd HH:mm";
    public static final String FormatDateTimeMsec = "yyyy-MM-dd HH:mm:ss.S";
    public static final String FormatDateTimeDate = "MM-dd";

    public static final String FormatDateYMD = "yyyy年MM月dd日";

    public static final String GeneralFormatDateTime = "y-M-d H:m:s";
    public static final String GeneralFormatDate = "y-M-d";
    public static final String GeneralSlashFormatDateTime = "y/M/d H:m:s";
    public static final String GeneralSlashFormatDate = "y/M/d";


    private DateUtil() {
    }

    /**
     * 取当前时间，格式 2015-12-02 16:53:51.0452503
     *
     * @return
     */
    public static String getNow() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
        return sf.format(new Date());
    }

    public static Date toDate(String source) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Date toDate(String source, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 字符串转日期
     *
     * @param source
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date stringToDateFormat(String source, String format) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            return sf.parse(source);
        } catch (ParseException e) {
            logger.error("格式化异常", e);
        }
        return null;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToStringFormat(Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(date);
    }

    public static String localDateToStringFormat(LocalDate localDate, String format) {
        if (localDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return formatter.format(localDate);
        }
        return "";
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        return Date.from(zdt.toInstant());
    }

    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }

        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay().atZone(zoneId);

        return Date.from(zdt.toInstant());
    }


    public static String localDateTimeToStringFormat(LocalDateTime localDateTime, String format) {
        if (localDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return formatter.format(localDateTime);
        }
        return "";
    }


    /**
     * 对应数据库函数[f_GetRepaymentAdvance_Date]
     * 指定时间=date+months+1day
     *
     * @param date
     * @param months
     * @return
     */
    public static Date nextFewMonth(Date date, Integer months) {
        Objects.requireNonNull(date, "date is null");
        Objects.requireNonNull(months, "months is null");
        return 0 == months ? date : Date.from(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusMonths(1).plusDays(1).plusMonths((long) (months - 1)).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 对应数据库函数[f_GetMaxRepayment_Date]
     * 指定时间=date+months+1day
     *
     * @param date
     * @param months
     * @param repaymentType
     * @return
     */
    public static Date maxRepaymentDate(Date date, Integer months, Integer repaymentType) {
        if (repaymentType == 1) {
            Objects.requireNonNull(date, "date is null");
            Objects.requireNonNull(months, "months is null");
            return Date.from(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).plusMonths(months).plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        } else {
            return nextFewMonth(date, months);
        }
    }

    /**
     * 计算两个日期的时间间隔,返回xx天xx小时xx分钟xx秒
     *
     * @param d1 第一个日期和时间
     * @param d2 第二个日期和时间
     * @return
     */
    public static String diff(LocalDateTime d1, LocalDateTime d2) {
        long begin = Timestamp.valueOf(d1).getTime();
        long end = Timestamp.valueOf(d2).getTime();
        long between = (end - begin) / 1000;//除以1000是为了转换成秒
        long day1 = between / (24 * 3600);
        long hour1 = between % (24 * 3600) / 3600;
        long minute1 = between % 3600 / 60;
        long second1 = between % 60;
        return day1 + "天" + hour1 + "小时" + minute1 + "分" + second1 + "秒";
    }

    /**
     * 判断日期是否为今天
     *
     * @param localDateTime 要判断的日期
     */
    public static boolean isToday(LocalDateTime localDateTime) {
        LocalDate date = LocalDate.now();//今天日期
        String d1 = localDateTime.getYear() + "" + localDateTime.getMonthValue() + "" + localDateTime.getDayOfMonth();
        String d2 = date.getYear() + "" + date.getMonthValue() + "" + date.getDayOfMonth();
        return d1.equals(d2);
    }

    /**
     * 获取当前日期的前{month}月的年月
     *
     * @param month
     * @return
     */
    public static Date getDateByMonth(Integer month) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -month);
        return c.getTime();
    }


}
