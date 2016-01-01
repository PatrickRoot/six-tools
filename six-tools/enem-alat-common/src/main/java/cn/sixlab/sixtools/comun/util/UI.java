/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.util;

import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/22 8:52
 */
public class UI {

    public static Parent nullParent(){
        Parent parent = new Label("读取失败");
        return parent;
    }
}
