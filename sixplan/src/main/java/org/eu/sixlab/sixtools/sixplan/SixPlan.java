/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Six Plan 的main方法
 * @author 六楼的雨/loki
 * @date 2015/4/3 12:43
 */
public class SixPlan extends Application{
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));

        Scene scene = new Scene(parent, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Six Plan");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
