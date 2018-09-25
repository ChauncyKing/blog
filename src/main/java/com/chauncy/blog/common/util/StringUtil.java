package com.chauncy.blog.common.util;

import com.alibaba.fastjson.JSONObject;

public class StringUtil {

    /**
     * 字符串为 null 或者 trim()后为空
     * @param text
     * @return
     */
    public static boolean isNullOrEmpty(String text){
        return text == null || text.trim().isEmpty();
    }


    public static void main(String[] args) {
        String s = JSONObject.parseObject("abc", String.class);
        System.out.println(s);
    }
}
