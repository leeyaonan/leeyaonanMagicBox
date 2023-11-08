package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.date.DateGeneratorUtils;
import com.leeyaonan.magic.box.utils.date.DateUtils;
import com.leeyaonan.magic.box.utils.date.TimeIntervalUtils;
import org.junit.Test;

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
        Date date1 = DateGeneratorUtils.of(2023, 11, 8, 0, 1, 0, 0);
        Date date2 = DateGeneratorUtils.of(2023, 11, 8, 1, 59, 0, 0);

        List<Date[]> dates = TimeIntervalUtils.sliceDates(date1, date2, 30);

        for (Date[] dateArr : dates) {
            System.out.println("1:" + DateUtils.standardLongFormatString(dateArr[0]));
            System.out.println("2:" + DateUtils.standardLongFormatString(dateArr[1]));
        }
    }
}
