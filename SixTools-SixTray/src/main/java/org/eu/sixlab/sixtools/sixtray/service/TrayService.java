/* 
 **************************************************************************************
 * Copyright www.ebidding.com.cn 2015/2/17 Authors: 曹林伟 <caolinwei@ebidding.com.cn>*
 **************************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.service;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixtray.dao.TrayDao;

import java.awt.*;

/**
 * 作者：曹林伟
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：2.0.1
 */
public class TrayService {

    private static PopupMenu popup;

    public static void trayIcon(){

        TrayIcon trayIcon = null;
        if (SystemTray.isSupported()) // 判断系统是否支持系统托盘
        {
            SystemTray tray = SystemTray.getSystemTray(); // 创建系统托盘
            Image image = Toolkit.getDefaultToolkit().getImage("logo.png");

            // 创建弹出菜单
            initPopupMenu();

            trayIcon = new TrayIcon(image, "SixTools-SixTray", popup);// 创建trayIcon

            trayIcon.addMouseListener(new TrayActionListener());
            try {
                tray.add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void initPopupMenu(){
        popup = new PopupMenu();

        initMenuItem(popup, 0);

        popup.add(new MenuItem("-"));
        MenuItem trayItem1 = new MenuItem("退出");
        trayItem1.addActionListener(e->{
            System.exit(0);
        });

        popup.add(trayItem1);
    }

    private static void initMenuItem(Menu popup, Integer parentId){

        java.util.List<SixTray> sixTrayList = TrayDao.getSubTrays(parentId);

        for (SixTray sixTray : sixTrayList) {
            String trayName = sixTray.getTrayName();

            if(SixToolsConstants.TOOL_TYPE_TRAY_FOLDER.equals(sixTray.getToolType())){
                Menu menu =  new Menu(trayName);

                initMenuItem(menu, sixTray.getId());

                popup.add(menu);
            }else{

                MenuItem trayItem = new MenuItem(trayName);
                addListener(sixTray, trayItem);
                popup.add(trayItem);
            }

        }
    }

    private static void addListener(SixTray sixTray, MenuItem trayItem) {
        String toolType = sixTray.getToolType();

        if(SixToolsConstants.TOOL_TYPE_FOLDER.equals(toolType)){
            trayItem.addActionListener(e->{
                new TrayActionListener().folderToolAction(sixTray, e);
            });
        }else if(SixToolsConstants.TOOL_TYPE_FILE.equals(toolType)){
            trayItem.addActionListener(e->{
                new TrayActionListener().fileToolAction(sixTray, e);
            });
        }else if(SixToolsConstants.TOOL_TYPE_WEBSITE.equals(toolType)){
            trayItem.addActionListener(e->{
                new TrayActionListener().websiteToolAction(sixTray, e);
            });
        }else if(SixToolsConstants.TOOL_TYPE_COMMAND.equals(toolType)){
            trayItem.addActionListener(e->{
                new TrayActionListener().commandToolAction(sixTray, e);
            });
        }else if(SixToolsConstants.TOOL_TYPE_TRAY_FOLDER.equals(toolType)){
            trayItem.addActionListener(e->{

            });
        }

    }
}
