/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/23 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixtray.dao.SixTrayDao;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/23
 * 功能描述：
 * 版本：1.0-snapshot
 */
public class SixTrayAddController implements Initializable{

    public static SixTray sixTray;
    public TextField addName;
    public TextField addOrder;
    public ComboBox addParent;
    public ComboBox addType;
    public TextField addCommand;
    public TextField addPara;
    public TextField addPath;
    
    private ObservableList<SixTray> parentData = FXCollections.observableArrayList();
    private ObservableList<ToolType> toolTypeData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAddParent();
        initToolType();
        if(null!=sixTray){
            addName.setText(sixTray.getTrayName());
            addOrder.setText(sixTray.getToolOrder());
            addCommand.setText(sixTray.getCommand());
            addPara.setText(sixTray.getParams());
            addPath.setText(sixTray.getPath());
        }
    }

    private void initAddParent() {
        List<SixTray> sixTrayList = SixTrayDao.getToolFolders();
        SixTray noParent = new SixTray();
        noParent.setId(0);
        noParent.setTrayName("--不选择--");
        sixTrayList.add(0,noParent);
        parentData.clear();
        parentData.addAll(sixTrayList);
        addParent.setItems(parentData);
        if(null==sixTray){
            addParent.getSelectionModel().selectFirst();
        }else{
            Integer parentId = sixTray.getParentId();
            int i = -1;
            for (SixTray s:sixTrayList){
                i++;
                if(parentId.equals(s.getId())){
                    break;
                }
            }
            addParent.getSelectionModel().select(i);
        }

        addParent.setCellFactory(p -> {
            final ListCell<SixTray> cell = new ListCell<SixTray>() {
                @Override
                protected void updateItem(SixTray sixTray, boolean bln) {
                    super.updateItem(sixTray, bln);
                    if (sixTray != null) {
                        setText(sixTray.getTrayName());
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        });
    }

    private void initToolType() {
        List<ToolType> toolTypeList = ToolType.allToolTypes();
        toolTypeData.clear();
        toolTypeData.addAll(toolTypeList);
        addType.setItems(toolTypeData);
        if(null==sixTray){
            addType.getSelectionModel().selectFirst();
        }else{
            String toolType = sixTray.getToolType();
            int i = -1;
            for (ToolType s:toolTypeList){
                i++;
                if(toolType.equals(s.getToolType())){
                    break;
                }
            }
            addType.getSelectionModel().select(i);
        }

        addType.setCellFactory(p -> {
            final ListCell<ToolType> cell = new ListCell<ToolType>() {
                @Override
                protected void updateItem(ToolType toolType, boolean bln) {
                    super.updateItem(toolType, bln);
                    if (toolType != null) {
                        setText(toolType.getToolTypeName());
                    } else {
                        setText(null);
                    }
                }
            };
            return cell;
        });
    }

    public void choosePath(ActionEvent event) {
        //TODO 选择路径
    }

    public void saveAndContinue(ActionEvent event) {
        //TODO 保存
    }

    public void addClose(ActionEvent event) {
        addName.getScene().getWindow().hide();
    }

    public void saveAndClose(ActionEvent event) {
        saveAndContinue(event);
        saveAndClose(event);
    }
}

class ToolType{
    public static List<ToolType> allToolTypes(){
        List<ToolType> toolTypeList = new ArrayList<>();
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_FOLDER,"目录"));
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_FILE,"文件"));
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_WEBSITE,"网址"));
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_COMMAND, "命令"));
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_TRAY_FOLDER,"Tray文件夹"));
        toolTypeList.add(new ToolType(SixToolsConstants.TOOL_TYPE_COPY_TOOL,"复制工具"));
        return toolTypeList;
    }

    private String toolType;
    private String toolTypeName;

    public ToolType() {
        super();
    }

    public ToolType(String toolType, String toolTypeName) {
        this.toolType = toolType;
        this.toolTypeName = toolTypeName;
    }

    @Override
    public String toString() {
        return toolTypeName;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolTypeName() {
        return toolTypeName;
    }

    public void setToolTypeName(String toolTypeName) {
        this.toolTypeName = toolTypeName;
    }
}

