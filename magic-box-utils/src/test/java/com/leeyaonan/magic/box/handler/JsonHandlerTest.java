package com.leeyaonan.magic.box.handler;

import org.junit.Test;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 11:06
 * @desc:
 */
public class JsonHandlerTest {

    @Test
    public void testJsonArray2Excel() {
        String jsonFilePath = "src/test/resources/testJsonArray2Excel.json";
        String excelFileName = "src/test/resources/output/testJsonArray2Excel.xlsx";

        JsonHandler.jsonArray2Excel(jsonFilePath, excelFileName);
    }
}
