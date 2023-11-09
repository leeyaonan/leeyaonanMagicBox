package com.leeyaonan.magic.box.utils.number;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: leeyaonan
 * @date: 2023-11-09 11:35
 * @desc:
 */
public class NumberCombinationUtils {

    /**
     * 从一组数字中找出求和后最接近（大于等于）目标数字的组合
     *
     * @param numbers 一组数字
     * @param target  目标值
     * @return 组合
     */
    public static List<List<Double>> findClosestCombination(List<Double> numbers, double target) {
        List<List<Double>> allCombinations = new ArrayList<>();
        findCombinations(numbers, new ArrayList<>(), 0, target, allCombinations);

        List<List<Double>> closestCombinations = new ArrayList<>();
        double minDifference = Double.MAX_VALUE;

        for (List<Double> combination : allCombinations) {
            double sum = combination.stream().mapToDouble(Double::doubleValue).sum();
            double difference = Math.abs(target - sum);

            if (difference < minDifference) {
                minDifference = difference;
                closestCombinations.clear();
                closestCombinations.add(combination);
            } else if (difference == minDifference) {
                closestCombinations.add(combination);
            }
        }

        return closestCombinations;
    }

    /**
     * 这段代码是一个递归函数，用于生成给定数字列表中所有可能的数字组合。函数名为`findCombinations`，它接受5个参数：
     * <p>
     * 1. `numbers`：一个包含数字的列表，表示要进行组合的数字集合。
     * 2. `currentCombination`：一个空列表，用于存储当前正在构建的组合。
     * 3. `startIndex`：一个整数，表示从哪个索引开始遍历数字列表。在每次递归调用时，该值会递增1，以便跳过已经处理过的数字。
     * 4. `target`：一个整数，表示目标和。当当前组合的和大于等于该值时，将该组合添加到结果列表中。
     * 5. `allCombinations`：一个空列表，用于存储所有满足条件的组合。
     * <p>
     * 函数的主要逻辑如下：
     * <p>
     * 1. 首先检查当前组合的和是否大于等于目标和。如果是，则将当前组合添加到结果列表中，并返回。
     * 2. 然后，使用一个循环遍历数字列表中的每个数字。在每次迭代中，将当前数字添加到当前组合中，并递归调用`findCombinations`函数，将下一个索引作为起始索引。
     * 3. 在递归调用返回后，将刚刚添加的数字从当前组合中移除，以便尝试其他可能的组合。
     * 4. 最后，当所有数字都已经被处理过时，函数返回结果列表。
     * <p>
     * 通过调用这个函数并传入相应的参数，可以得到给定数字列表中所有可能的数字组合，这些组合的和大于等于指定的目标和。
     *
     * @param numbers            一个包含数字的列表，表示要进行组合的数字集合。
     * @param currentCombination 一个空列表，用于存储当前正在构建的组合。
     * @param startIndex         一个整数，表示从哪个索引开始遍历数字列表。在每次递归调用时，该值会递增1，以便跳过已经处理过的数字。
     * @param target             一个整数，表示目标和。当当前组合的和大于等于该值时，将该组合添加到结果列表中。
     * @param allCombinations    一个空列表，用于存储所有满足条件的组合。
     */
    private static void findCombinations(List<Double> numbers, List<Double> currentCombination, int startIndex, double target, List<List<Double>> allCombinations) {
        if (currentCombination.stream().mapToDouble(Double::doubleValue).sum() >= target) {
            allCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = startIndex; i < numbers.size(); i++) {
            currentCombination.add(numbers.get(i));
            findCombinations(numbers, currentCombination, i + 1, target, allCombinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
