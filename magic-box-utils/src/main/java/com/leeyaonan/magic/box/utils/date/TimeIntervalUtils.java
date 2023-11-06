package com.leeyaonan.magic.box.utils.date;

import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;

import java.util.Arrays;
import java.util.Date;

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

}
