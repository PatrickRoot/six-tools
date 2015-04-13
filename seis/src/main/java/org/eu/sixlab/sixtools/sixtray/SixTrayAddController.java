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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.eu.sixlab.sixtools.comun.bean.old.SixTray;
import org.eu.sixlab.sixtools.comun.dao.TrayDao;
import org.eu.sixlab.sixtools.comun.util.C;
import org.eu.sixlab.sixutil.StrUtil;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SixTray的添加界面的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/3/23 19:46
 */
public class SixTrayAddController implements Initializable{

    public static SixTray oldTray;
    public TextField addName;
    public TextField addOrder;
    public ComboBox addParent;
    public ComboBox addType;
    public TextField addCommand;
    public TextField addPara;
    public TextField addPath;
    public Label tipsLabel;
    
    private ObservableList<SixTray> parentData = FXCollections.observableArrayList();
    private ObservableList<ToolType> toolTypeData = FXCollections.observableArrayList();
    private Integer id;
    private TrayDao dao = new TrayDao();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initAddParent();
        initToolType();
        if(null!= oldTray){
            addName.setText(oldTray.getTrayName());
            addOrder.setText(oldTray.getToolOrder());
            addCommand.setText(oldTray.getCommand());
            addPara.setText(oldTray.getParams());
            addPath.setText(oldTray.getPath());
            id = oldTray.getId();
        }
    }

    private void initAddParent() {
        List<SixTray> sixTrayList = dao.getToolFolders();
        SixTray noParent = new SixTray();
        noParent.setId(0);
        noParent.setTrayName("--不选择--");
        sixTrayList.add(0,noParent);
        parentData.clear();
        parentData.addAll(sixTrayList);
        addParent.setItems(parentData);
        if(null== oldTray){
            addParent.getSelectionModel().selectFirst();
        }else{
            Integer parentId = oldTray.getParentId();
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
            return new ListCell<SixTray>() {
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
        });
    }

    private void initToolType() {
        List<ToolType> toolTypeList = ToolType.allToolTypes();
        toolTypeData.clear();
        toolTypeData.addAll(toolTypeList);
        addType.setItems(toolTypeData);
        if(null== oldTray){
            addType.getSelectionModel().selectFirst();
        }else{
            String toolType = oldTray.getToolType();
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
            return new ListCell<ToolType>() {
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
        });
    }

    public void choosePath(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("选择路径");
        File file = dc.showDialog(addName.getScene().getWindow());
        addPath.setText(file.getPath());
    }

    public void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文件");
        File file = fc.showOpenDialog(addName.getScene().getWindow());
        addPath.setText(file.getPath());
    }

    public void saveAndContinue(ActionEvent event) {
        String trayName = addName.getText();
        if( StrUtil.isEmpty(trayName)){
            tipsLabel.setText("名字不能为空");
            addName.requestFocus();
            return;
        }
        SixTray sixTray = new SixTray();
        sixTray.setId(id);
        sixTray.setTrayName(trayName);
        sixTray.setToolOrder(addOrder.getText());
        sixTray.setPath(addPath.getText());
        sixTray.setCommand(addCommand.getText());
        sixTray.setParams(addPara.getText());
        sixTray.setParentId(((SixTray)addParent.getValue()).getId());
        sixTray.setToolType(((ToolType)addType.getValue()).getToolType());
        if(null==id){
            dao.insert(sixTray);
            tipsLabel.setText("添加工具成功：" + trayName);
        }else{
            dao.update(sixTray);
            tipsLabel.setText("编辑工具成功：" + trayName);
        }




        List<SixTray> sixTrayTableList = dao.getAll();
        SixTrayMainController.tableData.clear();
        SixTrayMainController.tableData.addAll(sixTrayTableList);




        List<SixTray> sixTrayComboList = dao.getToolFolders();

        SixTray notSelect = new SixTray();
        notSelect.setId(-1);
        notSelect.setTrayName("--不选择--");
        sixTrayComboList.add(0, notSelect);

        SixTray noParent = new SixTray();
        noParent.setId(0);
        noParent.setTrayName("-无父目录-");
        sixTrayComboList.add(1, noParent);

        SixTrayMainController.comboData.clear();
        SixTrayMainController.comboData.addAll(sixTrayComboList);





        SixTrayMain.popup.removeAll();
        new SixTrayController().loadPopupMenu(SixTrayMain.popup, C.ROOT_PARENT_ID);
    }

    public void addClose(ActionEvent event) {
        addName.getScene().getWindow().hide();
    }

    public void saveAndClose(ActionEvent event) {
        saveAndContinue(event);
        addClose(event);
    }
}

class ToolType{

    private String toolType;
    private String toolTypeName;

    public ToolType() {
        super();
    }

    public ToolType(String toolType, String toolTypeName) {
        this.toolType = toolType;
        this.toolTypeName = toolTypeName;
    }

    public static List<ToolType> allToolTypes(){
        List<ToolType> toolTypeList = new ArrayList<>();
        toolTypeList.add(new ToolType(C.TOOL_TYPE_FOLDER,"目录"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_FILE,"文件"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_WEBSITE,"网址"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_COMMAND, "命令"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_TRAY_FOLDER,"Tray文件夹"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_COPY_TOOL,"复制工具"));
        return toolTypeList;
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

