package com.zxin.basemodule.util;

public class StringUtils {
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String LF = "\n";
    public static final String CR = "\r";
    public static final int INDEX_NOT_FOUND = -1;
    private static final int PAD_LIMIT = 8192;

    public StringUtils() {
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if(cs != null && (strLen = cs.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String trim(String str) {
        return str == null?null:str.trim();
    }

    /**
     * <i> 判断输入字符串是否为空 </i>
     *
     * @param inputStr 指定的字符串
     * @return 如果为空则返回true 如果不为空则返回false
     * @author <a href="mailto:wangyf@mapbar.com">wangyf</a>
     * @date 2011-5-25 下午02:15:02
     */
    public static boolean isNull(Object inputStr) {
        return (null == inputStr) || "".equals(inputStr) || "null".equals(inputStr) || "".equals(inputStr.toString().trim());
    }
    /**
     * 转换储存空间信息
     *
     * @param nSize
     *
     * @return
     */
    public static String byteToString(long nSize) {
        try {
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            String strSize;
            if (nSize < 1024) {
                strSize = nSize + "B";
            } else if (nSize < 1048576) {
                // 1024*1024
                df.applyPattern("0");
                double d = (double) nSize / 1024;
                strSize = df.format(d) + "K";
            } else if (nSize < 1073741824) {
                // 1024*1024*1024
                df.applyPattern("0.0");
                double d = (double) nSize / 1048576;
                strSize = df.format(d) + "M";
            } else {
                // >1G
                df.applyPattern("0.0");
                double d = (double) nSize / 1073741824;
                strSize = df.format(d) + "G";
            }
            return strSize;
        } catch (Exception e) {
            return "0";
        }
    }
}
