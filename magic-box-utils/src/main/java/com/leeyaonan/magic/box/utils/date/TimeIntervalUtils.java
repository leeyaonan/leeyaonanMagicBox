package com.leeyaonan.magic.box.utils.date;

import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.leeyaonan.magic.box.common.NumberConstant.*;

/**
 * @author: leeyaonan
 * @date: 2023-11-06 9:20
 * @desc: 时间区间比较
 */
public class TimeIntervalUtils {

    public static boolean hasIntersection(Date[][] dateArray) {
        long[][] longArray = new long[dateArray.length][];

        for (int i = 0; i < dateArray.length; i++) {
            longArray[i] = new long[dateArray[i].length];
            for (int j = 0; j < dateArray[i].length; j++) {
                longArray[i][j] = dateArray[i][j].getTime();
            }
        }

        return IntervalComparatorUtils.hasIntersection(longArray);
    }

    /**
     * 按指定分钟数作为间隔对一段时间切片，并返回每一段时间的数组
     *
     * @param start
     * @param end
     * @param intervalInMinutes
     * @return
     */
    public static List<Date[]> sliceDates(Date start, Date end, int intervalInMinutes) {
        List<Date[]> result = new ArrayList<>();
        long timeDifference = end.getTime() - start.getTime();
        int sliceCount = (int) (timeDifference / (intervalInMinutes * Number_1000 * Number_60));

        for (int i = 0; i < sliceCount; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start);
            calendar.add(Calendar.MINUTE, i * Number_30);
            Date sliceStart = calendar.getTime();

            calendar.setTime(start);
            calendar.add(Calendar.MINUTE, (i + 1) * Number_30);
            Date sliceEnd = calendar.getTime();

            result.add(new Date[]{sliceStart, sliceEnd});
        }

        return result;
    }

    /**
     * 将HH:mm格式的时间区间拆分为多个
     *
     * @param time1
     * @param time2
     * @return
     */
    public static List<String[]> sliceTimes(String time1, String time2, int minutesToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = LocalTime.parse(time1, formatter);
        LocalTime endTime = LocalTime.parse(time2, formatter);
        List<String[]> result = new ArrayList<>();

        while (!startTime.isAfter(endTime)) {
            String[] sliceTimes = new String[2];
            sliceTimes[0] = startTime.format(formatter);
            sliceTimes[1] = startTime.plusMinutes(minutesToAdd).format(formatter);
            result.add(sliceTimes);
            startTime = startTime.plusMinutes(minutesToAdd);
            if (startTime.equals(endTime) || startTime.isAfter(endTime)) {
                break;
            }
        }

        return result;
    }

    /**
     * 英文星期转为数字星期（从周日开始）
     */
    public static Map<String, DayOfWeek> WEEKDAY_NUMBER_MAP = new HashMap<>();

    static {
        WEEKDAY_NUMBER_MAP.put("SUNDAY", DayOfWeek.SUNDAY);
        WEEKDAY_NUMBER_MAP.put("MONDAY", DayOfWeek.MONDAY);
        WEEKDAY_NUMBER_MAP.put("TUESDAY", DayOfWeek.TUESDAY);
        WEEKDAY_NUMBER_MAP.put("WEDNESDAY", DayOfWeek.WEDNESDAY);
        WEEKDAY_NUMBER_MAP.put("THURSDAY", DayOfWeek.THURSDAY);
        WEEKDAY_NUMBER_MAP.put("FRIDAY", DayOfWeek.FRIDAY);
        WEEKDAY_NUMBER_MAP.put("SATURDAY", DayOfWeek.SATURDAY);
    }

    /**
     * 获取从指定日期开始未来x天内的星期几
     *
     * @param date
     * @param futureDays
     * @param weekday
     * @return
     */
    public static List<LocalDate> getNextPeriodWeekdays(LocalDate date, int futureDays, String weekday) {
        List<LocalDate> weekdayList = new ArrayList<>();
        LocalDate nextWeekday = date.with(WEEKDAY_NUMBER_MAP.get(weekday));
        if (nextWeekday.isBefore(date)) {
            nextWeekday = nextWeekday.plusWeeks(1);
        }

        LocalDate maxDate = date.plusDays(futureDays);
        while (nextWeekday.isBefore(maxDate) || nextWeekday.equals(maxDate)) {
            weekdayList.add(nextWeekday);
            nextWeekday = nextWeekday.plusWeeks(1);
        }

        return weekdayList;
    }

    /**
     * 获取从指定日期开始未来x天内的几号
     *
     * @param date
     * @param futureDays
     * @param monthday
     * @return
     */
    public static List<LocalDate> getNextPeriodMonthdays(LocalDate date, int futureDays, int monthday) {
        List<LocalDate> monthdayList = new ArrayList<>();
        LocalDate maxDate = date.plusDays(futureDays);
        // 遍历未来的天数，比较每月几号是否相符
        for (int i = 0; i <= futureDays; i++) {
            LocalDate temp = date.plusDays(i);
            int dayOfMonth = temp.getDayOfMonth();
            if (dayOfMonth != monthday) {
                continue;
            }
            if (temp.isBefore(maxDate) || temp.equals(maxDate)) {
                monthdayList.add(temp);
            }
        }
        return monthdayList;
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public long daysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }


}
