package com.leeyaonan.magic.box.utils.str;

/**
 * @author: leeyaonan
 * @date: 2023-12-04 16:19
 * @desc:
 */
public class RegexUtils {

    /**
     * 手机号脱敏
     * 这段代码中，maskPhoneNumber方法使用了Java的正则表达式功能。正则表达式是一种用于匹配字符串的模式，可以用于文本搜索、替换和提取等操作。
     *
     * 在这段代码中，正则表达式(\\d{3})\\d{4}(\\d{4})用于匹配手机号。其中，\\d表示一个数字字符，{3}表示前面的数字字符重复3次，\\d{4}表示前面的数字字符重复4次，()表示捕获组，用于提取匹配的内容。
     *
     * 因此，正则表达式(\\d{3})\\d{4}(\\d{4})的含义是：匹配一个由3个数字字符、4个数字字符和4个数字字符组成的字符串，并将中间的4个数字字符捕获为一个组。
     *
     * 在maskPhoneNumber方法中，使用replaceAll方法将匹配到的组替换为星号()。具体来说，$1表示第一个捕获组的内容，即3个数字字符，$2表示第二个捕获组的内容，即4个数字字符，$3表示第三个捕获组的内容，即4个数字字符。因此，$1****$3表示将中间的4个数字字符替换为星号()。
     * @param phoneNumber
     * @return
     */
    public static String phoneMasking(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 11) {
            throw new IllegalArgumentException("手机号格式不正确");
        }
        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
