package com.leeyaonan.magic.box.utils.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

/**
 * @author: leeyaonan
 * @date: 2024-02-02 17:18
 * @desc:
 */
public class Xml2JsonUtils {

    /**
     * 将xml文件解析为json字符串
     *
     * @param filePath
     * @return
     */
    public static String parseXml2Json(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.getName().endsWith(".xml")) {
            try {
                // 创建XmlMapper对象
                XmlMapper xmlMapper = new XmlMapper();

                // 将XML文件解析为JsonNode对象
                JsonNode jsonNode = xmlMapper.readTree(file);

                // 创建ObjectMapper对象
                ObjectMapper objectMapper = new ObjectMapper();

                // 将JsonNode对象转换为JSON字符串
                return objectMapper.writeValueAsString(jsonNode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
