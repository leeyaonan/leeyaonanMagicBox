package com.leeyaonan.magic.box.utils.base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64工具类
 */
public class Base64Utils {

    /**
     * 对字符串进行安全的Base64编码。
     * 以下摘自腾讯云文档：
     * 在数据万象的处理操作中，有很多参数需要进行 URL 安全的 BASE64 编码，例如文字水印的文字内容、颜色、字体设置和图片水印的水印图链接。URL 安全的 BASE64 编码具体规则为：
     * 1. 将普通 BASE64 编码结果中的加号（+）替换成连接号（-）；
     * 2. 将编码结果中的正斜线（/）替换成下划线（_）；
     * 3. 将编码结果中的“=”去掉
     *
     * @param data 待编码的字符串
     * @return 自定义Base64编码后的字符串
     */
    public static String safeBase64Encode(String data) {
        // 将字符串转换为字节数组
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        // 使用Base64编码器进行编码
        String base64Encoded = Base64.getEncoder().encodeToString(dataBytes);

        // 替换字符
        base64Encoded = base64Encoded.replace("+", "-"); // 替换加号为连接号
        base64Encoded = base64Encoded.replace("/", "_"); // 替换正斜线为下划线
        base64Encoded = base64Encoded.replace("=", "");  // 去掉填充字符

        return base64Encoded;
    }

    public static void main(String[] args) {
        String originalData = "Hello, World!";
        String encodedData = safeBase64Encode(originalData);
        System.out.println("Encoded Data: " + encodedData); // SGVsbG8sIFdvcmxkIQ
    }
}
