/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.plan;

import cn.sixlab.sixtools.comun.util.S;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import cn.sixlab.sixtools.comun.bean.SeisPlan;
import cn.sixlab.sixtools.comun.bean.ext.PlanStatus;
import cn.sixlab.sixtools.comun.bean.ext.PlanType;
import cn.sixlab.sixtools.comun.dao.PlanDao;
import cn.sixlab.sixtools.comun.util.C;
import cn.sixlab.sixfx.ConfirmDialogExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 17:32
 */
public class PlanService {
    private Logger logger = LoggerFactory.getLogger(PlanService.class);

    private final ObservableList<SeisPlan> yearData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> seasonData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> monthData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> weekData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> dayData = FXCollections.observableArrayList();
    private final ObservableList<PlanStatus> statusData = FXCollections.observableArrayList();
    private final ObservableList<PlanType> typeData = FXCollections.observableArrayList();
    private PlanDao dao = new PlanDao();
    private PlanController ctrl;
    private SeisPlan thePlan = null;

    public PlanService(PlanController planController) {
        this.ctrl = planController;
    }

    public void initCombo() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<ComboBox> statusCombos = Arrays.asList(ctrl.yearStatusCombo, ctrl.monthStatusCombo,
                ctrl.seasonStatusCombo, ctrl.weekStatusCombo, ctrl.dayStatusCombo);
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

