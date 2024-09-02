package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.file.TextFileUtils;
import org.junit.Test;

import java.util.List;

public class TextFileUtilsTest {

    @Test
    public void testReadTextFileToList() {
        List<String> textList = TextFileUtils.readTextFileToList("src/test/resources/testReadTextFileToList.txt", true);
        System.out.println(textList);
    }
}
