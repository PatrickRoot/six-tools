/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/23 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/23
 * 功能描述：
 * 版本：1.0-snapshot
 */
public class SixToolsUtil {

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("strings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key){
        return properties.getProperty(key);
    }
    
}
