/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.seis;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import cn.sixlab.sixtools.comun.bean.SeisBandeja;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 13:27
 */
public class SeisController implements Initializable{
    
    public Tab toolsTab;
    public TabPane tabPane;
    public VBox vBox;
    public Tab searchTab;
    public TableView toolTable;
    public TableColumn idColumn;
    public TableColumn nameColumn;
    public TableColumn pathColumn;
    public TableColumn commandColumn;
    public TableColumn paramColumn;
    public TableColumn typeColumn;
    public TableColumn orderColumn;
    public TableColumn superColumn;
    public ComboBox searchCombo;
    public Tab opeTab;
    public TextField nameField;
    public TextField orderField;
    public ComboBox typeCombo;
    public ComboBox parentCombo;
    public TextField pathField;
    public TextField commandField;
    public TextField paramField;
    public Label tipLabel;
    public FlowPane flowPane;

    private SeisService service = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service = new SeisService(SeisController.this);

        service.initCombo();
        service.initTable();
        service.initToolTab();
    }

    public void search(ActionEvent event) {
        Integer parentId = ((SeisBandeja) searchCombo.getValue()).getId();
        service.search(parentId);
    }

    public void delete(ActionEvent event) {
        SeisBandeja seisBandeja = (SeisBandeja) toolTable.getSelectionModel().getSelectedItem();
        if(null!=seisBandeja){
            service.delete(seisBandeja);
        }
    }

    public void refresh(ActionEvent event) {
        service.refresh();
    }

    public void reset(ActionEvent event) {
        service.reset();
    }

    public void confirm(ActionEvent event) {
        service.confirm();
    }

    public void refreshToolTab(ActionEvent event) {
        service.initToolTab();
    }

    public void chooseFile(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("选择文件");
        File file = fc.showOpenDialog(tabPane.getScene().getWindow());
        pathField.setText(file.getPath());
    }

    public void chooseFolder(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("选择路径");
        File file = dc.showDialog(tabPane.getScene().getWindow());
        pathField.setText(file.getPath());
    }
}
