/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/22 8:52
 */
public class UI {

    public static Parent nullParent(){
        Parent parent = new Label("读取失败");
        return parent;
    }
}
