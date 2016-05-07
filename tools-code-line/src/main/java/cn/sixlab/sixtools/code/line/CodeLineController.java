/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.code.line;

import cn.sixlab.sixtools.dao.base.BaseController;
import cn.sixlab.sixtools.dao.bean.PlanStatus;
import cn.sixlab.sixtools.dao.bean.PlanType;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolsPlan;
import cn.sixlab.sixtools.dao.bean.sqlite.ToolMeta;
import cn.sixlab.sixtools.dao.util.C;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.dao.util.S;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:31
 */
public class CodeLineController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(CodeLineController.class);
    public static CodeLineController self;
    private Dao dao = D.dao;

    public final ObservableList<ToolsPlan> yearData = FXCollections.observableArrayList();
    public final ObservableList<ToolsPlan> seasonData = FXCollections.observableArrayList();
    public final ObservableList<ToolsPlan> monthData = FXCollections.observableArrayList();
    public final ObservableList<ToolsPlan> weekData = FXCollections.observableArrayList();
    public final ObservableList<ToolsPlan> dayData = FXCollections.observableArrayList();
    public final ObservableList<PlanStatus> statusData = FXCollections.observableArrayList();
    public final ObservableList<PlanType> typeData = FXCollections.observableArrayList();

    public TabPane tabPane;
    public Tab yearTab;
    public Tab seasonTab;
    public Tab monthTab;
    public Tab weekTab;
    public Tab dayTab;
    public Tab taskTab;

    public ComboBox yearStatusCombo;

    public TableView yearTable;
    public TableColumn yearIdColumn;
    public TableColumn yearNameColumn;
    public TableColumn yearTimeColumn;
    public TableColumn yearDateColumn;
    public TableColumn yearStatusColumn;

    public ComboBox seasonStatusCombo;

    public TableView seasonTable;
    public TableColumn seasonIdColumn;
    public TableColumn seasonNameColumn;
    public TableColumn seasonTimeColumn;
    public TableColumn seasonDateColumn;
    public TableColumn seasonStatusColumn;

    public ComboBox monthStatusCombo;

    public TableView monthTable;
    public TableColumn monthIdColumn;
    public TableColumn monthNameColumn;
    public TableColumn monthDateColumn;
    public TableColumn monthTimeColumn;
    public TableColumn monthStatusColumn;

    public ComboBox dayStatusCombo;

    public TableView dayTable;
    public TableColumn dayIdColumn;
    public TableColumn dayNameColumn;
    public TableColumn dayTimeColumn;
    public TableColumn dayDateColumn;
    public TableColumn dayStatusColumn;

    public ComboBox weekStatusCombo;

    public TableView weekTable;
    public TableColumn weekIdColumn;
    public TableColumn weekNameColumn;
    public TableColumn weekTimeColumn;
    public TableColumn weekDateColumn;
    public TableColumn weekStatusColumn;

    public TextField nameField;
    public ComboBox typeCombo;
    public TextArea contentArea;
    public DatePicker datePicker;
    public TextField timeField;

    public Label tipLabel;
    public Label taskTitleLabel;

    private List<ToolMeta> dirs = new ArrayList<>();
    private List<ToolMeta> fileTypes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;

        initPatterns();

        initTable();
        initTab();
    }

    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
    }

    private void initPatterns() {
        dirs = dao.query(ToolMeta.class, Cnd.where("toolKey", "=", "code-line-dir"));
        fileTypes = dao.query(ToolMeta.class, Cnd.where("toolKey", "=", "code-line-file"));
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            yearData.clear();
            seasonData.clear();
            monthData.clear();
            weekData.clear();
            dayData.clear();
            statusData.clear();
            typeData.clear();
        }finally {
            super.finalize();
        }
    }

    public void initTable() {
        initTableCellValueFactory();
        initTableColumnWidth();

        yearTable.setItems(yearData);
        seasonTable.setItems(seasonData);
        monthTable.setItems(monthData);
        weekTable.setItems(weekData);
        dayTable.setItems(dayData);
    }

    private void initTableCellValueFactory() {
        yearIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        seasonIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        monthIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        weekIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        dayIdColumn.setCellValueFactory(new PropertyValueFactory("id"));

        yearNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        seasonNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        monthNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        weekNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        dayNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));

        yearDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        seasonDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        monthDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        weekDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        dayDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));

        yearTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        seasonTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        monthTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        weekTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        dayTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));

        yearStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        seasonStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        monthStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        weekStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        dayStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
    }

    private void initTableColumnWidth() {
        yearIdColumn.setPrefWidth(60);
        seasonIdColumn.setPrefWidth(60);
        monthIdColumn.setPrefWidth(60);
        weekIdColumn.setPrefWidth(60);
        dayIdColumn.setPrefWidth(60);

        yearNameColumn.setPrefWidth(510);
        seasonNameColumn.setPrefWidth(510);
        monthNameColumn.setPrefWidth(510);
        weekNameColumn.setPrefWidth(510);
        dayNameColumn.setPrefWidth(510);

        yearDateColumn.setPrefWidth(90);
        seasonDateColumn.setPrefWidth(90);
        monthDateColumn.setPrefWidth(90);
        weekDateColumn.setPrefWidth(90);
        dayDateColumn.setPrefWidth(90);

        yearTimeColumn.setPrefWidth(60);
        seasonTimeColumn.setPrefWidth(60);
        monthTimeColumn.setPrefWidth(60);
        weekTimeColumn.setPrefWidth(60);
        dayTimeColumn.setPrefWidth(60);

        yearStatusColumn.setPrefWidth(60);
        seasonStatusColumn.setPrefWidth(60);
        monthStatusColumn.setPrefWidth(60);
        weekStatusColumn.setPrefWidth(60);
        dayStatusColumn.setPrefWidth(60);
    }

    private void modifyPlan(ToolsPlan clickedPlan) {

        nameField.setText(clickedPlan.getPlanName());
        timeField.setText(clickedPlan.getPlanTime());
        contentArea.setText(clickedPlan.getPlanContent());

        typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        clickedPlan.getPlanType()))
                .forEach(type -> {
                    typeCombo.getSelectionModel().select(type);
                });

        if (S.isNotEmpty(clickedPlan.getPlanDate())) {
            datePicker.setValue(LocalDate.parse(clickedPlan.getPlanDate()));
        }

        taskTitleLabel.setText("修改任务ID：" + clickedPlan.getId());
        tabPane.getSelectionModel().select(taskTab);
    }

    public void initTab() {

        yearTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        monthTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        seasonTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        weekTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        dayTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        tabPane.getSelectionModel().select(dayTab);

        //initTaskTab();
    }

    private void changeTab() {
        //TODO currentTab && ctrl.yearTab.isSelected() 到底哪个好用
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectTab == yearTab && yearTab.isSelected()) {
            tipLabel.setText("任务数量为：" + yearData.size());
        } else if (selectTab == seasonTab && seasonTab.isSelected()) {
            tipLabel.setText("任务数量为：" + seasonData.size());
        } else if (selectTab == monthTab && monthTab.isSelected()) {
            tipLabel.setText("任务数量为：" + monthData.size());
        } else if (selectTab == weekTab && weekTab.isSelected()) {
            tipLabel.setText("任务数量为：" + weekData.size());
        } else if (selectTab == dayTab && dayTab.isSelected()) {
            tipLabel.setText("任务数量为：" + dayData.size());
        }
    }

    public void searchDay() {
        String status = ((PlanStatus) weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_DAY);
        if(!C.PLAN_STATUS_ALL.equals(status)){
            cnd = cnd.and("planStatus", "=", status);
        }
        dayData.clear();
        dayData.addAll(dao.query(ToolsPlan.class, cnd));
    }
}
