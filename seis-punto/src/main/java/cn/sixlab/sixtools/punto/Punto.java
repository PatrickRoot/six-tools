/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.punto;

import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.ToolLoader;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 21:06
 */
public class Punto extends ToolLoader {

    public static void main(String[] args) {
        title = "Six Punto : " + A.get();
        C.implicitExit = true;
        launch(args);
    }

}
