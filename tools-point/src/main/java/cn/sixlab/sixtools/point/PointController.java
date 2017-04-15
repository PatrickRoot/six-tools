/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.point;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsPoint;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.S;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/5/22 21:50
 */
public class PointController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(PointController.class);
    public static PointController self;
    private Dao dao = D.dao;

    public TextField reasonField;
    public TextField puntoField;
    public TableColumn idColumn;
    public TableColumn puntoColumn;
    public TableColumn reasonColumn;
    public TableColumn timeColumn;
    public TableView puntoTable;
    public Label tipLabel;
    public Label totalPunto;
    public BorderPane toolRoot;

    private final ObservableList<ToolsPoint> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
        initTable();
        loadPunto();
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            data.clear();
        }finally {
            super.finalize();
        }
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

    private ToolsPoint getInput(int i) {
        ToolsPoint toolsPoint = new ToolsPoint();
        String reason = reasonField.getText();
        String punto = puntoField.getText();
        if (S.isNumber(punto)) {
            toolsPoint.setPunto(i * Double.valueOf(punto));
        } else {
            return null;
        }
        toolsPoint.setReason(reason);
        toolsPoint.setIndate(LocalDate.now().toString());
        return toolsPoint;
    }

    public void addPunto(ActionEvent event) {
        ToolsPoint toolsPoint = getInput(1);
        if(null!= toolsPoint){
            dao.insert(toolsPoint);
            tipLabel.setText("计分成功：" + String.format("%.2f", toolsPoint.getPunto()));
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
        List<ToolsPoint> list = dao.query(ToolsPoint.class, Cnd.orderBy().desc("id"));
        if(null!=list && list.size()>0){
            data.addAll(list);
            loadPuntoHis();
        }
    }

    private void loadPuntoHis() {
        Sql sql = Sqls.create(" select sum(punto) from seis_punto ");
        sql.setCallback(Sqls.callback.doubleValue());
        dao.execute(sql);
        Double punto = sql.getNumber().doubleValue();
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