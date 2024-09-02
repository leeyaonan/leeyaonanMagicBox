package com.leeyaonan.magic.box.utils.zip;

import java.io.*;
import java.util.zip.*;

/**
 * ZIP压缩工具类
 */
public class ZipUtils {

    /**
     * 将指定文件夹打包成ZIP文件
     *
     * @param sourceFolder 源文件夹路径
     * @param zipFilePath  目标ZIP文件路径
     */
    public static void zipFolder(String sourceFolder, String zipFilePath) {
        File folder = new File(sourceFolder);

        // 如果目标文件路径为空，则默认为源文件夹路径加".zip"后缀
        if (zipFilePath == null || zipFilePath.trim().isEmpty()) {
            zipFilePath = sourceFolder + ".zip";
        }

        try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFilePath))) {
            zipFile(folder, "", zipOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归压缩文件夹
     *
     * @param folder       当前文件夹
     * @param parentFolder 父文件夹名称
     * @param zipOut       ZIP输出流
     * @throws IOException 如果发生IO异常
     */
    private static void zipFile(File folder, String parentFolder, ZipOutputStream zipOut) throws IOException {
        File[] files = folder.listFiles();
        if (files == null) {
            return;
        }

        byte[] bytes = new byte[1024];
        for (File file : files) {
            if (file.isDirectory()) {
                if (!parentFolder.isEmpty()) {
                    zipOut.putNextEntry(new ZipEntry(parentFolder + File.separator + file.getName() + "/"));
                } else {
                    zipOut.putNextEntry(new ZipEntry(file.getName() + "/"));
                }
                zipFile(file, parentFolder + File.separator + file.getName(), zipOut);
                continue;
            }

            FileInputStream fis = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(parentFolder.isEmpty() ? file.getName() : parentFolder + File.separator + file.getName());
            zipOut.putNextEntry(zipEntry);

            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            zipOut.closeEntry();
            fis.close();
        }
    }
}
