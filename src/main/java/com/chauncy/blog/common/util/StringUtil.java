package com.chauncy.blog.common.util;

public class StringUtil {

    /**
     * 字符串为 null 或者 trim()后为空
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text){
        return text == null || text.trim().isEmpty();
    }
}
