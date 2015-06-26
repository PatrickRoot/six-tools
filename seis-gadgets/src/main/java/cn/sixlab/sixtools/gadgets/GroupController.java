/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.gadgets;

import cn.sixlab.sixtools.comun.base.BaseController;
import cn.sixlab.sixtools.comun.bean.db.SeisTools;
import cn.sixlab.sixtools.comun.util.D;
import cn.sixlab.sixtools.comun.util.UI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/18 11:47
 */
public class GroupController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(GroupController.class);
    private Dao dao = D.dao;
    
    public void toolBtnClick(ActionEvent event) {
        String btnId = ((Button) event.getTarget()).getId();
        SeisTools seisTools = dao.fetch(SeisTools.class, Cnd.where("btnId", "=", btnId));
        Platform.runLater(() -> {
            Parent parent = null;
            try {
                String className = seisTools.getClassName();
                Class clz = Class.forName(className);
                parent = FXMLLoader.load(clz.getResource("tool.fxml"));
            } catch (Exception e1) {
                parent = UI.nullParent();
                logger.error(e1.getMessage(), e1);
            }
            GadgetsController.self.mainPane.setContent(parent);
        });
    }
}
