package com.leeyaonan.magic.box.utils.number;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 整数拆分工具类
 */
public class IntegerPartitioningUtils {

    /**
     * 递归实现
     * @param n
     * @return
     */
    public static List<List<Integer>> partitions1(int n) {
        return partitionsHelper1(n, n);
    }

    private static List<List<Integer>> partitionsHelper1(int n, int maxPart) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        for (int part = 1; part <= Math.min(maxPart, n); part++) {
            List<List<Integer>> subPartitions = partitionsHelper1(n - part, part);

            for (List<Integer> subPartition : subPartitions) {
                List<Integer> newPartition = new ArrayList<>();
                newPartition.add(part);
                newPartition.addAll(subPartition);
                result.add(newPartition);
            }
        }

        return result;
    }

    /**
     * 迭代实现
     * @param n
     * @return
     */
    public static List<List<Integer>> partitions2(int n) {
        List<List<Integer>> result = new ArrayList<>();
        partitionHelper2(result, new ArrayList<>(), n, n);
        return result;
    }

    private static void partitionHelper2(List<List<Integer>> result, List<Integer> current, int n, int maxPart) {
        if (n == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int part = 1; part <= Math.min(maxPart, n); part++) {
            current.add(part);
            partitionHelper2(result, current, n - part, part);
            current.remove(current.size() - 1);
        }
    }

    /**
     * 动态规划实现
     * @param n
     * @return
     */
    private static Map<Integer, List<List<Integer>>> memo = new HashMap<>();

    public static List<List<Integer>> partitions3(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        List<List<Integer>> result = partitionsHelper3(n, n);
        memo.put(n, result);
        return result;
    }

    private static List<List<Integer>> partitionsHelper3(int n, int maxPart) {
        List<List<Integer>> result = new ArrayList<>();

        if (n == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        for (int part = 1; part <= Math.min(maxPart, n); part++) {
            List<List<Integer>> subPartitions = partitionsHelper3(n - part, part);
            for (List<Integer> subPartition : subPartitions) {
                List<Integer> newPartition = new ArrayList<>();
                newPartition.add(part);
                newPartition.addAll(subPartition);
                result.add(newPartition);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(partitions1(5));
        System.out.println(partitions2(5));
        System.out.println(partitions3(5));
    }


}
