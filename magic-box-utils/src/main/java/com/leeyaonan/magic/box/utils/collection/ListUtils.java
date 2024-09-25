package com.leeyaonan.magic.box.utils.collection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ListUtils {

    /**
     * 对列表的指定字段进行排序，注意：必须实现get方法
     * @param list
     * @param field
     * @param ascending
     */
    public static List sortByFieldName(List list, String field, boolean ascending) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        List sortedList = new ArrayList(list);
        field = field.substring(0, 1).toUpperCase() + field.substring(1);
        String finalField = field;

        sortedList.sort((o1, o2) -> {
            try {
                Method getter1 = o1.getClass().getDeclaredMethod("get" + finalField);
                Object val1 = getter1.invoke(o1);
                Method getter2 = o2.getClass().getDeclaredMethod("get" + finalField);
                Object val2 = getter2.invoke(o2);

                if (val1 == null) {
                    if (val2 == null) {
                        return 0;
                    } else {
                        return ascending ? -1 : 1;
                    }
                }
                if (val2 == null) {
                    return ascending ? 1 : -1;
                }

                if (val1 instanceof Comparable && val2 instanceof Comparable) {
                    Comparable comp1 = (Comparable) val1;
                    Comparable comp2 = (Comparable) val2;
                    int result = comp1.compareTo(comp2);
                    return ascending ? result : -result;
                }

                // 如果不是可比较类型，则返回默认值
                return 0;
            } catch (Exception e) {
                log.warn("sort error, list={}, field={}, ascending={}", list, finalField, ascending, e);
                return 0;
            }
        });
        return sortedList;
    }

}
