/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.bandeja;

import cn.sixlab.sixtools.comun.bean.SeisBandeja;
import cn.sixlab.sixtools.comun.dao.TrayDao;
import cn.sixlab.sixtools.comun.util.A;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.ToolLoader;
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
public class BandejaService {
    public static BandejaService self;

    public BandejaService(){
        self = this;
    }

    private TrayDao dao = new TrayDao();
    public PopupMenu popupMenu = new PopupMenu();

    public MouseListener trayAction(ToolLoader loader) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
                    Platform.setImplicitExit(false);
                    synchronized (this) {
                        if (null == loader.stage) {
                            LocalTime beginTime = LocalTime.now();
                            Platform.runLater(() -> {
                                loader.load();
                            });
                            LocalTime nowTime = LocalTime.now();
                            while (null == loader.stage) {
                                if (nowTime.compareTo(beginTime.plusMinutes(1)) < 0) {
                                    break;
                                }
                            }
                        } else {
                            if (loader.stage.isShowing()) {
                                Platform.runLater(() -> {
                                    loader.stage.hide();
                                });
                            } else {
                                Platform.runLater(() -> {
                                    loader.stage.show();
                                });
                            }
                        }
                    }
                }
            }
        };
    }

    public void initTray(ToolLoader loader) {
        if (SystemTray.isSupported()){
            SystemTray systemTray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage("logo.png");

            loadMenu(popupMenu, null);

            TrayIcon trayIcon = new TrayIcon(image, getTooltips(), popupMenu);
            trayIcon.addMouseListener(trayAction(loader));
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadMenu(PopupMenu popupMenu, Integer parentId) {
        popupMenu.removeAll();

        initMenus(popupMenu, null == parentId ? C.ROOT_PARENT_ID : parentId);

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
        String command = seisBandeja.getCommand();
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void copyToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String command = seisBandeja.getCommand();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(command),null);
    }

    public void websiteToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String command = seisBandeja.getCommand();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_DEFAULT_IE_COMMAND + command);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void fileToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String command = seisBandeja.getCommand();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_EXPLORER_COMMAND + command,null,new File(command).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void folderToolAction(SeisBandeja seisBandeja, ActionEvent e) {
        String command = seisBandeja.getCommand();
        try {
            Runtime.getRuntime().exec(C.WINDOWS_EXPLORER_COMMAND + command,null,new File(command).getParentFile());
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
