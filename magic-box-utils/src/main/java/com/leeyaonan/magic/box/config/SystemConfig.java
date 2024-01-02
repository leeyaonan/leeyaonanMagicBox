package com.leeyaonan.magic.box.config;

/**
 * @author: leeyaonan
 * @date: 2024-01-02 15:17
 * @desc:
 */
public class SystemConfig {

    /**
     * 根路径（按Linux标准，以/作为分隔符）
     *
     * @return
     */
    public static String rootPath() {
        return System.getProperty("user.dir").replace("\\", "/");
    }

    /**
     * 测试图片文件路径
     *
     * @return
     */
    public static String picPath() {
        return rootPath() + "/src/test/resources/pic/";
    }

    /**
     * 输出文件路径
     *
     * @return
     */
    public static String outputPath() {
        return rootPath() + "/src/test/resources/output/";
    }
}
