package com.qinyue.monitor.util;

import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Base64;

import androidx.annotation.RequiresApi;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class Base64Converter {
    public static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }
}
