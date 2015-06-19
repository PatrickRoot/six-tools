/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets;

import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.ToolLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/17 22:40
 */
public class Gadgets extends Application implements ToolLoader {
    public static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    private void show(){
        Stage stage = new Stage();
        this.stage = stage;
        Platform.setImplicitExit(false);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("gadgets.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(parent, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Seis Gadgets : " + A.get());
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
