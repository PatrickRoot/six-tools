/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seisplan;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.ToolLaunch;

import java.io.IOException;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:30
 */
public class Plan implements ToolLaunch{

    @Override
    public void launch() {
        Stage stage = new Stage();
        Platform.setImplicitExit(false);
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("plan.fxml"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Scene scene = new Scene(parent, 600, 600);
        stage.setScene(scene);
        stage.setTitle("Six Plan : " + A.get());
        stage.show();
    }

}