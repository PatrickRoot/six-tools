/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis.old;

import cn.sixlab.StrUtil;
import cn.sixlab.sixtools.comun.bean.SeisBandeja;
import cn.sixlab.sixtools.comun.bean.SeisTools;
import cn.sixlab.sixtools.comun.bean.ext.ToolType;
import cn.sixlab.sixtools.comun.dao.ToolsDao;
import cn.sixlab.sixtools.comun.dao.TrayDao;
import cn.sixlab.sixtools.comun.util.C;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.time.LocalTime;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 13:27
 */
public class SeisService {

    private SeisController controller;
    public static final ObservableList<SeisBandeja> searchData = FXCollections.observableArrayList();
    public static final ObservableList<SeisBandeja> superData = FXCollections.observableArrayList();
    public static final ObservableList<SeisBandeja> tableData = FXCollections.observableArrayList();
    private ObservableList<ToolType> toolTypeData = FXCollections.observableArrayList();
    private TrayDao dao = new TrayDao();
    private ToolsDao toolDao = new ToolsDao();
    private SeisBandeja theBandeja;

    public SeisService(SeisController seisController) {
        this.controller = seisController;
    }


    public void initCombo() {
        loadSuperComboData();

        controller.searchCombo.setItems(searchData);
        controller.searchCombo.getSelectionModel().selectFirst();
        controller.searchCombo.setCellFactory(superComboCellFactory());

        controller.parentCombo.setItems(superData);
        controller.parentCombo.getSelectionModel().selectFirst();
        controller.parentCombo.setCellFactory(superComboCellFactory());

        loadTypeCombo();
    }

    private void loadTypeCombo() {
        List<ToolType> toolTypeList = ToolType.allToolTypes();
        toolTypeData.clear();
        toolTypeData.addAll(toolTypeList);
        controller.typeCombo.setItems(toolTypeData);
        controller.typeCombo.getSelectionModel().selectFirst();

        controller.typeCombo.setCellFactory(p -> new ListCell<ToolType>() {
            @Override
            protected void updateItem(ToolType toolType, boolean bln) {
                super.updateItem(toolType, bln);
                if (toolType != null) {
                    setText(toolType.getToolTypeName());
                } else {
                    setText(null);
                }
            }
        });
    }

    private void loadSuperComboData() {
        List<SeisBandeja> superList = dao.getToolFolders();

        SeisBandeja noParent = new SeisBandeja();
        noParent.setId(0);
        noParent.setTrayName("-无父目录-");
        superList.add(0, noParent);
        superData.clear();
        superData.addAll(superList);

        SeisBandeja notSelect = new SeisBandeja();
        notSelect.setId(-1);
        notSelect.setTrayName("--不选择--");
        superList.add(0, notSelect);
        searchData.clear();
        searchData.addAll(superList);
    }

    private Callback<ListView, ListCell> superComboCellFactory() {
        return p -> new ListCell<SeisBandeja>() {
            @Override
            protected void updateItem(SeisBandeja seisBandeja, boolean bln) {
                super.updateItem(seisBandeja, bln);
                if (seisBandeja != null) {
                    setText(seisBandeja.getTrayName());
                } else {
                    setText(null);
                }
            }
        };
    }

