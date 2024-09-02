package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.pdf.Pdf2JpgUtils;
import org.junit.Test;

public class Pdf2JpgUtilsTest {

    /**
     * 测试pdf转jpg
     */
    @Test
    public void pdf2Jpg() {
        String pdfFilePath = "src/test/resources/pdf/24070082.pdf";
        String outputFolder = "src/test/resources/pdf/jpg/";
        Pdf2JpgUtils.pdf2Jpg(pdfFilePath, outputFolder);
    }

    /**
     * 批量测试pdf转jpg
     */
    @Test
    public void batchPdf2Jpg() {
        String pdfFolderPath = "src/test/resources/pdf/";
        String outputFolder = "src/test/resources/pdf/jpg/";
        Pdf2JpgUtils.batchPdf2Jpg(pdfFolderPath, outputFolder);
    }
}
