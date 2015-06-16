/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seis;

import javafx.application.Platform;
import org.eu.sixlab.sixtools.comun.util.C;
import org.eu.sixlab.sixtools.comun.util.ToolLaunch;
import org.eu.sixlab.sixtools.seispelicula.Pelicula;
import org.eu.sixlab.sixtools.seisplan.Plan;
import org.eu.sixlab.sixtools.sixpunto.Punto;
import org.eu.sixlab.sixtools.sixtomcat.Tomcat;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/19 17:14
 */
public class Launcher {
    private static ToolLaunch toolLaunch = null;

    public static void launchTool(Integer id){
        switch (id) {
            case C.TOOLS_PLAN_ID:
                toolLaunch = new Plan();
                break;
            case C.TOOLS_PELICULA_ID:
                toolLaunch = new Pelicula();
                break;
            case C.TOOLS_PUNTO_ID:
                toolLaunch = new Punto();
                break;
            case C.TOOLS_TOMCAT_ID:
                toolLaunch = new Tomcat();
                break;
            default:
                return;
        }
        Platform.runLater(() -> {
            toolLaunch.launch();
        });
    }
}
