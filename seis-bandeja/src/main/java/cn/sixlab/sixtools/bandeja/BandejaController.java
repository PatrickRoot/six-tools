/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.bandeja;

import cn.sixlab.sixfx.AlertDialog;
import cn.sixlab.sixfx.ConfirmDialog;
import cn.sixlab.sixtools.comun.bean.SeisBandeja;
import cn.sixlab.sixtools.comun.bean.ext.ToolType;
import cn.sixlab.sixtools.comun.dao.TrayDao;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/6/19 17:00
 */
public class BandejaController implements Initializable{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObservableList<SeisBandeja> tableData = FXCollections.observableArrayList();
    private final ObservableList<SeisBandeja> superData = FXCollections.observableArrayList();
    private final ObservableList<SeisBandeja> searchData = FXCollections.observableArrayList();
    private final ObservableList<ToolType> typeData = FXCollections.observableArrayList();
    private TrayDao dao = new TrayDao();
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
        initData();
        initCombo();
        initTable();
    }

    private void initCombo() {

        List<ComboBox> superComboBoxes = Arrays.asList(parentCombo, searchCombo);
        parentCombo.setItems(superData);
        searchCombo.setItems(searchData);
        superComboBoxes.stream().forEach(comboBox -> {
            comboBox.getSelectionModel().selectFirst();
            comboBox.setCellFactory(param -> new ListCell<SeisBandeja>() {
                @Override
                protected void updateItem(SeisBandeja seisBandeja, boolean bln) {
                    super.updateItem(seisBandeja, bln);
                    if (seisBandeja != null) {
                        setText(seisBandeja.getTrayName());
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
                SeisBandeja bandeja = (SeisBandeja) toolTable.getSelectionModel().getSelectedItem();
                if (null != bandeja && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPlan(bandeja);
                }
            } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                delete(null);
            }
        });

        toolTable.setItems(tableData);
    }

    private void modifyPlan(SeisBandeja bandeja) {
        id = bandeja.getId();
        nameField.setText(bandeja.getTrayName());
        commandText.setText(bandeja.getCommand());
    }

    private void initData(){

        typeData.clear();
        typeData.addAll(ToolType.allToolTypes());

        List<SeisBandeja> bandejaList = dao.getToolFolders();

        superData.clear();
        SeisBandeja bandeja1 = new SeisBandeja();
        bandeja1.setId(C.ROOT_PARENT_ID);
        bandeja1.setTrayName("--无父目录--");
        superData.add(bandeja1);
        superData.addAll(bandejaList);

        searchData.clear();
        SeisBandeja bandeja = new SeisBandeja();
        bandeja.setTrayName("--全部--");
        searchData.add(bandeja);
        searchData.add(bandeja1);
        searchData.addAll(bandejaList);

        tableData.clear();
        tableData.addAll(dao.getAll());
    }

    public void search(ActionEvent event) {

        SeisBandeja bandeja = (SeisBandeja) searchCombo.getSelectionModel().getSelectedItem();
        List<SeisBandeja> bandejaList;
        if(null==bandeja || null==bandeja.getId()){
            bandejaList = dao.getAll();
        }else{
            bandejaList = dao.getSubTrays(bandeja.getId());
        }
        tableData.clear();
        tableData.addAll(bandejaList);

    }

    public void delete(ActionEvent event) {
        SeisBandeja bandeja = (SeisBandeja) toolTable.getSelectionModel().getSelectedItem();
        if (null != bandeja && null!=bandeja.getId()) {
            ConfirmDialog dialog = new ConfirmDialog("提醒","确定删除:"+bandeja.getTrayName());
            dialog.setOnAction(e->{
                dao.delete(bandeja.getId());
                refresh(null);
            });
            dialog.show();
        }
    }

    public void refresh(ActionEvent event) {
        initData();
        searchCombo.getSelectionModel().selectFirst();
        parentCombo.getSelectionModel().selectFirst();
        typeCombo.getSelectionModel().selectFirst();
        try {
            BandejaService.self.loadMenu(BandejaService.self.popupMenu,null);
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
            new AlertDialog("错误提示","工具名字不能为空!").show();
            return;
        }
        String command = commandText.getText();
        String order = orderField.getText();
        SeisBandeja bandeja = (SeisBandeja) parentCombo.getSelectionModel().getSelectedItem();
        Integer parentId = bandeja.getId();
        ToolType toolType = (ToolType) typeCombo.getSelectionModel().getSelectedItem();
        String type = toolType.getToolType();

        SeisBandeja seisBandeja = new SeisBandeja();
        seisBandeja.setTrayName(name);
        seisBandeja.setCommand(command);
        seisBandeja.setParentId(parentId);
        seisBandeja.setToolOrder(order);
        seisBandeja.setToolType(type);
        if(null==id){
            dao.insert(seisBandeja);
            id = seisBandeja.getId();
            new AlertDialog("成功提示", "添加成功:" + name).show();
        }else{
            seisBandeja.setId(id);
            dao.update(seisBandeja);
            new AlertDialog("成功提示", "更新成功:" + name).show();
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
