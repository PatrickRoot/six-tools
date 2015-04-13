/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seispelicula;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:48
 */
public class Pelicula {
    public void launch(){
        try {
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));

            Scene scene = new Scene(parent, 600, 600);
            stage.setScene(scene);
            stage.setTitle("Movie Recorder");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
