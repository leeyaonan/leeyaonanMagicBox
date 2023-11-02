package com.leeyaonan.magic.box.utils.list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 10:05
 * @desc:
 */
public class ListComparatorUtils {

    /**
     * 比较两个字符串列表，并找出各自不同的元素
     *
     * @param list1 列表1
     * @param list2 列表2
     * @return 每个列表中不同的元素
     */
    public static Map<String, List<String>> compareTwoStrList(List<String> list1, List<String> list2) {
        Map<String, List<String>> differentElements = new HashMap<>();
        differentElements.put("list1", list1.stream()
                .filter(element -> !list2.contains(element))
                .collect(Collectors.toList()));
        differentElements.put("list2", list2.stream()
                .filter(element -> !list1.contains(element))
                .collect(Collectors.toList()));
        return differentElements;
    }
}
