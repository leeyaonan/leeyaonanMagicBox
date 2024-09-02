package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.file.TextFileUtils;
import com.leeyaonan.magic.box.utils.number.NumberCombinationUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: leeyaonan
 * @date: 2023-11-09 11:36
 * @desc:
 */
public class NumberCombinationUtilsTest {

    @Test
    public void testFindClosestCombination() {
        // 读取文件，按行解析转为double类型
        String filePath = "src/test/resources/testFindClosestCombination.txt";
        List<Double> numbers = TextFileUtils.readTextFileToList(filePath, true).stream().map(Double::parseDouble).collect(Collectors.toList());
        List<List<Double>> result1 = NumberCombinationUtils.findClosestCombination(numbers, 10);
        System.out.println(result1);
    }
}
