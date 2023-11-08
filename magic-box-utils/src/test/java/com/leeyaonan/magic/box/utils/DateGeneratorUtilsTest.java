package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.date.DateGeneratorUtils;
import com.leeyaonan.magic.box.utils.date.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * @author: leeyaonan
 * @date: 2023-11-08 9:47
 * @desc:
 */
public class DateGeneratorUtilsTest {

    @Test
    public void testGetNearestHalfHour(){
        Date date = DateGeneratorUtils.of(2023, 11, 8, 1, 5, 0, 0);
        System.out.println("入参时间： " + DateUtils.standardLongFormatString(date));
        Date nearestHalfHour = DateGeneratorUtils.getNearestHalfHour(date);
        System.out.println("最近的半小时： " + DateUtils.standardLongFormatString(nearestHalfHour));
    }
}
