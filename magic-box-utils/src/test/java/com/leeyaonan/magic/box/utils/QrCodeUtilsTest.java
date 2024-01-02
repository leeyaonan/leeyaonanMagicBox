package com.leeyaonan.magic.box.utils;

import com.google.zxing.WriterException;
import com.leeyaonan.magic.box.utils.qrCode.QrCodeUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: leeyaonan
 * @date: 2024-01-02 10:22
 * @desc: 二维码测试工具类
 */
public class QrCodeUtilsTest {

    @Test
    public void testGenerateQRCodeImage() {
        String qrCodeText = "https://www.baidu.com";
        int size = 300;
        String filename = "QrCode-baidu.png";
        String filePath = System.getProperty("user.dir") +  "\\output\\" + filename;

        try {
            QrCodeUtils.generateQRCodeImage(qrCodeText, size, filePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
