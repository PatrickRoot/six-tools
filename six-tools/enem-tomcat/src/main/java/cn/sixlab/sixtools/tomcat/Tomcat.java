/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.tomcat;

import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.base.BaseMain;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 21:06
 */
public class Tomcat extends BaseMain {

    public static void main(String[] args) {
        title = "Six Tomcat : " + A.get();
        C.implicitExit = true;
        launch(args);
    }

}
