/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.sixpunto;

import cn.sixlab.sixtools.comun.util.T;

import javax.swing.*;
import java.util.TimerTask;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/12 9:34
 */
public class PuntoTask extends TimerTask{

    public static void startTask() {
        T.repeatSpecificTime(new PuntoTask(),8,55);
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "注意计划啊！");
    }
}
