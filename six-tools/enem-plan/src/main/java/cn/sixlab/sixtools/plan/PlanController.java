/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.plan;

import cn.sixlab.sixutil.sixfx.ConfirmDialogExt;
import cn.sixlab.sixtools.comun.base.BaseController;
import cn.sixlab.sixtools.comun.bean.PlanStatus;
import cn.sixlab.sixtools.comun.bean.PlanType;
import cn.sixlab.sixtools.comun.bean.db.SeisPlan;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixtools.comun.util.D;
import cn.sixlab.sixtools.comun.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:31
 */
public class PlanController extends BaseController implements Initializable {
    private static Logger logger = LoggerFactory.getLogger(PlanController.class);
    public static PlanController self;
    private Dao dao = D.dao;

    public final ObservableList<SeisPlan> yearData = FXCollections.observableArrayList();
    public final ObservableList<SeisPlan> seasonData = FXCollections.observableArrayList();
    public final ObservableList<SeisPlan> monthData = FXCollections.observableArrayList();
    public final ObservableList<SeisPlan> weekData = FXCollections.observableArrayList();
    public final ObservableList<SeisPlan> dayData = FXCollections.observableArrayList();
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

    private SeisPlan thePlan = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        self = this;
        datePicker.setShowWeekNumbers(true);
        initCombo();
        initTable();
        initTab();
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

    public void searchYearSer(ActionEvent event) {
        searchYearSer();
    }

    public void searchSeasonSer(ActionEvent event) {
        searchSeasonSer();
    }

    public void searchMonthSer(ActionEvent event) {
        searchMonthSer();
    }

    public void searchWeekSer(ActionEvent event) {
        searchWeekSer();
    }

    public void okPlanSer(ActionEvent event) {
        okPlanSer();
    }

    public void resetPlan() {
        initTaskTab();
    }

    public void searchDay(ActionEvent event) {
        searchDay();
    }


    public void initCombo() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<ComboBox> statusCombos = Arrays.asList(yearStatusCombo, monthStatusCombo,
                seasonStatusCombo, weekStatusCombo, dayStatusCombo);
        statusCombos.stream().forEach(comboBox -> {
            comboBox.setItems(statusData);
            comboBox.getSelectionModel().select(1);
            comboBox.setCellFactory(param -> new ListCell<PlanStatus>() {
                @Override
                protected void updateItem(PlanStatus planStatus, boolean bln) {
                    super.updateItem(planStatus, bln);
                    if (planStatus != null) {
                        //TODO 在这里搜索试一试
                        setText(planStatus.getStatusName());
                    } else {
                        setText(null);
                    }
                }
            });
        });

        List<PlanType> typeList = PlanType.allTypes();
        typeData.clear();
        typeData.addAll(typeList);

