package com.leeyaonan.magic.box.utils.pdf;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
public class Pdf2JpgUtils {

    /**
     * 单个pdf文件转换jpg
     *
     * @param pdfFilePath  pdf文件路径
     * @param outputFolder 输出文件夹
     */
    public static void pdf2Jpg(String pdfFilePath, String outputFolder) {
        log.info("Pdf2JpgUtils.pdf2Jpg,processing,pdfFilePath={},outputFolder={}", pdfFilePath, outputFolder);
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            // 提取PDF文件名
            String pdfFileName = new File(pdfFilePath).getName();
            String baseName = pdfFileName.substring(0, pdfFileName.lastIndexOf('.'));

            PDFRenderer renderer = new PDFRenderer(document);
            int numberOfPages = document.getNumberOfPages();

            for (int i = 0; i < numberOfPages; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300); // 可以调整DPI
                // 构造输出文件名
                String outputFileName = baseName + "_page_" + (i + 1) + ".jpg";
                ImageIO.write(image, "jpg", new File(outputFolder + outputFileName));
            }
        } catch (IOException e) {
            log.error("Pdf2JpgUtils.pdf2Jpg,processError,pdfFilePath={},outputFolder={}", pdfFilePath, outputFolder, e);
        }
    }

    /**
     * 批量转换文件夹内的PDF文件为JPG
     *
     * @param inputFolder  输入文件夹路径
     * @param outputFolder 输出文件夹路径
     */
    public static void batchPdf2Jpg(String inputFolder, String outputFolder) {
        log.info("Pdf2JpgUtils.batchPdf2Jpg,processing,inputFolder={},outputFolder={}", inputFolder, outputFolder);
        File folder = new File(inputFolder);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("输入路径不是一个有效的文件夹: " + inputFolder);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));

        if (files == null || files.length == 0) {
            System.out.println("文件夹内没有PDF文件: " + inputFolder);
            return;
        }

        for (File file : files) {
            String pdfFilePath = file.getAbsolutePath();
            pdf2Jpg(pdfFilePath, outputFolder);
        }
    }
}
