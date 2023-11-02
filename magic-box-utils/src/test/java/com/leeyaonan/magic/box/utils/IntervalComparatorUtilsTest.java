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
        int[][] intervals = {{1, 5}, {4, 8}, {9, 10}};
        boolean hasIntersection = IntervalComparatorUtils.hasIntersection(intervals);
        Assert.assertTrue(hasIntersection);
    }
}
