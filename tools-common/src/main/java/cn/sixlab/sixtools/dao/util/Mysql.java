/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/27 1:38
 */
public class Mysql {
    public static Dao dao = null;
    public static SimpleDataSource ds = new SimpleDataSource();
    static {
        String url = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8";
        ds.setJdbcUrl(url);
        ds.setUsername("root");
        ds.setPassword("root");
        try {
            ds.setDriverClassName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dao = new NutDao(ds);
    }
}
