package com.leeyaonan.magic.box.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 9:55
 * @desc:
 */
public class JsonExcelUtils {

    /**
     * 将json列表转成excel文件
     * 注意：目前只支持json为单一列表，不支持复杂json
     *
     * @param jsonArrayString json列表文本
     * @param excelFileName 输出的excel文件名称
     */
    public static void exportJsonArrayToExcel(String jsonArrayString, String excelFileName) {
        try {
            JSONArray jsonArray = JSON.parseArray(jsonArrayString);
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Sheet1");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            int columnIndex = 0;
            for (Object key : ((JSONObject) jsonArray.get(0)).keySet()) {
                Cell cell = headerRow.createCell(columnIndex++);
                cell.setCellValue(key.toString());
            }

            // 填充数据
            int rowIndex = 1;
            for (Object jsonObject : jsonArray) {
                Row dataRow = sheet.createRow(rowIndex++);
                int cellIndex = 0;
                for (Object value : ((JSONObject) jsonObject).values()) {
                    Cell cell = dataRow.createCell(cellIndex++);
                    cell.setCellValue(String.valueOf(value));
                }
            }

            // 自动调整列宽
            for (int i = 0; i < columnIndex; i++) {
                sheet.autoSizeColumn(i);
            }

            // 将工作簿写入文件
            try (FileOutputStream fileOut = new FileOutputStream(excelFileName)) {
                workbook.write(fileOut);
            }

            workbook.close();
            System.out.println("导出成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
