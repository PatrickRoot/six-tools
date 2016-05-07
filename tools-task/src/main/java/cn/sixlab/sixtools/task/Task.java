/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.task;

import cn.sixlab.sixtools.dao.util.A;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.base.BaseMain;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/25 15:58
 */
public class Task extends BaseMain {

    public static void main(String[] args) {
        title = "Six Tarea : " + A.get();
        C.implicitExit = true;
        launch(args);
    }

}
