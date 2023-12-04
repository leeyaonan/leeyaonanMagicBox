package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.str.RegexUtils;
import org.junit.Test;

/**
 * @author: leeyaonan
 * @date: 2023-12-04 16:22
 * @desc:
 */
public class RegexUtilsTest {

    @Test
    public void testPhoneMasking() {
        String phone = "19999999999";
        System.out.println(RegexUtils.phoneMasking(phone));
    }
}
