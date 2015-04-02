/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.common.util;

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
public class SixDaoUtil {
    private static SqlSessionFactory factory;
    private static SqlSession sqlSession;

    static {
        InputStream in = SixDaoUtil.class.getClassLoader().getResourceAsStream(
                "mybatis-six-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    private SixDaoUtil(){
        super();
    }

    public static<T> T getMapper(Class<T> clz){
        sqlSession = factory.openSession();
        return sqlSession.getMapper(clz);
    }

    public static void close(){
        sqlSession.commit();
        sqlSession.close();
    }
}
