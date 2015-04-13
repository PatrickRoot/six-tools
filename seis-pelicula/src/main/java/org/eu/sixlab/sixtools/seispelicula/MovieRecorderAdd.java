/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seispelicula;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * MovieRecorder添加电影记录的Main方法
 *
 * @author 六楼的雨/loki
 * @date 2015/3/21 19:46
 */
public class MovieRecorderAdd extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("addMovie.fxml"));

        Scene scene = new Scene(parent, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Recorder");
        primaryStage.show();
    }
}
