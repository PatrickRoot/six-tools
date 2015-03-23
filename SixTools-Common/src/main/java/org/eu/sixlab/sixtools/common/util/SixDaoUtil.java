/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
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
        System.out.println("");
    }
//
//    private static SqlSessionFactory getSessionFactory(){
//        return factory;
//    }
//
//    private static SqlSession getSession() {
//        sqlSession = factory.openSession();
//        return sqlSession;
//    }

    public static<T> T getMapper(Class<T> clz){
        sqlSession = factory.openSession();
        return sqlSession.getMapper(clz);
    }

    public static void close(){
        sqlSession.commit();
        sqlSession.close();
    }
}
