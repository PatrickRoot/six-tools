/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Six Plan 管理查看的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 14:58
 */
public class SixPlanController implements Initializable{
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addPlan(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        Parent parent = FXMLLoader.load(getClass().getResource("add.fxml"));

        Scene scene = new Scene(parent, 320, 230);

        stage.setScene(scene);
        stage.setTitle("Add Plan");
        stage.show();
    }

    public void stopPlan(ActionEvent event) {

    }

    public void search(ActionEvent event) {

    }
}
