package cn.druglots.mall.common.utils;

import java.security.MessageDigest;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.common.utils
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-04 00:34
 * @Description: 加密工具类
 */
public class MD5Util {

    public static String encrypt(String encryptStr) {
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
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    public static String encrypt16(String encryptStr) {
        return encrypt(encryptStr).substring(8, 24);
    }
}
