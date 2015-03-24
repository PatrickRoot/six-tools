/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/23 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixtray.dao.SixTrayDao;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/23
 * 功能描述：
 * 版本：1.0-snapshot
 */
public class SixTrayMainController implements Initializable {

    public ComboBox comboBox;
    public TableColumn tcId;
    public TableColumn tcName;
    public TableColumn tcPath;
    public TableColumn tcCommand;
    public TableColumn tcPara;
    public TableColumn tcType;
    public TableColumn tcOrder;
    public TableColumn tcParent;
    public TableView tableView;
    public Label tipsTool;

    public static final ObservableList<SixTray> comboData = FXCollections.observableArrayList();
    public static final ObservableList<SixTray> tableData = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox.setItems(comboData);
        loadComboData();
        comboBox.getSelectionModel().selectFirst();

        comboBox.setCellFactory(p -> {
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

        tcId.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("trayName"));
        tcPath.setCellValueFactory(new PropertyValueFactory("path"));
        tcCommand.setCellValueFactory(new PropertyValueFactory("command"));
        tcPara.setCellValueFactory(new PropertyValueFactory("params"));
        tcType.setCellValueFactory(new PropertyValueFactory("toolType"));
        tcOrder.setCellValueFactory(new PropertyValueFactory("toolOrder"));
        tcParent.setCellValueFactory(new PropertyValueFactory("parentId"));

        tableView.addEventFilter(MouseEvent.MOUSE_CLICKED,e->{
            if( e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() ==  2  ){
                SixTray sixTray = (SixTray) tableView.getSelectionModel().getSelectedItem();
                editTray(sixTray);
            }
        });

        tableView.setItems(tableData);
        loadTableData();
    }

    private void loadComboData() {
        List<SixTray> sixTrayList = SixTrayDao.getToolFolders();

        SixTray notSelect = new SixTray();
        notSelect.setId(-1);
        notSelect.setTrayName("--不选择--");
        sixTrayList.add(0, notSelect);

        SixTray noParent = new SixTray();
        noParent.setId(0);
        noParent.setTrayName("-无父目录-");
        sixTrayList.add(1, noParent);

        comboData.clear();
        comboData.addAll(sixTrayList);
    }

    private void loadTableData() {
        List<SixTray> sixTrayList = SixTrayDao.getAll();
        tableData.clear();
        tableData.addAll(sixTrayList);
    }

    public void addTool(ActionEvent event) {
        editTray(null);
    }

    private void editTray(SixTray sixTray){
        SixTrayAddController.oldTray = sixTray;
        Stage stage = new Stage();
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("addTray.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent, 400, 300);
        stage.setScene(scene);
        stage.setTitle(null == sixTray ? "Add Tray" : "修改：" + sixTray.getTrayName());
        stage.show();
    }

    public void deleteTool(ActionEvent event) {
        SixTray sixTray = (SixTray) tableView.getSelectionModel().getSelectedItem();
        if(null!=sixTray){
            SixTrayDao.delete(sixTray.getId());
            refreshTable(event);
            refreshTools(event);
        }
    }

    public void refreshTable(ActionEvent event) {
        SixTray sixTray = (SixTray) comboBox.getValue();
        List<SixTray> sixTrayList;
        if(sixTray.getId() == -1){
            sixTrayList = SixTrayDao.getAll();
        }else{
            sixTrayList = SixTrayDao.getSubTrays(sixTray.getId());
        }
        tableData.clear();
        tableData.addAll(sixTrayList);
    }

    public void refreshTools(ActionEvent event) {
        SixTrayMain.popup.removeAll();
        new SixTrayController().loadPopupMenu(SixTrayMain.popup, SixToolsConstants.ROOT_PARENT_ID);
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
