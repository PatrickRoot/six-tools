/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolConfig;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.UI;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/17 22:40
 */
public class GadgetsController extends BaseController implements Initializable{
    private static Logger logger = LoggerFactory.getLogger(GadgetsController.class);
    public static GadgetsController self;
    private Dao dao = D.dao;

    public BorderPane rootPane;
    public GridPane topPane;
    public Pane bottomPane;

    public SplitPane splitPane;
    public ScrollPane scrollPane;
    public ScrollPane mainPane;
    public Label tipsLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
        Platform.runLater(() -> {
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("groups/group01.fxml"));
                Parent mainParent = null;
                try {
                    Button btn = (Button) parent.getChildrenUnmodifiable().get(0);
                    String btnId = btn.getId();
                    ToolConfig toolConfig = dao.fetch(ToolConfig.class, Cnd.where("btnId","=",btnId));
                    String className = toolConfig.getClassName();
                    Class clz = Class.forName(className);

                    mainParent = FXMLLoader.load(clz.getResource("tool.fxml"));
                }catch (Exception e){
                    mainParent = UI.nullParent();
                    logger.error(e.getMessage(), e);
                }
                mainPane.setContent(mainParent);
            } catch (Exception e1) {
                parent = UI.nullParent();
                logger.error(e1.getMessage(), e1);
            }
            scrollPane.setContent(parent);
        });
    }

    public void groupBtnClick(ActionEvent event) {
        System.out.println("groupBtnClick->" + this);
        String idStr = ((Button) event.getTarget()).getId();
        Platform.runLater(() -> {

            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("groups/" + idStr + ".fxml"));

                Parent mainParent = null;
                try {
                    Button btn = (Button) parent.getChildrenUnmodifiable().get(0);
                    String btnId = btn.getId();
                    ToolConfig toolConfig = dao.fetch(ToolConfig.class, Cnd.where("btnId", "=", btnId));
                    String className = toolConfig.getClassName();
                    Class clz = Class.forName(className);

                    mainParent = FXMLLoader.load(clz.getResource("tool.fxml"));
                }catch (Exception e){
                    mainParent = UI.nullParent();
                    logger.error(e.getMessage(), e);
                }
                mainPane.setContent(mainParent);
            } catch (Exception e1) {
                parent = UI.nullParent();
                logger.error(e1.getMessage(), e1);
            }
            scrollPane.setContent(parent);
        });
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
