/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.util;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/27 1:38
 */
public class D {
    public static Dao dao = null;
    public static SimpleDataSource ds = new SimpleDataSource();
    static {
        ds.setJdbcUrl("jdbc:sqlite:sixtools.db");
        dao = new NutDao(ds);
    }

    public void genBean(String table){

    }

    public static void main(String[] args) {

        new D().genBean("");

    }
}
