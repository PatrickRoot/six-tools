/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.movie;

import cn.sixlab.sixtools.dao.util.A;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.base.BaseMain;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:48
 */
public class MovieRecorder extends BaseMain {

    public static void main(String[] args) {
        title = "Seis Pelicula : " + A.get();
        C.implicitExit = true;
        launch(args);
    }
}
