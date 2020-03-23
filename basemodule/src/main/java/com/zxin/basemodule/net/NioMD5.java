package com.zxin.basemodule.net;

import android.text.TextUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NioMD5 {
    private static NioMD5 INSTANCE = null;

    public static NioMD5 getInstance() {
        if (INSTANCE == null) {
            synchronized (NioMD5.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NioMD5();
                }
            }
        }
        return INSTANCE;
    }

    private NioMD5() {

    }

    public String getMD5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder("");
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    result.append('0');
                }
                result.append(temp);
            }
            String signed = result.toString();
            return signed;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
