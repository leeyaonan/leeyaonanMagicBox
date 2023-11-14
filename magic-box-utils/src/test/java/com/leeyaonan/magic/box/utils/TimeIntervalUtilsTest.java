package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.date.DateGeneratorUtils;
import com.leeyaonan.magic.box.utils.date.DateUtils;
import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;
import org.junit.Test;

import java.text.ParseException;
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

}
