package com.leeyaonan.magic.box.utils.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author: leeyaonan
 * @date: 2023-11-06 9:45
 * @desc:
 */
public class DateGeneratorUtils {

    /**
     * 类似LocalDateTime的of方法
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    public static Date of(int year, int month, int dayOfMonth) {
        return of(year, month, dayOfMonth, 0, 0, 0, 0);
    }

    /**
     * 类似LocalDateTime的of方法
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @param hour
     * @param minute
     * @param second
     * @param nanoOfSecond
     * @return
     */
    public static Date of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1); // 月份从0开始
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, nanoOfSecond / 1_000_000);
        return calendar.getTime();
    }

    /**
     * 获取指定时间向后最近的半小时时间，例如：8:05，最近的时间是8:30
     *
     * @param date
     * @return
     */
    public static Date getNearestHalfHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        if (minute < 30) {
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
        } else {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.add(Calendar.HOUR_OF_DAY, 1);
        }

        return calendar.getTime();
    }
}
