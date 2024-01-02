package com.leeyaonan.magic.box.utils;

import com.google.zxing.WriterException;
import com.leeyaonan.magic.box.config.SystemConfig;
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
        String content = "https://www.baidu.com";
        String filePath = SystemConfig.outputPath() + "QrCode-baidu.png";

        try {
            QrCodeUtils.generateQRCodeImage(content, filePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void generateQRCodeWithText() {
        String content = "https://www.baidu.com";
        String text = "百度一下";
        String filePath = SystemConfig.outputPath() + "QrCode-baidu_text.png";

        try {
            QrCodeUtils.generateQRCodeWithText(content, text, filePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void generateQRCodeWithLogo() {
        String content = "https://www.baidu.com";
        String logoPath = SystemConfig.picPath() + "baidu_logo.png";
        String filePath = SystemConfig.outputPath() + "QrCode-baidu_logo.png";

        try {
            QrCodeUtils.generateQRCodeWithLogo(content, logoPath, filePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testGenerateQRCodeImageWithLogoAndText() {
        String content = "https://www.baidu.com";
        String logoPath = SystemConfig.picPath() + "baidu_logo.png";
        String text = "百度一下";
        String filePath = SystemConfig.outputPath() + "QrCode-baidu_logoAndText.png";

        try {
            QrCodeUtils.generateQRCodeImage(content, logoPath, text, filePath);
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
