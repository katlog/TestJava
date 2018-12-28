package org.person.dfw.util.file;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 提供一些常用的属性文件相关的方法
 */
public final class PropertiesUtil {
	
    public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);


    public static final Map<String, Map<String, String>> PROPERTIES_CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * 从系统属性文件中获取相应的值
     *
     * @param key key
     * @return 返回value
     */
    public final static String key(String key) {
        return System.getProperty(key);
    }

    /**
     * 根据Key读取Value
     *  @param filePath 属性文件
     * @param key      需要读取的属性
     */
    public final static String getValueByKey(String filePath, String key) {

        if (PROPERTIES_CACHE_MAP.get(filePath) != null) {
            return PROPERTIES_CACHE_MAP.get(filePath).get(key);
        }

        Properties pps = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {

            pps.load(in);
            Map<String, String> map = transferMap(pps);
            PROPERTIES_CACHE_MAP.put(filePath, map);
            return pps.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final static Map<String,String> properties(InputStream in){
        Properties pps = new Properties();
        try {
            pps.load(in);
        } catch (IOException e) {
            logger.error("load properties error:"+e.getMessage());
        }
        return transferMap(pps);
    }

    private static Map<String, String> transferMap(Properties pps) {
        Enumeration en = pps.propertyNames();
        Map<String,String> map = new HashMap<>();
        while (en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            map.put(strKey,strValue);
        }
        return map;
    }

    /**
     * 读取Properties的全部信息
     *
     * @param filePath 读取的属性文件
     * @return 返回所有的属性 key:value<>key:value
     */
    public final static Map<String,String> getAllProperties(String filePath) throws IOException {

        if (PROPERTIES_CACHE_MAP.get(filePath) != null) {
            return PROPERTIES_CACHE_MAP.get(filePath);
        }

        Properties pps = new Properties();
        try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
            pps.load(in);
            Map<String, String> map = transferMap(pps);
            PROPERTIES_CACHE_MAP.put(filePath, map);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 写入Properties信息
     *
     * @param filePath 写入的属性文件
     * @param pKey     属性名称
     * @param pValue   属性值
     */
    public final static void WriteProperties(String filePath, String pKey, String pValue) throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream(filePath));
        OutputStream fos = new FileOutputStream(filePath);
        props.setProperty(pKey, pValue);
        props.store(fos, "Update '" + pKey + "' value");

        PROPERTIES_CACHE_MAP.put(filePath, transferMap(props));

    }

}
