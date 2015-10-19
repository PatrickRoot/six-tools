/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.util;

import cn.sixlab.j2p.Callback;
import cn.sixlab.j2p.J2p;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * 与Python通讯
 *
 * @author 六楼的雨/loki
 * @date 2015/6/5 14:34
 */
public class P implements Callback{

    private static boolean started = false;
    private static List list = new ArrayList();

    @Override
    public void call(String message) {
        for (Object o : list) {
            try {
                Object obj = JSON.parse(message);

                break;
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private static void start(){
        J2p.setCallBack(new P());
        J2p.start();
        started = true;
    }

    public static void addObject(Object o){
        list.add(o);
    }

    /**
     * 发送数据
     *
     * @param t
     * @param <T>
     * @return
     */
    public static<T> int sendData(T t){
        try{
            if(!started){
                start();
            }
            String jsonStr = JSON.toJSONString(t);
            J2p.sendData(jsonStr);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
