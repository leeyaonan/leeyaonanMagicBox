package com.leeyaonan.magic.box.config;

/**
 * @author: leeyaonan
 * @date: 2024-01-02 15:17
 * @desc:
 */
public class SystemConfig {

    /**
     * 测试图片文件路径
     *
     * @return
     */
    public static String picPath() {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\pic\\";
    }

    /**
     * 输出文件路径
     *
     * @return
     */
    public static String outputPath() {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\output\\";
    }
}
