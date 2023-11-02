package com.leeyaonan.magic.box.utils.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
    public static String readTextFileNowrap(String filePath) {
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
}
