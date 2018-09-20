package com.chauncy.blog.common.message;

import com.chauncy.blog.common.util.StringUtil;

import java.util.TreeMap;

/**
 * 封装要发送的数据
 * @author Chauncy
 */
public class ParamMap extends TreeMap<String, String> {

    /**
     * value分隔符
     */
    private static final String SEPARATOR = ",";

    /**
     * 定义一个拼接模式，将数据被添加到map里。
     * 列如
     * <code>
     * addWithSeparator("K", "v1");
     * addWithSeparator("K", "v2");
     * </code>
     * Then, the map is {"K", "v1, v2"}
     *
     * @param key   is the map's key
     * @param value is the adding data
     */
    public void addWithSeparator(String key, String value) {
        if (StringUtil.isNullOrEmpty(key)) {
            return;
        }
        if (this.containsKey(key)) {
            String item = SEPARATOR + value;
            this.put(key, this.get(key) + item);
        } else {
            this.put(key, value);
        }
    }
}
