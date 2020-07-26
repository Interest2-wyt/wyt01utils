package com.test.properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件工具类
 */
public class PropertiesUitl {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUitl.class);

    private static String path = null;
    private static PropertiesConfiguration config = null;

    /**
     * 旧的方法，通过jdk本身的工具实现
     */
    public void oldStaticRecord(){
        Properties properties = null;
        if (null == properties) {
            properties = new Properties();
            InputStream reader = null;
            try {
                path = System.getProperty("user.dir").replace("\\", "/") + "/config/test.properties";
//                path = System.getProperty("user.dir").replace("\\", "/") + "/autoconfig.properties";
                reader = new BufferedInputStream(new FileInputStream(new File(path)));
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
        properties.getProperty("key");
    }

    /**
     * 新的方法：静态初始化
     */
    public static void newStaticMethod(){
        try {
            path = System.getProperty("user.dir").replace("\\", "/") + "/config/test.properties";
//                path = System.getProperty("user.dir").replace("\\", "/") + "/autoconfig.properties";
            config = new PropertiesConfiguration(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 新的方法：根据key获取属性文件中的值
     */
    public static String getString(String key) {
        if (config==null||path==null){
            newStaticMethod();
        }
        return config.getProperty(key).toString();
    }

    /**
     * 新的方法：回写属性文件中key对应的值
     */
    public static void update(String key,String value){
        try {
            config.setProperty(key, value);
            config.save();
            config = new PropertiesConfiguration(path);
        } catch (ConfigurationException e) {
            logger.error("更新属性文件异常：",e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getString("violation.id"));
        update("violation.id","25");
        System.out.println(getString("violation.id"));
        System.out.println(getString("violation.volume"));
    }

}