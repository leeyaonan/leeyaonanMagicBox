package com.leeyaonan.magic.box.utils;

import com.leeyaonan.magic.box.utils.zip.ZipUtils;
import org.junit.Test;

public class ZipUtilsTest {

    @Test
    public void testZip() {
        String sourceFolder = "src/test/resources/pic";
//        String zipFilePath = "src/test/resources/output/20240902.zip";
        ZipUtils.zipFolder(sourceFolder, null);
    }
}
