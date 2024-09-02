package com.leeyaonan.magic.box.utils.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: leeyaonan
 * @date: 2023-11-02 9:57
 * @desc:
 */
public class TextFileUtils {

    /**
     * （不换行）读取文本文件为一整个字符串
     *
     * @param filePath 文件路径
     * @return 文本内容
     */
    public static String readTextFileNoWrap(String filePath) {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    /**
     * 按行读取文本文件为字符串列表
     * @param filePath
     * @param removeBlankLine 是否移除空白行
     * @return
     */
    public static List<String> readTextFileToList(String filePath, Boolean removeBlankLine) {
        List<String> list = new ArrayList<>();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (removeBlankLine && line.trim().isEmpty()) {
                    continue;
                }
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
