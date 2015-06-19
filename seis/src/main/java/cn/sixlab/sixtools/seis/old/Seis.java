/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis.old;

import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.punto.PuntoTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 12:21
 */
public class Seis extends Application{

    public static void main(String[] args) {
        startTimers();
        launch(args);
    }

    private static void startTimers() {
        PuntoTask.startTask();
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
