/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.util;

import java.util.Random;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/17 10:49
 */
public class A {

    private static String[] aforismos = new String[]{
            "用80%时间做20%事情"
    };

    public static String get(){
        int index = new Random().nextInt(aforismos.length);
        return aforismos[index];
    }
}
