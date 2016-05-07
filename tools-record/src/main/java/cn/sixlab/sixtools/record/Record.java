package cn.sixlab.sixtools.record;

import cn.sixlab.sixtools.dao.util.UI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Record extends Application {
    private static Logger logger = LoggerFactory.getLogger(Record.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("tool.fxml"));
        } catch (IOException e1) {
            parent = UI.nullParent();
            logger.error(e1.getMessage(), e1);
        }
        Scene scene = new Scene(parent, 795, 598);
        primaryStage.setScene(scene);
        primaryStage.setTitle("记录对比工具");
        primaryStage.show();
    }
}
