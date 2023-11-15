package com.leeyaonan.magic.box.utils.date;

import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

}
