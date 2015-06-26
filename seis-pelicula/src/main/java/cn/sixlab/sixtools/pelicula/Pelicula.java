/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.pelicula;

import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.base.BaseMain;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:48
 */
public class Pelicula extends BaseMain {

    public static void main(String[] args) {
        title = "Seis Pelicula : " + A.get();
        C.implicitExit = true;
        launch(args);
    }
}
