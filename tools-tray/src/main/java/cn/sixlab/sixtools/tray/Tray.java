/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.tray;

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
import java.time.LocalDateTime;

/**
 * cn.sixlab.sixtools.bandeja.Seis main方法,启动所有定时器,初始化右下角界面.
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 12:21
 */
public class Tray extends BaseMain {
    private static Logger logger = LoggerFactory.getLogger(Tray.class);
    private Stage stage;
    
    public static void main(String[] args) {
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
    public void show(Stage stage) {
        this.stage = stage;
        Platform.setImplicitExit(C.implicitExit);
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("tool.fxml"));
        } catch (IOException e1) {
            parent = UI.nullParent();
            logger.error(e1.getMessage(),e1);
        }
        Scene scene = new Scene(parent, 795, 498);
        stage.setScene(scene);
        stage.setTitle("Seis Bandeja : " + LocalDateTime.now().toString());
        TrayService service = new TrayService();
        service.initTray(this);
    }
}