        ctrl.typeCombo.setItems(typeData);
        ctrl.typeCombo.setCellFactory(p -> new ListCell<PlanType>() {
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

        ctrl.yearTable.setItems(yearData);
        ctrl.seasonTable.setItems(seasonData);
        ctrl.monthTable.setItems(monthData);
        ctrl.weekTable.setItems(weekData);
        ctrl.dayTable.setItems(dayData);

        ctrl.yearTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(ctrl.yearTable));
        ctrl.seasonTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(ctrl.seasonTable));
        ctrl.monthTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(ctrl.monthTable));
        ctrl.weekTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(ctrl.weekTable));
        ctrl.dayTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(ctrl.dayTable));
    }

    private void searchAllTableData() {
        searchYear();
        searchSeason();
        searchMonth();
        searchWeek();
        searchDay();
    }

    private void initTableCellValueFactory() {
        ctrl.yearIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        ctrl.seasonIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        ctrl.monthIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        ctrl.weekIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        ctrl.dayIdColumn.setCellValueFactory(new PropertyValueFactory("id"));

        ctrl.yearNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        ctrl.seasonNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        ctrl.monthNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        ctrl.weekNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        ctrl.dayNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));

        ctrl.yearDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        ctrl.seasonDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        ctrl.monthDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        ctrl.weekDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        ctrl.dayDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));

        ctrl.yearTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        ctrl.seasonTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        ctrl.monthTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        ctrl.weekTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        ctrl.dayTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));

        ctrl.yearStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        ctrl.seasonStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        ctrl.monthStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        ctrl.weekStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        ctrl.dayStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
    }

    private void initTableColumnWidth() {
        ctrl.yearIdColumn.setPrefWidth(60);
        ctrl.seasonIdColumn.setPrefWidth(60);
        ctrl.monthIdColumn.setPrefWidth(60);
        ctrl.weekIdColumn.setPrefWidth(60);
        ctrl.dayIdColumn.setPrefWidth(60);

        ctrl.yearNameColumn.setPrefWidth(510);
        ctrl.seasonNameColumn.setPrefWidth(510);
        ctrl.monthNameColumn.setPrefWidth(510);
        ctrl.weekNameColumn.setPrefWidth(510);
        ctrl.dayNameColumn.setPrefWidth(510);

        ctrl.yearDateColumn.setPrefWidth(90);
        ctrl.seasonDateColumn.setPrefWidth(90);
        ctrl.monthDateColumn.setPrefWidth(90);
        ctrl.weekDateColumn.setPrefWidth(90);
        ctrl.dayDateColumn.setPrefWidth(90);

        ctrl.yearTimeColumn.setPrefWidth(60);
        ctrl.seasonTimeColumn.setPrefWidth(60);
        ctrl.monthTimeColumn.setPrefWidth(60);
        ctrl.weekTimeColumn.setPrefWidth(60);
        ctrl.dayTimeColumn.setPrefWidth(60);

        ctrl.yearStatusColumn.setPrefWidth(60);
        ctrl.seasonStatusColumn.setPrefWidth(60);
        ctrl.monthStatusColumn.setPrefWidth(60);
        ctrl.weekStatusColumn.setPrefWidth(60);
        ctrl.dayStatusColumn.setPrefWidth(60);
    }

    private void initTableCellFactory() {
        ctrl.yearStatusColumn.setCellFactory(tableColorCallback());
        ctrl.seasonStatusColumn.setCellFactory(tableColorCallback());
        ctrl.monthStatusColumn.setCellFactory(tableColorCallback());
        ctrl.weekStatusColumn.setCellFactory(tableColorCallback());
        ctrl.dayStatusColumn.setCellFactory(tableColorCallback());
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
                if(!isEmpty()){
                    //TODO 测试一下到底代码覆盖如何,可不可以省些代码
                    SeisPlan colorPlan = (SeisPlan) tableRow.getItem();
                    if (null != colorPlan) {
                        setText(item);
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

        ctrl.nameField.setText(clickedPlan.getPlanName());
        ctrl.timeField.setText(clickedPlan.getPlanTime());
        ctrl.contentArea.setText(clickedPlan.getPlanContent());

        ctrl.typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        clickedPlan.getPlanType()))
                .forEach(type -> {
                    ctrl.typeCombo.getSelectionModel().select(type);
                });

        if(S.isNotEmpty(clickedPlan.getPlanDate())){
            ctrl.datePicker.setValue(LocalDate.parse(clickedPlan.getPlanDate()));
        }

        ctrl.taskTitleLabel.setText("修改任务ID：" + clickedPlan.getId());
        ctrl.tabPane.getSelectionModel().select(ctrl.taskTab);
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
            }else{

                MenuItem menu4 = new MenuItem("重启任务");
                menu4.setOnAction(p -> {
                    confirmStartPlan(selectedPlan);
                });
                items.add(menu4);
            }

            contextMenu.setX(e.getScreenX());
            contextMenu.setY(e.getScreenY());
            contextMenu.show(ctrl.yearTable.getScene().getWindow());
        }
    }

    private void confirmStartPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定重启任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_ING);
            dao.updateById(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void confirmStopPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定终止任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_STOP);
            dao.updateById(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void confirmFinishPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定完成任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            seisPlan.setPlanStatus(C.PLAN_STATUS_ED);
            dao.updateById(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    public void initTab() {

        ctrl.yearTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        ctrl.monthTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        ctrl.seasonTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        ctrl.weekTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        ctrl.dayTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        ctrl.tabPane.getSelectionModel().select(ctrl.dayTab);

        initTaskTab();
    }

    private void changeTab() {
        //TODO currentTab && ctrl.yearTab.isSelected() 到底哪个好用
        Tab selectTab = ctrl.tabPane.getSelectionModel().getSelectedItem();
        if (selectTab == ctrl.yearTab && ctrl.yearTab.isSelected()) {
            ctrl.tipLabel.setText("任务数量为：" + yearData.size());
        } else if (selectTab == ctrl.seasonTab && ctrl.seasonTab.isSelected()) {
            ctrl.tipLabel.setText("任务数量为：" + seasonData.size());
        } else if (selectTab == ctrl.monthTab && ctrl.monthTab.isSelected()) {
            ctrl.tipLabel.setText("任务数量为：" + monthData.size());
        } else if (selectTab == ctrl.weekTab && ctrl.weekTab.isSelected()) {
            ctrl.tipLabel.setText("任务数量为：" + weekData.size());
        } else if (selectTab == ctrl.dayTab && ctrl.dayTab.isSelected()) {
            ctrl.tipLabel.setText("任务数量为：" + dayData.size());
        }
    }

    public void initTaskTab() {
        thePlan = null;
        ctrl.taskTitleLabel.setText("添加新任务");
        ctrl.nameField.setText("");
        ctrl.typeCombo.getSelectionModel().selectLast();
        ctrl.contentArea.setText("");
    }


    public void searchYear() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) ctrl.yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_YEAR);
        yearData.clear();
        yearData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchSeason() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) ctrl.seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_SEASON);
        seasonData.clear();
        seasonData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchMonth() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) ctrl.monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_MONTH);
        monthData.clear();
        monthData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchWeek() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) ctrl.weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_WEEK);
        weekData.clear();
        weekData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchDay() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) ctrl.weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_DAY);
        dayData.clear();
        dayData.addAll(dao.selectByPlan(sixPlan));
    }

    public void okPlan() {
        String name = ctrl.nameField.getText();
        if (S.isEmpty(name)) {
            ctrl.tipLabel.setText("任务名为空");
            ctrl.nameField.requestFocus();
            return;
        }

        String content = ctrl.contentArea.getText();

        String type = ((PlanType) ctrl.typeCombo.getSelectionModel()
                .getSelectedItem()).getTypeValue();

        boolean isInsert = false;
        if (null == thePlan) {
            isInsert = true;
            thePlan = new SeisPlan();
            thePlan.setPlanStatus(C.PLAN_STATUS_ING);
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(ctrl.timeField.getText());
        LocalDate localDate = ctrl.datePicker.getValue();
        thePlan.setPlanDate(null== localDate?null:localDate.toString());
        thePlan.setPlanType(type);
        thePlan.setPlanContent(content);
        if (name.startsWith(C.PLAN_OUT_COUNT)) {
            thePlan.setPlanType(C.PLAN_TYPE_YEAR);
        }
        if (isInsert) {
            dao.insert(thePlan);
            ctrl.tipLabel.setText("添加任务成功：" + name);
            ctrl.taskTitleLabel.setText("修改任务ID："+thePlan.getId());
        } else {
            dao.updateById(thePlan);
            ctrl.tipLabel.setText("更新任务成功：" + name);
        }
        searchAllTableData();
    }
}
