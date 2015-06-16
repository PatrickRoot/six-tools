/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.util;

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
    @Override
    public void run() {
        timer.purge();
    }
    static {
        timer.schedule(new T(), 0, 1000 * 60 * 30);
    }

    public static void simpleRepeat(TimerTask timerTask, Long period) {
        timer.schedule(timerTask, 0, period);
    }

    public static void repeatTimes(TimerTask timerTask, Long period, Integer times) {
        timer.schedule(timerTask, 0, period);

        Timer endTimer = new Timer();
        endTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerTask.cancel();
            }
        }, period * times + 10000);
    }

    public static void repeatHours(TimerTask timerTask, Integer hours) {
        Date alreadyDate = new Date();

        alreadyDate.setYear(0);
        alreadyDate.setMonth(0);
        alreadyDate.setDate(0);
        alreadyDate.setHours(0);

        Date aHour = new Date(0, 0, 0, 1, 0, 0);
        Long delayTimes = aHour.getTime() - alreadyDate.getTime();

        timer.schedule(timerTask, delayTimes, 1000 * 60 * 60);
    }

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
}
