package cn.sixlab.sixtools.comun.util;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ToolLoader extends Application {
    private static Logger logger = LoggerFactory.getLogger(ToolLoader.class);
    public static String title = "";
    public static ToolLoader loader;
    public static Stage stage;

    public void show(Stage stage){
        this.loader = this;
        if (null == stage) {
            stage = new Stage();
        }
        this.stage = stage;
        Platform.setImplicitExit(C.implicitExit);
        Parent parent;
        try {
            parent = FXMLLoader.load(getClass().getResource("tool.fxml"));
        } catch (IOException e1) {
            parent = UI.nullParent();
            logger.error(e1.getMessage(), e1);
        }
        Scene scene = new Scene(parent, 795, 498);
        stage.setScene(scene);
        stage.setTitle(title);
    }

    public void load(){
        show(null);
    }

    public void load(ToolLoader loader) {
        this.loader = loader;
        show(null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        show(primaryStage);
        primaryStage.show();
    }
}
