/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis;

import cn.sixlab.sixtools.bandeja.BandejaService;
import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.gadgets.Gadgets;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;


/**
 * cn.sixlab.sixtools.seis.Seis main方法,启动托盘工具
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 12:21
 */
public class Seis extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
        Platform.runLater(()->{
            Gadgets.title = "Seis Tools : " + A.get();
            Gadgets gadgets = new Gadgets();
            gadgets.load(gadgets);
            BandejaService service = new BandejaService();
            service.initTray(gadgets);
        });
    }
}
