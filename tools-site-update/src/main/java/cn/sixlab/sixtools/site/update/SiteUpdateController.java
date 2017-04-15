/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.site.update;

import cn.sixlab.sixtools.dao.base.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/22 19:30
 */
public class SiteUpdateController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(SiteUpdateController.class);
    public static SiteUpdateController self;
    
    public WebView leftText;
    public WebView rightText;
    public Label tipsLabel;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
    }
    
    public void itemClick(ActionEvent event) {
        Button btn = (Button) event.getTarget();
        String id = btn.getId();
        
        Crawler crawler = Crawler.init(id);
        
        String leftHtml = crawler.fetchOld();
        String rightHtml = crawler.fetchNew();
        leftText.getEngine().loadContent(leftHtml);
        rightText.getEngine().loadContent(rightHtml);
    }
}