/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.tray;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.ToolType;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsTray;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/19 17:00
 */
public class TrayController extends BaseController implements Initializable{
    private static Logger logger = LoggerFactory.getLogger(TrayController.class);
    public static TrayController self;
    public Label tipsLabel;
    private Dao dao = D.dao;

    private final ObservableList<ToolsTray> tableData = FXCollections.observableArrayList();
    private final ObservableList<ToolsTray> superData = FXCollections.observableArrayList();
    private final ObservableList<ToolsTray> searchData = FXCollections.observableArrayList();
    private final ObservableList<ToolType> typeData = FXCollections.observableArrayList();

    public ComboBox searchCombo;
    public TextField nameField;
    public TextField orderField;
    public ComboBox typeCombo;
    public ComboBox parentCombo;
    public TextField pathField;
    public TextArea commandText;
    public TableView toolTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn commandColumn;
    public TableColumn typeColumn;
    public TableColumn orderColumn;
    public TableColumn superColumn;
    private Integer id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
        initData();
        initCombo();
        initTable();
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            tableData.clear();
            superData.clear();
            typeData.clear();
            searchData.clear();
        }finally {
            super.finalize();
        }
    }

    private void initCombo() {

        List<ComboBox> superComboBoxes = Arrays.asList(parentCombo, searchCombo);
        parentCombo.setItems(superData);
        searchCombo.setItems(searchData);
        superComboBoxes.stream().forEach(comboBox -> {
            comboBox.getSelectionModel().selectFirst();
            comboBox.setCellFactory(param -> new ListCell<ToolsTray>() {
                @Override
                protected void updateItem(ToolsTray toolsTray, boolean bln) {
                    super.updateItem(toolsTray, bln);
                    if (toolsTray != null) {
                        setText(toolsTray.getTrayName());
                    } else {
                        setText(null);
                    }
                }
            });
        });

        typeCombo.setItems(typeData);
        typeCombo.getSelectionModel().selectLast();
        typeCombo.setCellFactory(p -> new ListCell<ToolType>() {
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

    private void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("trayName"));
        commandColumn.setCellValueFactory(new PropertyValueFactory("command"));
        typeColumn.setCellValueFactory(new PropertyValueFactory("toolType"));
        orderColumn.setCellValueFactory(new PropertyValueFactory("toolOrder"));
        superColumn.setCellValueFactory(new PropertyValueFactory("parentId"));

        toolTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                //TODO (click count == 2)===( double click)?
                ToolsTray bandeja = (ToolsTray) toolTable.getSelectionModel().getSelectedItem();
                if (null != bandeja && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPlan(bandeja);
                }
            } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                delete(null);
            }
        });

        toolTable.setItems(tableData);
    }

    private void modifyPlan(ToolsTray bandeja) {
        tipsLabel.setText("修改任务中："+bandeja.getTrayName());
        id = bandeja.getId();
        nameField.setText(bandeja.getTrayName());
        commandText.setText(bandeja.getCommand());
    }

    private void initData(){

        typeData.clear();
        typeData.addAll(ToolType.allToolTypes());

        List<ToolsTray> bandejaList = dao.query(ToolsTray.class, Cnd.where("toolType","=",C.TOOL_TYPE_TRAY_FOLDER));

        superData.clear();
        ToolsTray bandeja1 = new ToolsTray();
        bandeja1.setId(C.ROOT_PARENT_ID);
        bandeja1.setTrayName("--无父目录--");
        superData.add(bandeja1);
        superData.addAll(bandejaList);

        searchData.clear();
        ToolsTray bandeja = new ToolsTray();
        bandeja.setTrayName("--全部--");
        searchData.add(bandeja);
        searchData.add(bandeja1);
        searchData.addAll(bandejaList);

        tableData.clear();
        tableData.addAll(dao.query(ToolsTray.class, null));
    }

    public void search(ActionEvent event) {

        ToolsTray bandeja = (ToolsTray) searchCombo.getSelectionModel().getSelectedItem();
        List<ToolsTray> bandejaList;
        if(null==bandeja || null==bandeja.getId()){
            bandejaList = dao.query(ToolsTray.class, null);
        }else{
            bandejaList = dao.query(ToolsTray.class, Cnd.where("parentId","=", bandeja.getId()));
        }
        tableData.clear();
        tableData.addAll(bandejaList);

    }

    public void delete(ActionEvent event) {
        ToolsTray bandeja = (ToolsTray) toolTable.getSelectionModel().getSelectedItem();
        if (null != bandeja && null!=bandeja.getId()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("删除提醒");
            alert.setHeaderText(null);
            alert.setContentText("确定删除：" + bandeja.getTrayName());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                dao.delete(ToolsTray.class, bandeja.getId());
                tipsLabel.setText("删除成功：" + bandeja.getTrayName());
                refresh(null);
            }
        }
    }

    public void refresh(ActionEvent event) {
        initData();
        searchCombo.getSelectionModel().selectFirst();
        parentCombo.getSelectionModel().selectFirst();
        typeCombo.getSelectionModel().selectFirst();
        try {
            TrayService.self.loadMenu(TrayService.self.popupMenu,null);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    public void reset(ActionEvent event) {
        id = null;
        nameField.setText("");
        orderField.setText("");
        pathField.setText("");
        commandText.setText("");
        refresh(null);
    }

    public void confirm(ActionEvent event) {
        String name = nameField.getText();
        if(S.isEmpty(name)){
            tipsLabel.setText("错误：工具名字不能为空!");
            nameField.requestFocus();
            return;
        }
        String command = commandText.getText();
        String order = orderField.getText();
        ToolsTray bandeja = (ToolsTray) parentCombo.getSelectionModel().getSelectedItem();
        Integer parentId = bandeja.getId();
        ToolType toolType = (ToolType) typeCombo.getSelectionModel().getSelectedItem();
        String type = toolType.getToolType();

        ToolsTray toolsTray = new ToolsTray();
        toolsTray.setTrayName(name);
        toolsTray.setCommand(command);
        toolsTray.setParentId(parentId);
        toolsTray.setToolOrder(order);
        toolsTray.setToolType(type);
        if(null==id){
            dao.insert(toolsTray);
            id = toolsTray.getId();
            tipsLabel.setText("添加成功：" + name);
        }else{
            toolsTray.setId(id);
            dao.update(toolsTray);
            tipsLabel.setText("更新成功：" + name);
        }
        refresh(null);
    }

    public void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文件");
        File file = fc.showOpenDialog(toolTable.getScene().getWindow());
        pathField.setText(file.getPath());
    }

    public void chooseFolder(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("选择路径");
        File file = dc.showDialog(toolTable.getScene().getWindow());
        pathField.setText(file.getPath());
    }
}
