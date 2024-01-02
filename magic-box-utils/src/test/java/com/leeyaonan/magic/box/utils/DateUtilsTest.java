package com.leeyaonan.magic.box.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: leeyaonan
 * @date: 2023-11-08 9:47
 * @desc:
 */
public class DateUtilsTest {

    @Test
    public void testSunday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time);
        calendar1.set(Calendar.DAY_OF_YEAR, calendar1.get(Calendar.DAY_OF_YEAR) + 7);
        System.out.println(calendar1.getTime());
    }

    @Test
    public void test() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(i);
        }
        System.out.println(integers.subList(0, 5));
        System.out.println(integers.subList(5, integers.size()));
    }
}
