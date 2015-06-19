/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets;

import cn.sixlab.StrUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/17 22:40
 */
public class GadgetsController implements Initializable{
    private static Logger logger = LoggerFactory.getLogger(GadgetsController.class);
    public static GadgetsController ctrl;


    public BorderPane rootPane;
    public Pane bottomPane;
    public HBox topPane;

    public SplitPane splitPane;
    public ScrollPane scrollPane;
    public ScrollPane mainPane;
    public Label tipsLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ctrl = this;
        Platform.runLater(() -> {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("groups/group01.fxml"));
                scrollPane.setContent(parent);

                Parent mainParent = FXMLLoader.load(getClass().getResource("tools/01/tool01.fxml"));
                mainPane.setContent(mainParent);
            } catch (IOException e1) {
                logger.error(e1.getMessage(), e1);
            }
        });
    }

    public void groupBtnClick(ActionEvent event) {
        String idStr = ((Button) event.getTarget()).getId().substring(5);
        if (StrUtil.isNumber(idStr)) {
            Platform.runLater(() -> {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("groups/group" + idStr + ".fxml"));
                    scrollPane.setContent(parent);

                    Parent mainParent = FXMLLoader.load(getClass().getResource("tools/"+idStr+"/tool01.fxml"));
                    mainPane.setContent(mainParent);
                } catch (IOException e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }
    }
}
