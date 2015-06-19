/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis.old;

import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.ToolLoader;
import cn.sixlab.sixtools.gadgets.Gadgets;
import cn.sixlab.sixtools.pelicula.Pelicula;
import cn.sixlab.sixtools.plan.Plan;
import cn.sixlab.sixtools.punto.Punto;
import cn.sixlab.sixtools.tomcat.Tomcat;
import javafx.application.Platform;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/19 17:14
 */
public class Launcher {
    private static ToolLoader loader = null;

    public static void launchTool(Integer id){
        switch (id) {
            case C.TOOLS_PLAN_ID:
                loader = new Plan();
                break;
            case C.TOOLS_PELICULA_ID:
                loader = new Pelicula();
                break;
            case C.TOOLS_PUNTO_ID:
                loader = new Punto();
                break;
            case C.TOOLS_TOMCAT_ID:
                loader = new Tomcat();
                break;
            case C.TOOLS_GADGETS_ID:
                loader = new Gadgets();
                break;
            default:
                return;
        }
        Platform.runLater(() -> {
            loader.load();
        });
    }
}
