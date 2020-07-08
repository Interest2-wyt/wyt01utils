package com.test.properties;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 */
public class PropertiesUitl {


    private static Properties properties = null;

    static {
        if (null == properties) {
            properties = new Properties();
            InputStream reader = null;
            try {

                reader = new BufferedInputStream(new FileInputStream(
                        new File(System.getProperty("user.dir").replace("\\", "/") + "/config/config.properties")));

                properties.load(reader);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader = null;
                }
            }
        }
    }

    private PropertiesUitl() {
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

}