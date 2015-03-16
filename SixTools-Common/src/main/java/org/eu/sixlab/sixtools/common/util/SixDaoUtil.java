/* 
 **************************************************************************************
 * Copyright www.ebidding.com.cn 2015/2/17 Authors: 曹林伟 <caolinwei@ebidding.com.cn>*
 **************************************************************************************
 */
package org.eu.sixlab.sixtools.common.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * 作者：曹林伟
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：2.0.1
 */
public class SixDaoUtil {
    private static SqlSessionFactory factory;
    private static SqlSession sqlSession;

    static {
        InputStream in = SixDaoUtil.class.getClassLoader().getResourceAsStream(
                "mybatis-six-config.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
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
