/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixtray.service;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.Constant;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * SixTray的actionListener类
 *
 * @author 六楼的雨/loki
 * @date 2015/3/21 19:46
 */
public class SixTrayActionListener {
    
    public void folderToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(Constant.WINDOWS_EXPLORER_COMMAND+path,null,new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void fileToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(Constant.WINDOWS_EXPLORER_COMMAND+path,null,new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void websiteToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(Constant.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void commandToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        String para = sixTray.getParams();
        String comm = sixTray.getCommand();
        String fileName = new File(path).getName();
        String exec = comm + " " + fileName + " " + para;
        try {
            Runtime.getRuntime().exec(exec, null, new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void copyToolAction(SixTray sixTray, ActionEvent e) {
        String content = sixTray.getPath();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(content),null);
    }
}