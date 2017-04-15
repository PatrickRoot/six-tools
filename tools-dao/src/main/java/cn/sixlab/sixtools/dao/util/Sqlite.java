/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/27 1:38
 */
public class Sqlite {
    public static Dao dao = null;
    public static SimpleDataSource ds = new SimpleDataSource();
    static {
        String url = "jdbc:sqlite:sixtools.db";
        ds.setJdbcUrl(url);
        dao = new NutDao(ds);
    }
}