    public void initTable() {
        initCellValueFactory();
        loadTableData();
        controller.toolTable.setItems(tableData);

        controller.toolTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                SeisBandeja seisBandeja = (SeisBandeja) controller.toolTable.getSelectionModel().getSelectedItem();
                theBandeja = seisBandeja;
                controller.nameField.setText(seisBandeja.getTrayName());
                controller.orderField.setText(seisBandeja.getToolOrder());
                controller.commandField.setText(seisBandeja.getCommand());
                controller.pathField.setText(seisBandeja.getPath());
                controller.paramField.setText(seisBandeja.getParams());

                controller.typeCombo.getItems().stream().filter(type -> (
                        ((ToolType) type).getToolType().toString().equals(seisBandeja.getToolType())
                )).forEach(type -> {
                    controller.typeCombo.getSelectionModel().select(type);
                });

                controller.parentCombo.getItems().stream().filter(type -> (
                        ((SeisBandeja) type).getId().equals(seisBandeja.getParentId())
                )).forEach(type -> {
                    controller.parentCombo.getSelectionModel().select(type);
                });

                controller.tabPane.getSelectionModel().select(controller.opeTab);
            }
        });
    }

    private void loadTableData() {
        search(-1);
    }

    private void initCellValueFactory() {
        controller.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        controller.nameColumn.setCellValueFactory(new PropertyValueFactory("trayName"));
        controller.pathColumn.setCellValueFactory(new PropertyValueFactory("path"));
        controller.commandColumn.setCellValueFactory(new PropertyValueFactory("command"));
        controller.paramColumn.setCellValueFactory(new PropertyValueFactory("params"));
        controller.typeColumn.setCellValueFactory(new PropertyValueFactory("toolType"));
        controller.orderColumn.setCellValueFactory(new PropertyValueFactory("toolOrder"));
        controller.superColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
    }

    public void initToolTab() {
        List<SeisTools> seisBandejaList = toolDao.getAllTools();
        controller.flowPane.getChildren().clear();
        seisBandejaList.stream().forEach(tool -> {
            VBox vBox = new VBox();
            vBox.setId(tool.getId().toString());
            vBox.setPrefHeight(100);
            vBox.setPrefWidth(80);

            ImageView imageView = new ImageView();
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            Image image = new Image("logo.png");
            imageView.setImage(image);
            vBox.getChildren().add(imageView);

            Label label = new Label(tool.getToolName());
            label.setAlignment(Pos.CENTER);
            label.setContentDisplay(ContentDisplay.CENTER);
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefWidth(80);
            label.setPrefHeight(20);
            vBox.getChildren().add(label);

            controller.flowPane.getChildren().add(vBox);
            Insets insets = new Insets(5.0, 5.0, 5.0, 5.0);
            controller.flowPane.setMargin(vBox, insets);

            vBox.setOnMouseClicked(e -> {
                if(e.getClickCount()==2){
                    Launcher.launchTool(tool.getId());
                }
            });
        });
    }

    public void search(Integer parentId) {
        List<SeisBandeja> seisBandejaList;
        if(parentId == -1){
            seisBandejaList = dao.getAll();
        }else{
            seisBandejaList = dao.getSubTrays(parentId);
        }
        tableData.clear();
        tableData.addAll(seisBandejaList);
    }

    public void delete(SeisBandeja seisBandeja) {
        dao.delete(seisBandeja.getId());
        refresh();
    }

    public void refresh() {
        Integer parentId = ((SeisBandeja) controller.searchCombo.getValue()).getId();
        search(parentId);

        initToolTab();

        SeisC.SERVICE.loadMenu(SeisC.SERVICE.popupMenu, C.ROOT_PARENT_ID);
    }

    public void reset() {
        theBandeja = null;
        controller.nameField.setText("");
        controller.orderField.setText("");
        controller.paramField.setText("");
        controller.pathField.setText("");
        controller.commandField.setText("");
        loadSuperComboData();
        controller.typeCombo.getSelectionModel().selectFirst();
        controller.tipLabel.setText("");
    }

    public void confirm() {
        String trayName = controller.nameField.getText();
        if(StrUtil.isEmpty(trayName)){
            controller.nameField.requestFocus();
            controller.tipLabel.setText("名字必填");
            return;
        }

        String order = controller.orderField.getText();
        String param = controller.paramField.getText();
        String path  = controller.pathField.getText();
        String command = controller.commandField.getText();

        String type = String.valueOf(((ToolType) controller.typeCombo.getValue()).getToolType());
        Integer superId = ((SeisBandeja)controller.parentCombo.getValue()).getId();
        if(null == theBandeja){
            SeisBandeja newBandeja = new SeisBandeja();
            newBandeja.setTrayName(trayName);
            newBandeja.setToolOrder(order);
            newBandeja.setToolType(type);
            newBandeja.setParams(param);
            newBandeja.setPath(path);
            newBandeja.setCommand(command);
            newBandeja.setParentId(superId);
            dao.insert(newBandeja);
            controller.tipLabel.setText("添加成功。" + LocalTime.now().toString());
        }else{
            theBandeja.setTrayName(trayName);
            theBandeja.setToolOrder(order);
            theBandeja.setToolType(type);
            theBandeja.setParams(param);
            theBandeja.setPath(path);
            theBandeja.setCommand(command);
            theBandeja.setParentId(superId);
            dao.update(theBandeja);
            controller.tipLabel.setText("更新成功。"+ LocalTime.now().toString());
        }
        refresh();
    }
}
