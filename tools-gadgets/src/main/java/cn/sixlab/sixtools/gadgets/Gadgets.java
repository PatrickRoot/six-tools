/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets;

import cn.sixlab.sixtools.tray.TrayService;
import cn.sixlab.sixtools.dao.util.A;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.base.BaseMain;
import cn.sixlab.sixtools.dao.util.UI;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/17 22:40
 */
public class Gadgets extends BaseMain {
    private static Logger logger = LoggerFactory.getLogger(Gadgets.class);
    private static Stage stage;

    public static void main(String[] args) throws ClassNotFoundException {
        launch(args);
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show(primaryStage);
    }

    @Override
    public void show(Stage stage){
        this.stage = stage;
        Platform.setImplicitExit(C.implicitExit);
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("gadgets.fxml"));
        } catch (IOException e1) {
            parent = UI.nullParent();
            logger.error(e1.getMessage(), e1);
        }
        Scene scene = new Scene(parent, 1000, 600);
        stage.setScene(scene);
        stage.setTitle("Seis Gadgets : " + A.get());
        TrayService service = new TrayService();
        service.initTray(this);
    }
}
