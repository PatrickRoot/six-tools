/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Dao层的util类
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public abstract class Du {
    protected SqlSessionFactory factory;

    public Du(){
        InputStream in = Du.class.getClassLoader().getResourceAsStream("mybatis-six-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    public abstract Object getMapper(SqlSession sqlSession);
}
