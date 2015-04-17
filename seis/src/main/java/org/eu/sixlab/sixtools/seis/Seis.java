/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seis;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.comun.util.A;


/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 12:21
 */
public class Seis extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        Parent parent = FXMLLoader.load(getClass().getResource("seis.fxml"));

        primaryStage.setTitle("Six Tools : " + A.get());
        primaryStage.setScene(new Scene(parent, 800, 500));
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.hide();
        });

        SeisC.SERVICE.initTray(primaryStage);
    }
}
