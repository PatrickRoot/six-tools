/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.service;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class SixTrayActionListener {
    
    public void folderToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_EXPLORER_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void fileToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_EXPLORER_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void websiteToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(SixToolsConstants.WINDOWS_DEFAULT_IE_COMMAND+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void commandToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        String para = sixTray.getParams();
        String comm = sixTray.getCommand();

        String exec = comm + " " + path + " " + para;
        try {
            File runtimeFolder =new File(path).getParentFile();
            Process p = Runtime.getRuntime().exec(exec, null, runtimeFolder);
            System.out.println(p.waitFor());
        } catch (IOException e1) {
            e1.printStackTrace();
        }catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public void copyToolAction(SixTray sixTray, ActionEvent e) {
        String content = sixTray.getPath();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(content),null);
    }
}