        typeCombo.setItems(typeData);
        typeCombo.setCellFactory(p -> new ListCell<PlanType>() {
            @Override
            protected void updateItem(PlanType planType, boolean bln) {
                super.updateItem(planType, bln);
                if (planType != null) {
                    //TODO 在这里搜索试一试
                    setText(planType.getTypeName());
                } else {
                    setText(null);
                }
            }
        });
    }

    public void initTable() {
        searchAllTableData();
        initTableCellValueFactory();
        initTableColumnWidth();
        initTableCellFactory();

        yearTable.setItems(yearData);
        seasonTable.setItems(seasonData);
        monthTable.setItems(monthData);
        weekTable.setItems(weekData);
        dayTable.setItems(dayData);

        yearTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(yearTable));
        seasonTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(seasonTable));
        monthTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(monthTable));
        weekTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(weekTable));
        dayTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(dayTable));
    }

    private void searchAllTableData() {
        searchYearSer();
        searchSeasonSer();
        searchMonthSer();
        searchWeekSer();
        searchDay();
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

    private void initTableCellFactory() {
        yearStatusColumn.setCellFactory(tableColorCallback());
        seasonStatusColumn.setCellFactory(tableColorCallback());
        monthStatusColumn.setCellFactory(tableColorCallback());
        weekStatusColumn.setCellFactory(tableColorCallback());
        dayStatusColumn.setCellFactory(tableColorCallback());
    }

    private Callback<TableColumn, TableCell> tableColorCallback() {
        return p -> new TableCell<SeisPlan, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                TableRow tableRow = this.getTableRow();
                tableRow.setStyle("");
                tableRow.setGraphic(null);
                tableRow.setText(null);
                setText(null);
                setGraphic(null);
                setStyle("");
                if (!isEmpty()) {
                    //TODO 测试一下到底代码覆盖如何,可不可以省些代码
                    SeisPlan colorPlan = (SeisPlan) tableRow.getItem();
                    if (null != colorPlan) {
                        setText(translateStatus(item));
                        if (C.PLAN_STATUS_ED.equals(colorPlan.getPlanStatus())) {
                            tableRow.setStyle("-fx-background-color: #3366CC");
                        } else if (C.PLAN_STATUS_STOP.equals(colorPlan.getPlanStatus())) {
                            tableRow.setStyle("-fx-background-color: #F3705E");
                        } else if (C.PLAN_STATUS_ING.equals(colorPlan.getPlanStatus())) {
                            tableRow.setStyle("-fx-background-color: #99CC33");
                        }
                    }
                }
            }
        };
    }

    private String translateStatus(String item){
        switch (item){
            case "100":
                return "未完成";
            case "300":
                return "完成";
            case "400":
                return "进行中";
            default:
                return item;
        }
    }

    private EventHandler<MouseEvent> tableClick(TableView table) {
        return e -> {
            if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2) {
                //TODO (click count == 2)===( double click)?
                SeisPlan clickedPlan = (SeisPlan) table.getSelectionModel().getSelectedItem();
                if (null != clickedPlan && e.getTarget().getClass() != TableColumnHeader.class) {
                    modifyPlan(clickedPlan);
                }
            } else if (e.getButton().equals(MouseButton.SECONDARY)) {
                showContextMenu(table, e);
            }
        };
    }

    private void modifyPlan(SeisPlan clickedPlan) {
        thePlan = clickedPlan;

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

    private void showContextMenu(TableView table, MouseEvent e) {
        SeisPlan selectedPlan = (SeisPlan) table.getSelectionModel().getSelectedItem();
        if (null != selectedPlan) {
            ContextMenu contextMenu = new ContextMenu();
            ObservableList<MenuItem> items = contextMenu.getItems();

            if (C.PLAN_STATUS_ING.equals(selectedPlan.getPlanStatus())) {
                MenuItem menu40 = new MenuItem("完成任务");
                menu40.setOnAction(p -> {
                    confirmFinishPlan(selectedPlan);
                });
                items.add(menu40);

                MenuItem menu4 = new MenuItem("暂停任务");
                menu4.setOnAction(p -> {
                    confirmStopPlan(selectedPlan);
                });
                items.add(menu4);
            } else {

                MenuItem menu4 = new MenuItem("重启任务");
                menu4.setOnAction(p -> {
                    confirmStartPlan(selectedPlan);
                });
                items.add(menu4);
            }

            contextMenu.setX(e.getScreenX());
            contextMenu.setY(e.getScreenY());
            contextMenu.show(yearTable.getScene().getWindow());
        }
    }

    private void confirmStartPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定重启任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_ING);
            dao.update(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void confirmStopPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定终止任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_STOP);
            dao.update(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void confirmFinishPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定完成任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_ED);
            dao.update(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
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

        initTaskTab();
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

    public void initTaskTab() {
        thePlan = null;
        taskTitleLabel.setText("添加新任务");
        nameField.setText("");
        typeCombo.getSelectionModel().selectLast();
        contentArea.setText("");
    }


    public void searchYearSer() {
        String status = ((PlanStatus) yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_YEAR);
        if (!C.PLAN_STATUS_ALL.equals(status)) {
            cnd = cnd.and("planStatus", "=", status);
        }
        yearData.clear();
        yearData.addAll(dao.query(SeisPlan.class, cnd));
    }

    public void searchSeasonSer() {
        String status = ((PlanStatus) seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_SEASON);
        if (!C.PLAN_STATUS_ALL.equals(status)) {
            cnd = cnd.and("planStatus", "=", status);
        }
        seasonData.clear();
        seasonData.addAll(dao.query(SeisPlan.class, cnd));
    }

    public void searchMonthSer() {
        String status = ((PlanStatus) monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_MONTH);
        if (!C.PLAN_STATUS_ALL.equals(status)) {
            cnd = cnd.and("planStatus", "=", status);
        }
        monthData.clear();
        monthData.addAll(dao.query(SeisPlan.class, cnd));
    }

    public void searchWeekSer() {
        String status = ((PlanStatus) weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_WEEK);
        if (!C.PLAN_STATUS_ALL.equals(status)) {
            cnd = cnd.and("planStatus", "=", status);
        }
        weekData.clear();
        weekData.addAll(dao.query(SeisPlan.class, cnd));
    }

    public void searchDay() {
        String status = ((PlanStatus) weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();

        Cnd cnd = Cnd.where("planType", "=", C.PLAN_TYPE_DAY);
        if(!C.PLAN_STATUS_ALL.equals(status)){
            cnd = cnd.and("planStatus", "=", status);
        }
        dayData.clear();
        dayData.addAll(dao.query(SeisPlan.class, cnd));
    }

    public void okPlanSer() {
        String name = nameField.getText();
        if (S.isEmpty(name)) {
            tipLabel.setText("任务名为空");
            nameField.requestFocus();
            return;
        }

        String content = contentArea.getText();

        String type = ((PlanType) typeCombo.getSelectionModel()
                .getSelectedItem()).getTypeValue();

        boolean isInsert = false;
        if (null == thePlan) {
            isInsert = true;
            thePlan = new SeisPlan();
            thePlan.setPlanStatus(C.PLAN_STATUS_ING);
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(timeField.getText());
        LocalDate localDate = datePicker.getValue();
        thePlan.setPlanDate(null == localDate ? null : localDate.toString());
        thePlan.setPlanType(type);
        thePlan.setPlanContent(content);
        if (name.startsWith(C.PLAN_OUT_COUNT)) {
            thePlan.setPlanType(C.PLAN_TYPE_YEAR);
        }
        if (isInsert) {
            dao.insert(thePlan);
            tipLabel.setText("添加任务成功：" + name);
            taskTitleLabel.setText("修改任务ID：" + thePlan.getId());
        } else {
            dao.update(thePlan);
            tipLabel.setText("更新任务成功：" + name);
        }
        searchAllTableData();
    }
}
