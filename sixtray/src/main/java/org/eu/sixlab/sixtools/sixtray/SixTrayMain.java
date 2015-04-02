/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * SixTray的Main方法
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public class SixTrayMain extends Application{

    public static PopupMenu popup;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));

        primaryStage.setTitle("SixTray");
        primaryStage.setScene(new Scene(parent, 800, 500));
        primaryStage.setOnCloseRequest(e->{
            primaryStage.hide();
        });
        initTray(primaryStage);
    }
    
    private void initTray(Stage primaryStage) {
        TrayIcon trayIcon = null;
        // 判断系统是否支持系统托盘
        if (SystemTray.isSupported()){
            // 创建系统托盘
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("logo.png");
            // 创建弹出菜单
            popup = new PopupMenu();
            new SixTrayController().loadPopupMenu(popup, SixToolsConstants.ROOT_PARENT_ID);
            // 创建trayIcon
            trayIcon = new TrayIcon(image, "待实现", popup);
            trayIcon.addMouseListener(trayListener(primaryStage));
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public MouseListener trayListener(Stage primaryStage){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getModifiers() == MouseEvent.BUTTON1_MASK){
                    Platform.setImplicitExit(false);
                    if(primaryStage.isShowing()){
                        Platform.runLater(()->{
                            primaryStage.hide();
                        });
                    }else{
                        Platform.runLater(()->{
                            primaryStage.show();
                        });
                    }
                }
            }
        };
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
