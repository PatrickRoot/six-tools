/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis;

import cn.sixlab.sixtools.comun.bean.SeisBandeja;
import cn.sixlab.sixtools.comun.dao.TrayDao;
import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.gadgets.Gadgets;
import javafx.application.Platform;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 13:37
 */
public class SeisTrayService {
    public static SeisTrayService self;

    public SeisTrayService(){
        self = this;
    }

    private TrayDao dao = new TrayDao();
    public PopupMenu popupMenu = new PopupMenu();

    public void initTray() {
        if (SystemTray.isSupported()){
            SystemTray systemTray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("logo.png");

            loadMenu(popupMenu, C.ROOT_PARENT_ID);

            TrayIcon trayIcon = new TrayIcon(image, getTooltips(), popupMenu);
            trayIcon.addMouseListener(trayAction());
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public MouseListener trayAction() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                    Platform.setImplicitExit(false);
                    synchronized (this) {
                        if (null == Gadgets.stage) {
                            LocalTime beginTime = LocalTime.now();
                            Platform.runLater(() -> {
                                new Gadgets().load();
                            });
                            while (null == Gadgets.stage) {
                                LocalTime nowTime = LocalTime.now();
                                if(nowTime.compareTo(beginTime.plusMinutes(1))<0){
                                    break;
                                }
                            }
                        } else {
                            if (Gadgets.stage.isShowing()) {
                                Platform.runLater(() -> {
                                    Gadgets.stage.hide();
                                });
                            } else {
                                Platform.runLater(() -> {
                                    Gadgets.stage.show();
                                });
                            }
                        }
                    }
                }
            }
        };
    }

    public void loadMenu(PopupMenu popupMenu, Integer parentId) {
        popupMenu.removeAll();

        initMenus(popupMenu, parentId);

        popupMenu.add(new MenuItem("-"));
        MenuItem trayItem3 = new MenuItem("百度一下");
        trayItem3.addActionListener(e->{
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clipboard.getContents(null);

            try {
                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
                    if( null!=text && !"".equals(text) ){
                        String path = C.BAIDU_SEARCH_STRING+text;

                        Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND+path);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        popupMenu.add(trayItem3);

        MenuItem trayItem2 = new MenuItem("豆瓣电影");
        trayItem2.addActionListener(e->{
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable t = clipboard.getContents(null);

            try {
                if (null != t && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String text = (String)t.getTransferData(DataFlavor.stringFlavor);
                    if( null!=text && !"".equals(text) ){
                        String path = C.DOUBAN_SEARCH_STRING+text;

                        Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND+path);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        popupMenu.add(trayItem2);

        MenuItem spaceMenuItem2 = new MenuItem("-");
        popupMenu.add(spaceMenuItem2);

        MenuItem menuItem = new MenuItem("退出");
        menuItem.addActionListener(e -> {
            System.exit(0);
        });
        popupMenu.add(menuItem);
    }

    public void initMenus(Menu popup, Integer parentId){
        List<SeisBandeja> seisBandejaList = dao.getSubTrays(parentId);

        for (SeisBandeja seisBandeja : seisBandejaList) {
            String trayName = seisBandeja.getTrayName();
            if( C.TOOL_TYPE_TRAY_FOLDER.equals(seisBandeja.getToolType())){
                Menu menu =  new Menu(trayName);
                initMenus(menu, seisBandeja.getId());
                popup.add(menu);
            }else{
                MenuItem trayItem = new MenuItem(trayName);
                addListener(seisBandeja, trayItem);
                popup.add(trayItem);
            }
        }
    }

    private void addListener(SeisBandeja seisBandeja, MenuItem trayItem) {
        String toolType = seisBandeja.getToolType();

        if( C.TOOL_TYPE_FOLDER.equals(toolType)){
            trayItem.addActionListener(e->{folderToolAction(seisBandeja, e);});
        }else if( C.TOOL_TYPE_FILE.equals(toolType)){
            trayItem.addActionListener(e->{fileToolAction(seisBandeja, e);});
        }else if( C.TOOL_TYPE_WEBSITE.equals(toolType)){
            trayItem.addActionListener(e->{websiteToolAction(seisBandeja, e);});
        }else if( C.TOOL_TYPE_COMMAND.equals(toolType)){
            trayItem.addActionListener(e->{commandToolAction(seisBandeja, e);});
        }else if( C.TOOL_TYPE_COPY_TOOL.equals(toolType)){
            trayItem.addActionListener(e->{copyToolAction(seisBandeja, e);});
        }
    }

    public void commandToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String path = seisBandeja.getPath();
        String para = seisBandeja.getParams();
        String comm = seisBandeja.getCommand();
        String fileName = new File(path).getName();
        String exec = comm + " " + fileName + " " + para;
        try {
            Runtime.getRuntime().exec(exec, null, new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void copyToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String content = seisBandeja.getPath();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(content),null);
    }

    public void websiteToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String path = seisBandeja.getPath();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void fileToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String path = seisBandeja.getPath();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_EXPLORER_COMMAND+path,null,new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void folderToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String path = seisBandeja.getPath();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_EXPLORER_COMMAND+path,null,new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public String getTooltips() {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        sb.append(" 周\n");
        sb.append(A.get());
        return sb.toString();
    }
}
