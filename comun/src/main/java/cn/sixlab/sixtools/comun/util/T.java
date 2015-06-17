/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器Util
 *
 * @author 六楼的雨/loki
 * @date 2015/6/11 23:24
 */
public class T extends TimerTask{

    private static Timer timer = new Timer();

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:19
     * 方法描述：半小时purge一次
     */
    @Override
    public void run() {
        timer.purge();
    }
    static {
        timer.schedule(new T(), 0, 1000 * 60 * 30);
    }

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:20
     * 方法描述：每隔period毫秒重复一次
     */
    public static void simpleRepeat(TimerTask timerTask, Long period) {
        timer.schedule(timerTask, 0, period);
    }

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:20
     * 方法描述：每隔period毫秒重复一次,总共重复times次
     */
    public static void repeatTimes(TimerTask timerTask, Long period, Integer times) {
        timer.schedule(timerTask, 0, period);

        Timer endTimer = new Timer();
        endTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerTask.cancel();
            }
        }, period * times);
    }

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:20
     * 方法描述：每个整点重复一次
     */
    public static void repeatHours(TimerTask timerTask) {
        Date alreadyDate = new Date();

        alreadyDate.setYear(0);
        alreadyDate.setMonth(0);
        alreadyDate.setDate(0);
        alreadyDate.setHours(0);

        Date aHour = new Date(0, 0, 0, 1, 0, 0);
        Long delayTimes = aHour.getTime() - alreadyDate.getTime();

        timer.schedule(timerTask, delayTimes, 1000 * 60 * 60);
    }

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:20
     * 方法描述：每天的hour点minute分重复一次
     */
    public static void repeatSpecificTime(TimerTask timerTask, Integer hour, Integer minute) {
        Date alreadyDate = new Date();
        alreadyDate.setYear(0);
        alreadyDate.setMonth(0);
        alreadyDate.setDate(0);
        System.out.println(alreadyDate.toGMTString());

        Date specificDate = new Date(0, 0, 0, hour, minute, 0);
        System.out.println(specificDate.toGMTString());

        Long delayTimes = specificDate.getTime() - alreadyDate.getTime();
        System.out.println(delayTimes);
        if(delayTimes<0){
            delayTimes += 86400000;
        }
        System.out.println(delayTimes);

        timer.schedule(timerTask, delayTimes, 1000 * 60 * 60 * 24);
    }

    /**
     * 作者: 六楼的雨
     * 创建时间：2015/6/16 22:20
     * 方法描述：每周的week[1-7]周的hour点minute分重复一次
     */
    public static void repeatByWeek(TimerTask timerTask, Integer week, Integer hour, Integer minute) {
        //Date alreadyDate = new Date();
        //alreadyDate.setYear(0);
        //alreadyDate.setMonth(0);
        //alreadyDate.setDate(0);
        //System.out.println(alreadyDate.toGMTString());
        //
        //Date specificDate = new Date(0, 0, 0, hour, minute, 0);
        //System.out.println(specificDate.toGMTString());
        //
        //Long delayTimes = specificDate.getTime() - alreadyDate.getTime();
        //System.out.println(delayTimes);
        //if (delayTimes < 0) {
        //    delayTimes += 86400000;
        //}
        //System.out.println(delayTimes);
        //
        //timer.schedule(timerTask, delayTimes, 1000 * 60 * 60 * 24);
    }
}
