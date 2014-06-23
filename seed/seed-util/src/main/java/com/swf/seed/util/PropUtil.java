package com.swf.seed.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swf.seed.base.SystemContants;

public class PropUtil {
    private static final Log logger = LogFactory.getLog(PropUtil.class);
    private static Properties prop;
    private static  String propFile = SystemContants.PROJECT_PATH+SystemContants.file_separator+"config.properties";//默认属性文件路径
    
    
    /**
     * 修改默认配置文件
     * @param propFile
     */
    public static void changePropFile(String propFile){
    	if (new File(propFile).exists()){
    		PropUtil.propFile = propFile;
    	}else{
    		logger.error("配置文件不存在："+propFile);
    	}
    }
    
    public static Properties getProp(String proFilePath){
    	 Properties props = new Properties();
    	 try {
    		logger.debug("加载配置文件："+proFilePath);
			props.load(new FileInputStream(proFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return props;
    }
    
    /**
     * 获取属性值
     * @param key 键
     * @return
     */
    public static String getPropValue(String key){
    	if (prop==null){
    		prop = getProp(propFile);
    	}
    	
    	if (prop == null){
    		return null;
    	}else {
    		return prop.getProperty(key);
    	}
    }
    
	/**
	 * @param filePath properties文件路径
	 * @param key
	 * @return
	 */
	 public static String getPropValue(String proFilePath,String key) {
	    return getProp(proFilePath).getProperty(key);
	   }
}
