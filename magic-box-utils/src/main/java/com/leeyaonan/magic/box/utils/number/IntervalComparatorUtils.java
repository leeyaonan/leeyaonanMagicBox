package com.leeyaonan.magic.box.utils.number;

import java.util.Arrays;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 14:03
 * @desc: 数字区间比较
 */
public class IntervalComparatorUtils {

    /**
     * 比较多个整数区间是否存在交集
     * 例如：
     * int[] interval1 = {1,5}
     * int[] interval2 = {4,8}
     * int[] interval3 = {9,10}
     *
     * 因为interval1和interval2存在交集，调用此方法会返回true
     * @param intervals
     * @return
     */
    public static boolean hasIntersection(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (starts[i + 1] <= ends[i]) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasIntersection(long[][] intervals) {
        long[] starts = new long[intervals.length];
        long[] ends = new long[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (starts[i + 1] <= ends[i]) {
                return true;
            }
        }

        return false;
    }
}
