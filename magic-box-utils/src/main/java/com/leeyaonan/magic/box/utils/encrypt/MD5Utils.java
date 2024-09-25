package com.leeyaonan.magic.box.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MD5Utils {

    /**
     * 将字符串加密为MD5格式
     *
     * @param input 输入字符串
     * @return 加密后的MD5字符串
     */
    public static String toMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing MD5", e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 将字节数组加密为Base64编码的MD5字符串
     *
     * @param input 输入字节数组
     * @return Base64编码的MD5字符串
     */
    public static String toBase64MD5(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input);
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error initializing MD5", e);
        }
    }

    public static void main(String[] args) {
        String input = "Hello, World!";
        System.out.println("MD5: " + toMD5(input));
        System.out.println("Base64 MD5: " + toBase64MD5(input.getBytes()));
    }
}
