/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixtomcat;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.comun.util.A;
import org.eu.sixlab.sixtools.comun.util.ToolLaunch;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 21:06
 */
public class Tomcat implements ToolLaunch{
    
    @Override
    public void launch() {
        Stage stage = new Stage();
        Platform.setImplicitExit(false);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("tomcat.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(parent, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Seis Tomcat : " + A.get());
        stage.show();
    }
}
