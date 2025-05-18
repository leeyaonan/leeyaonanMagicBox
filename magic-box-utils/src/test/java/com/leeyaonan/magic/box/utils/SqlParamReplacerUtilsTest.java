package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.file.TextFileUtils;
import com.leeyaonan.magic.box.utils.sql.SqlParamReplacerUtils;
import org.junit.Test;

import java.util.List;

public class SqlParamReplacerUtilsTest {

    @Test
    public void test() {
        String filePath = "src/test/resources/testSqlParamReplacerUtils.txt";
        List<String> lineList = TextFileUtils.readTextFileToList(filePath, true);
        String preparingSql = lineList.get(0); // sql
        String parametersLine = lineList.get(1); // param
        String completeSql = SqlParamReplacerUtils.buildCompleteSql(preparingSql, parametersLine);
        System.out.println(completeSql);
    }
}
