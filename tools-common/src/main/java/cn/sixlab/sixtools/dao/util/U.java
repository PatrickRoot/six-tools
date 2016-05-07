/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * SixTools的util类
 *
 * @author 六楼的雨/loki
 * @date 2015/3/23 19:46
 */
public class U {

    private static Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("strings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private U(){
        super();
    }

    public static String getString(String key){
        return properties.getProperty(key);
    }
    
}
