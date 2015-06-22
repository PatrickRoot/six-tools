/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.punto;

import cn.sixlab.sixtools.comun.bean.SeisPunto;
import cn.sixlab.sixtools.comun.dao.PuntoDao;
import cn.sixlab.sixtools.comun.util.S;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 21:50
 */
public class PuntoController implements Initializable {

    public TextField reasonField;
    public TextField puntoField;
    public TableColumn idColumn;
    public TableColumn puntoColumn;
    public TableColumn reasonColumn;
    public TableColumn timeColumn;
    public TableView puntoTable;
    public Label tipLabel;
    public Label totalPunto;

    private PuntoDao dao = new PuntoDao();

    private final ObservableList<SeisPunto> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
        loadPunto();
    }

    private void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        puntoColumn.setCellValueFactory(new PropertyValueFactory("punto"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory("reason"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("indate"));
        idColumn.setPrefWidth(50);
        puntoColumn.setPrefWidth(70);
        reasonColumn.setPrefWidth(580);
        timeColumn.setPrefWidth(90);

        puntoTable.setItems(data);
    }

    private SeisPunto getInput(int i) {
        SeisPunto seisPunto = new SeisPunto();
        String reason = reasonField.getText();
        String punto = puntoField.getText();
        if (S.isNumber(punto)) {
            seisPunto.setPunto(i * Double.valueOf(punto));
        } else {
            return null;
        }
        seisPunto.setReason(reason);
        seisPunto.setIndate(LocalDate.now().toString());
        return seisPunto;
    }

    public void addPunto(ActionEvent event) {
        SeisPunto seisPunto = getInput(1);
        if(null!=seisPunto){
            dao.insert(seisPunto);
            tipLabel.setText("计分成功：" + String.format("%.2f", seisPunto.getPunto()));
            loadPunto();
        }else{
            tipLabel.setText("计分失败");
        }
        System.out.println("----------------------------------");
        System.out.println(idColumn.getWidth());
        System.out.println(puntoColumn.getWidth());
        System.out.println(reasonColumn.getWidth());
        System.out.println(timeColumn.getWidth());
        System.out.println("------------------------------------");
    }

    private void loadPunto() {
        data.clear();
        List<SeisPunto> list = dao.queryAll();
        if(null!=list && list.size()>0){
            data.addAll(list);
            loadPuntoHis();
        }
    }

    private void loadPuntoHis() {
        Double punto = dao.queryPunto();
        if(null!=punto){
            if(punto<=0){
                totalPunto.setTextFill(Color.RED);
            }else{
                totalPunto.setTextFill(Color.GREEN);
            }
            totalPunto.setText("总分：" + String.format("%.2f", punto));
        }
    }
}