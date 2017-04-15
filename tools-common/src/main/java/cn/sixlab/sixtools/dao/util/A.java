/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import java.util.Random;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/4/17 10:49
 */
public class A {

    public static String get(){
        int index = new Random().nextInt(aforismos.length);
        return aforismos[index];
    }

    private static String[] aforismos = new String[]{
            "用80%时间做20%事情",
            "吾日三省吾身",
            "记日记啦，记日记啦",
            "每天做好一件事"
    };
}
