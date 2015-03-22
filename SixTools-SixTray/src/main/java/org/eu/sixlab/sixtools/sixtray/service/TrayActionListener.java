/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.service;

import org.eu.sixlab.sixtools.common.beans.SixTray;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class TrayActionListener extends MouseAdapter{

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getModifiers() == MouseEvent.BUTTON1_MASK){

        }
    }
    
    public void folderToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(" explorer "+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void fileToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(" explorer "+path);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void websiteToolAction(SixTray sixTray, ActionEvent e) {
        String path = sixTray.getPath();
        try {
            Runtime.getRuntime().exec(" rundll32 url.dll,FileProtocolHandler "+path);
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
                Runtime.getRuntime().exec(exec, null, new File(path).getParentFile());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}