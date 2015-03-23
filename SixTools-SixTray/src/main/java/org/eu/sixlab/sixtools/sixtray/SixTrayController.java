/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/23 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixtray.dao.SixTrayDao;
import org.eu.sixlab.sixtools.sixtray.service.TrayActionListener;

import java.awt.*;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/23
 * 功能描述：
 * 版本：1.0-snapshot
 */
public class SixTrayController {

    public void loadPopupMenu(PopupMenu pM, Integer parentId){
        initMenus(pM, parentId);

        pM.add(new MenuItem("-"));
        MenuItem exitTrayItem = new MenuItem("退出");
        exitTrayItem.addActionListener(e -> {
            System.exit(0);
        });

        pM.add(exitTrayItem);
    }

    public void initMenus(Menu popup, Integer parentId){
        java.util.List<SixTray> sixTrayList = SixTrayDao.getSubTrays(parentId);

        for (SixTray sixTray : sixTrayList) {
            String trayName = sixTray.getTrayName();
            if(SixToolsConstants.TOOL_TYPE_TRAY_FOLDER.equals(sixTray.getToolType())){
                Menu menu =  new Menu(trayName);
                initMenus(menu, sixTray.getId());
                popup.add(menu);
            }else{
                MenuItem trayItem = new MenuItem(trayName);
                addListener(sixTray, trayItem);
                popup.add(trayItem);
            }
        }
    }

    private void addListener(SixTray sixTray, MenuItem trayItem) {
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














//        popup.add(new MenuItem("-"));
//        MenuItem trayItem3 = new MenuItem("百度一下");
//        trayItem3.addActionListener(e->{
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            Transferable t = clipboard.getContents(null);
//
//            try {
//                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
//                    if( null!=text && !"".equals(text) ){
//                        String path = SixToolsConstants.BAIDU_SEARCH_STRING+text;
//
//                        Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
//                    }
//                }
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//        popup.add(trayItem3);
//
//        MenuItem trayItem2 = new MenuItem("豆瓣电影");
//        trayItem2.addActionListener(e->{
//            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//            Transferable t = clipboard.getContents(null);
//
//            try {
//                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
//                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
//                    if( null!=text && !"".equals(text) ){
//                        String path = SixToolsConstants.DOUBAN_SEARCH_STRING+text;
//
//                        Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
//                    }
//                }
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        });
//        popup.add(trayItem2);
}
