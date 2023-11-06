package com.leeyaonan.magic.box.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author: leeyaonan
 * @date: 2023-11-06 9:35
 * @desc:
 */
public class DateUtils {

    public static final String DATE_FORMAT_STANDARD_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_STANDARD_SHORT = "yyyy-MM-dd";

    /**
     * 按照指定格式解析日期字符串
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date format(String dateString, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 标准日期解析，pattern=yyyy-MM-dd HH:mm:ss
     *
     * @param dateString
     * @return
     */
    public static Date standardLongFormat(String dateString) {
        return format(dateString, DATE_FORMAT_STANDARD_LONG);
    }

    /**
     * Date转换成LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


}
