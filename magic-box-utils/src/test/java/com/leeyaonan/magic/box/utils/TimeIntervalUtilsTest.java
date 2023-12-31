package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.date.DateGeneratorUtils;
import com.leeyaonan.magic.box.utils.date.DateUtils;
import com.leeyaonan.magic.box.utils.date.TimeIntervalUtils;
import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: leeyaonan
 * @date: 2023-11-06 9:33
 * @desc:
 */
public class TimeIntervalUtilsTest {

    @Test
    public void testHasIntersection() {

        Date[][] dateArray = {{}, {}};

    }

    @Test
    public void testSliceDates() {
        Date date1 = DateGeneratorUtils.of(2023, 11, 8, 0, 0, 0, 0);
        Date date2 = DateGeneratorUtils.of(2023, 11, 8, 1, 0, 0, 0);

        List<Date[]> dates = com.leeyaonan.magic.box.utils.date.TimeIntervalUtils.sliceDates(date1, date2, 30);

        for (Date[] dateArr : dates) {
            System.out.println("1:" + DateUtils.standardLongFormatString(dateArr[0]));
            System.out.println("2:" + DateUtils.standardLongFormatString(dateArr[1]));
        }
    }

    @Test
    public void testMergeIntervals() throws ParseException {
        List<IntervalComparatorUtils.TimeInterval> intervals = Arrays.asList(
                new IntervalComparatorUtils.TimeInterval("2022-01-01 09:00:00", "2022-01-01 10:00:00"),
                new IntervalComparatorUtils.TimeInterval("2022-01-01 10:00:00", "2022-01-01 11:00:00"),
                new IntervalComparatorUtils.TimeInterval("2022-01-01 13:00:00", "2022-01-01 14:00:00")
        );

        List<IntervalComparatorUtils.TimeInterval> mergedIntervals = IntervalComparatorUtils.mergeIntervals(intervals);
        System.out.println(mergedIntervals);
    }

    @Test
    public void testSliceTime() {
        String time1 = "09:00";
        String time2 = "10:00";
        List<String[]> result = TimeIntervalUtils.sliceTimes(time1, time2, 30);
        for (String[] arr : result) {
            System.out.println("[" + arr[0] + "," + arr[1] + "]");
        }
    }

    @Test
    public void testGetNextPeriodWeekdays() {
        LocalDate startDate = LocalDate.of(2023, 11, 17);
        List<LocalDate> monday = TimeIntervalUtils.getNextPeriodWeekdays(startDate, 28, "FRIDAY");
        for (LocalDate date : monday) {
            System.out.println(date);
        }

        long daysBetween = ChronoUnit.DAYS.between(startDate, LocalDate.of(2023, 12, 15));
        System.out.println("两个日期之间的天数为： " + daysBetween);
    }

    @Test
    public void testGetNextPeriodMonthdays() {
        LocalDate startDate = LocalDate.of(2023, 11, 15);
        List<LocalDate> monday = TimeIntervalUtils.getNextPeriodMonthdays(startDate, 28, 16);
        for (LocalDate date : monday) {
            System.out.println(date);
            long daysBetween = ChronoUnit.DAYS.between(startDate, date);
            System.out.println("两个日期之间的天数为： " + daysBetween);
        }
        long daysBetween = ChronoUnit.DAYS.between(LocalDate.of(2023, 11, 15), LocalDate.of(2023, 12, 16));
        System.out.println("两个日期之间的天数为： " + daysBetween);
    }

    @Test
    public void testGetNextPeriodMonthdays2() {
        LocalDate startDate = LocalDate.of(2023, 11, 30);
        List<LocalDate> nextPeriodMonthdays = TimeIntervalUtils.getNextPeriodMonthdays(startDate, 28, 28);
        for (LocalDate nextDate : nextPeriodMonthdays) {
            System.out.println(nextDate);
        }
    }
}
