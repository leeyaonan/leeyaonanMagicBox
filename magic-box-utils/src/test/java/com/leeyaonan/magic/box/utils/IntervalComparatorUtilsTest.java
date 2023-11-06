package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.number.IntervalComparatorUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 15:42
 * @desc:
 */
public class IntervalComparatorUtilsTest {

    @Test
    public void testHasIntersection() {
        int[][] intervals1 = {{1, 5}, {4, 8}, {9, 10}};
        int[][] intervals2 = {{4, 8}, {9, 10}};
        int[][] intervals3 = {{1, 10}, {2, 9}};
        boolean hasIntersection1 = IntervalComparatorUtils.hasIntersection(intervals1);
        boolean hasIntersection2 = IntervalComparatorUtils.hasIntersection(intervals2);
        boolean hasIntersection3 = IntervalComparatorUtils.hasIntersection(intervals3);

        System.out.println(hasIntersection1);
        System.out.println(hasIntersection2);
        System.out.println(hasIntersection3);

        Assert.assertTrue(hasIntersection1);
        Assert.assertFalse(hasIntersection2);
        Assert.assertTrue(hasIntersection3);
    }
}
