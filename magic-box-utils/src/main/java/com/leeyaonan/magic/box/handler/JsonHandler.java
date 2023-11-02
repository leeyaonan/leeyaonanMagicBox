package com.leeyaonan.magic.box.handler;

import com.leeyaonan.magic.box.utils.file.TextFileUtils;
import com.leeyaonan.magic.box.utils.json.JsonExcelUtils;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 9:58
 * @desc:
 */
public class JsonHandler {

    /**
     * 将存储json列表的文件，转换成excel文件
     *
     * @param jsonFilePath  存储json列表的文件路径
     * @param excelFileName 导出为excel的文件名称
     */
    public static void jsonArray2Excel(String jsonFilePath, String excelFileName) {
        String jsonString = TextFileUtils.readTextFileNowrap(jsonFilePath);
        JsonExcelUtils.exportJsonArrayToExcel(jsonString, excelFileName);
    }
}
