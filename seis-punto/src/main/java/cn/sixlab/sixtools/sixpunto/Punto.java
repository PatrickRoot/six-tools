/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.sixpunto;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.ToolLoader;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 21:06
 */
public class Punto extends Application implements ToolLoader {

    public static void main(String[] args) {
        launch(args);
    }

    private void show() {
        Stage stage = new Stage();
        Platform.setImplicitExit(false);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("punto.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(parent, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Six Punto : " + A.get());
        stage.show();
    }

    @Override
    public void load() {
        show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show();
    }
}
