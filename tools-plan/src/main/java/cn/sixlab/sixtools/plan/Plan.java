/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.plan;

import cn.sixlab.sixtools.dao.util.A;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.base.BaseMain;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:30
 */
public class Plan extends BaseMain {

    public static void main(String[] args) {
        title = "Six Plan : " + A.get();
        C.implicitExit = true;
        launch(args);
    }

}