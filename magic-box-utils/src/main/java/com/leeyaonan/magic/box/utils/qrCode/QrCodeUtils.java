package com.leeyaonan.magic.box.utils.qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.logging.log4j.util.Strings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: leeyaonan
 * @date: 2024-01-02 10:19
 * @desc:
 */
public class QrCodeUtils {

    /**
     * 默认大小
     */
    private static final int defaultSize = 300;

    /**
     * 默认文件类型：png
     */
    private static final String defaultFileType = "PNG";

    /**
     * 默认字体：宋体
     */
    private static final String defaultFont = "宋体";

    /**
     * 默认字体颜色：黑色
     */
    private static final Color defaultFontColor = Color.BLACK;

    /**
     * 默认字体大小
     */
    private static final int defaultFontSize = 12;

    public static void generateQRCodeImage(String content, String filePath) throws WriterException, IOException {
        generateQRCodeImage(content, null, null, defaultSize, filePath);
    }

    public static void generateQRCodeImage(String content, String logoPath, String text, String filePath) throws WriterException, IOException {
        generateQRCodeImage(content, logoPath, text, defaultSize, filePath);
    }

    public static void generateQRCodeWithText(String content, String text, String filePath) throws WriterException, IOException {
        generateQRCodeImage(content, null, text, defaultSize, filePath);
    }

    public static void generateQRCodeWithLogo(String content, String logoPath, String filePath) throws WriterException, IOException {
        generateQRCodeImage(content, logoPath, null, defaultSize, filePath);
    }

    /**
     * 生成二维码（底层接口）
     *
     * @param content  二维码内容
     * @param logoPath logo图片路径
     * @param text     二维码底部说明文案
     * @param size     二维码大小
     * @param filePath 生成二维码路径
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCodeImage(String content, String logoPath, String text, int size, String filePath) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

        int[] topLeftOnBit = bitMatrix.getTopLeftOnBit();
        int padding = topLeftOnBit[0];

        // logo
        if (Strings.isNotBlank(logoPath)) {
            // 在内存创建图片缓冲区 这里设置画板的宽高和类型
            BufferedImage outImage = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);
            // 创建画布
            Graphics2D graphics = outImage.createGraphics();
            // 先绘制二维码主体
            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);

            // 再绘制logo
            BufferedImage logo = ImageIO.read(new File(logoPath));
            int matrixWidth = bitMatrix.getWidth() - (2 * padding);

            // 并根据需要调整大小
            int logoSize = Math.min(matrixWidth / 5, matrixWidth / 5);
            int x = ((matrixWidth - logoSize) / 2) + padding;
            int y = ((matrixWidth - logoSize) / 2) + padding;

            // 如果logo大于二维码，则调整其大小
            if (logo.getWidth() > logoSize || logo.getHeight() > logoSize) {
                logo = resizeImage(logo, logoSize, logoSize);
            }

            graphics.drawImage(logo, x, y, logoSize, logoSize, null);
            graphics.dispose();
            outImage.flush();
            image = outImage;
        }

        // 说明文案
        if (Strings.isNotBlank(text)) {
            // 在内存创建图片缓冲区 这里设置画板的宽高和类型
            BufferedImage outImage = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);
            // 创建画布
            Graphics2D graphics = outImage.createGraphics();
            // 先绘制二维码主体
            graphics.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
            // 再绘制说明文案
            graphics.setColor(defaultFontColor);
            graphics.setFont(new Font(defaultFont, Font.PLAIN, defaultFontSize)); // 字体、字型、字号
            // drawString(文字信息、x轴、y轴)方法根据参数设置文字的坐标轴 ，根据需要来进行调整
            int strWidth = graphics.getFontMetrics().stringWidth(text);
            // 底部空白区域居中
            graphics.drawString(text, (size - strWidth) / 2, size - (padding / 2));
            graphics.dispose();
            outImage.flush();
            image = outImage;
        }

        image.flush();

        // 保存二维码为文件
        ImageIO.write(image, defaultFileType, new File(filePath));
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(resultingImage, 0, 0, null);
        graphics2D.dispose();
        return outputImage;
    }
}
