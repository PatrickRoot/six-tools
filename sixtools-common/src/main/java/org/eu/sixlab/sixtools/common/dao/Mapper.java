package org.eu.sixlab.sixtools.common.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.eu.sixlab.sixutil.ClassUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

/**
 * Created by loki on 2015/1/18.
 */
public class Mapper {

    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            String resource = "mybatis-conf.xml";

            File file = new File("mytest.tttt");
            file.createNewFile();

            InputStream inputStream = Resources.getResourceAsStream(resource);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T>T getMapper(Class<T> clazz){
        return sqlSessionFactory.openSession().getMapper(clazz);
    }

    public static void main(String[] args) {
        List a = ClassUtil.getClassesByPackage("org.eu.sixlab.sixtools.common.dao");
        System.out.println(a.size());
        for (Object o : a) {
            Class clazz = (Class)o;
            System.out.println(clazz.getName());
        }
    }

}
