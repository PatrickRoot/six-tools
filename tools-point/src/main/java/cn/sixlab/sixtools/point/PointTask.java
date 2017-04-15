/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.point;

import cn.sixlab.sixtools.dao.util.T;

import javax.swing.*;
import java.util.TimerTask;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/12 9:34
 */
public class PointTask extends TimerTask{

    public static void startTask() {
        T.repeatSpecificTime(new PointTask(),8,55);
    }

    @Override
    public void run() {
        JOptionPane.showMessageDialog(null, "注意计划啊！");
    }
}
