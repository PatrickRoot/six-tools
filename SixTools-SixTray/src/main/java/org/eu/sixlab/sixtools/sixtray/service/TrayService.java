/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.service;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixtray.dao.SixTrayDao;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
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

        initMenuItem(popup, SixToolsConstants.ROOT_PARENT_ID);

        popup.add(new MenuItem("-"));
        MenuItem trayItem3 = new MenuItem("百度一下");
        trayItem3.addActionListener(e->{
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clipboard.getContents(null);

            try {
                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
                    if( null!=text && !"".equals(text) ){
                        String path = SixToolsConstants.BAIDU_SEARCH_STRING+text;

                        Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        popup.add(trayItem3);

        MenuItem trayItem2 = new MenuItem("豆瓣电影");
        trayItem2.addActionListener(e->{
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clipboard.getContents(null);

            try {
                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
                    if( null!=text && !"".equals(text) ){
                        String path = SixToolsConstants.DOUBAN_SEARCH_STRING+text;

                        Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        popup.add(trayItem2);

        popup.add(new MenuItem("-"));
        MenuItem trayItem1 = new MenuItem("退出");
        trayItem1.addActionListener(e->{
            System.exit(0);
        });

        popup.add(trayItem1);
    }

    private static void initMenuItem(Menu popup, Integer parentId){

        java.util.List<SixTray> sixTrayList = SixTrayDao.getSubTrays(parentId);

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
        }else if(SixToolsConstants.TOOL_TYPE_COPY_TOOL.equals(toolType)){
            trayItem.addActionListener(e->{
                new TrayActionListener().copyToolAction(sixTray, e);
            });
        }

    }
}
