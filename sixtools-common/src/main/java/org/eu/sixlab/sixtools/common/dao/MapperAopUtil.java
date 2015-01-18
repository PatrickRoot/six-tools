package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixutil.ClassUtil;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by loki on 2015/1/18.
 */
public class MapperAopUtil {

    static {
        String packageName = "org.eu.sixlab.sixtools.common.dao.mapper";

        List<Class>  classList= ClassUtil.getClassesByPackage(packageName);

        for (Class aClass : classList) {

        }


    }

}
