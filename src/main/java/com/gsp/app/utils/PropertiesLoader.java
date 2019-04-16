package com.gsp.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class PropertiesLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    
    private static String[] resourcesPaths = {"applicationContext.properties"};
    
    private static Properties properties = new Properties();
    
    static{
        for (String location : resourcesPaths) {
            logger.debug("Loading properties file from path:{}", location);
            InputStream is = null;
            try {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                properties.load(is);
            } catch (IOException ex) {
            	logger.error(ex.getMessage(),ex);
                logger.info("Could not load properties from path:{}, {} ", location, ex.getMessage());
            } finally {
                IOUtils.closeQuietly(is);
            }
        }
    }
    
    /**
     * 取出Property。
     */
    private static String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }

    /**
     * 取出String类型的Property,如果都為Null则抛出异常.
     */
    public static String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    /**
     * 取出String类型的Property.如果都為Null則返回Default值.
     */
    public static String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    /**
     * 取出Integer类型的Property.如果都為Null或内容错误则抛出异常.
     */
    public static Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 取出Integer类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property.如果都為Null或内容错误则抛出异常.
     */
    public static Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Double类型的Property.如果都為Null則返回Default值，如果内容错误则抛出异常
     */
    public static Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        if (value == null) {
            return Double.valueOf(defaultValue);
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property.如果都為Null抛出异常,如果内容不是true/false则返回false.
     */
    public static Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    /**
     * 取出Boolean类型的Propert.如果都為Null則返回Default值,如果内容不为true/false则返回false.
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }

}

