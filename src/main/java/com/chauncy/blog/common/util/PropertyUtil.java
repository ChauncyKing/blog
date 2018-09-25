package com.chauncy.blog.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取类
 *
 * @author Chauncy
 */
@Slf4j
public class PropertyUtil {

    private static Properties properties = new Properties();

    static {
        // 配置文件名
        String fileName = "application.properties";
        try {
            log.info("[读取配置文件:{}]", fileName);
            properties.load(new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常", e);
        }

    }

    /**
     * 根据键获取对应的值
     *
     * @param key 键
     * @return
     */
    public static String getProperty(String key) {
        String value = (String) properties.get(key.trim());
        if (StringUtil.isNullOrEmpty(value)) {
            log.error("[key:{} value:null or empty]", key);
            return null;
        }
        return value.trim();
    }

    /**
     * 根据键获取对应的值
     *
     * @param key          键
     * @param defaultValue 当键对应的值不存在或为空时，返回该默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = (String) properties.get(key.trim());
        if (StringUtil.isNullOrEmpty(value)) {
            log.info("[key:{} value:null or empty]", key);
            log.info("[使用默认值][key:{} value:{}]", key, defaultValue);
            return defaultValue;
        }
        return value.trim();
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String host = PropertyUtil.getProperty("redis.host");
        System.out.println(host);
    }
}
