package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.number.NumberCombinationUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author: leeyaonan
 * @date: 2023-11-09 11:36
 * @desc:
 */
public class NumberCombinationUtilsTest {

    @Test
    public void testFindClosestCombination() {
//        List<Double> numbers1 = Arrays.asList(1.1d, 2d, 1.2d, 3.9d, 4.3d);
//        List<List<Double>> result1 = NumberCombinationUtils.findClosestCombination(numbers1, 10);
//        System.out.println(result1);
//
//        List<Double> numbers2 = Arrays.asList(1.1d, 2d, 1.2d, 3.9d);
//        List<List<Double>> result2 = NumberCombinationUtils.findClosestCombination(numbers2, 10);
//        System.out.println(result2);

        List<Double> numbers2 = Arrays.asList(198d, 138.8d, 330d, 202d, 227d, 232d, 194.8d, 27.8d, 100.5d, 185d, 205d);
        List<List<Double>> result2 = NumberCombinationUtils.findClosestCombination(numbers2, 1350);
        System.out.println(result2);
    }
}
