package com.leeyaonan.magic.box.utils.number;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 14:03
 * @desc: 区间（数字、时间）处理工具
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

    public static List<TimeInterval> mergeIntervals(List<TimeInterval> intervals) {
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }

        Collections.sort(intervals);
        List<TimeInterval> merged = new ArrayList<>();
        TimeInterval current = intervals.get(0);

        for (int i = 1; i < intervals.size(); i++) {
            TimeInterval next = intervals.get(i);
            if (current.getEnd().compareTo(next.getStart()) >= 0) {
                current.end = new Date(Math.max(current.getEnd().getTime(), next.getEnd().getTime()));
            } else {
                merged.add(current);
                current = next;
            }
        }

        merged.add(current);
        return merged;
    }

    /**
     * 用于时间区间比较的封装工具类
     */
    public static class TimeInterval implements Comparable<TimeInterval> {
        /**
         * 开始时间
         */
        private Date start;
        /**
         * 结束时间
         */
        private Date end;

        public TimeInterval(String start, String end) throws ParseException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.start = sdf.parse(start);
            this.end = sdf.parse(end);
        }

        public TimeInterval(Date start, Date end) {
            this.start = start;
            this.end = end;
        }

        public Date getStart() {
            return start;
        }

        public Date getEnd() {
            return end;
        }

        @Override
        public int compareTo(TimeInterval other) {
            return this.start.compareTo(other.start);
        }

        @Override
        public String toString() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "[" + sdf.format(start) + ", " + sdf.format(end) + "]";
        }
    }
}
