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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/18 11:47
 */
public class GroupController {
    
    public void toolBtnClick(ActionEvent event) {
        String gId = ((Button) event.getTarget()).getId().substring(4,6);
        String tId = ((Button) event.getTarget()).getId().substring(7);
        if (StrUtil.isNumber(gId) && StrUtil.isNumber(tId)) {
            Platform.runLater(() -> {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("tools/"+gId+"/tool" + tId + ".fxml"));
                    GadgetsController.ctrl.mainPane.setContent(parent);
                } catch (IOException e1) {
                    LoggerFactory.getLogger(this.getClass()).error(e1.getMessage(), e1);
                }
            });
        }
    }
}
