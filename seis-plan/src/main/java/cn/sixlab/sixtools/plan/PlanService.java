/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.plan;

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
import cn.sixlab.StrUtil;
import cn.sixlab.sixfx.ConfirmDialogExt;

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

    private final ObservableList<SeisPlan> yearData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> seasonData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> monthData = FXCollections.observableArrayList();
    private final ObservableList<SeisPlan> weekData = FXCollections.observableArrayList();
    private final ObservableList<PlanStatus> statusData = FXCollections.observableArrayList();
    private final ObservableList<PlanType> typeData = FXCollections.observableArrayList();
    private PlanDao dao = new PlanDao();
    private PlanController controller;
    private SeisPlan thePlan = null;

    public PlanService(PlanController planController) {
        this.controller = planController;
    }

    public void initCombo() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<ComboBox> statusCombos = Arrays.asList(controller.yearStatusCombo, controller.monthStatusCombo,
                controller.seasonStatusCombo, controller.weekStatusCombo);
        statusCombos.stream().forEach(comboBox -> {
            comboBox.setItems(statusData);
            comboBox.getSelectionModel().selectFirst();
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

        controller.typeCombo.setItems(typeData);
        controller.typeCombo.getSelectionModel().selectLast();
        controller.typeCombo.setCellFactory(p -> new ListCell<PlanType>() {
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
        initTableCellFactory();

        controller.yearTable.setItems(yearData);
        controller.seasonTable.setItems(seasonData);
        controller.monthTable.setItems(monthData);
        controller.weekTable.setItems(weekData);

        controller.yearTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(controller.yearTable));
        controller.seasonTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(controller.seasonTable));
        controller.monthTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(controller.monthTable));
        controller.weekTable.addEventFilter(MouseEvent.MOUSE_CLICKED, tableClick(controller.weekTable));
    }

    private void searchAllTableData() {
        searchYear();
        searchSeason();
        searchMonth();
        searchWeek();
    }

    private void initTableCellValueFactory() {
        controller.yearIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        controller.seasonIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        controller.monthIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        controller.weekIdColumn.setCellValueFactory(new PropertyValueFactory("id"));

        controller.yearNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        controller.seasonNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        controller.monthNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        controller.weekNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));

        controller.yearDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        controller.seasonDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        controller.monthDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));
        controller.weekDateColumn.setCellValueFactory(new PropertyValueFactory("planDate"));

        controller.yearTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.seasonTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.monthTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.weekTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));

        controller.yearStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        controller.seasonStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        controller.monthStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
        controller.weekStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatus"));
    }

    private void initTableCellFactory() {
        controller.yearStatusColumn.setCellFactory(tableColorCallback());
        controller.seasonStatusColumn.setCellFactory(tableColorCallback());
        controller.monthStatusColumn.setCellFactory(tableColorCallback());
        controller.weekStatusColumn.setCellFactory(tableColorCallback());
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
                            tableRow.setStyle("-fx-background-color: #F3705E");
                        } else if (C.PLAN_STATUS_STOP.equals(colorPlan.getPlanStatus())) {
                            tableRow.setStyle("-fx-background-color: #FF7F27");
                        } else if (C.PLAN_STATUS_ING.equals(colorPlan.getPlanStatus())) {
                            tableRow.setStyle("-fx-background-color: #BEE02A");
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

        controller.nameField.setText(clickedPlan.getPlanName());
        controller.timeField.setText(clickedPlan.getPlanTime());
        controller.contentArea.setText(clickedPlan.getPlanContent());

        controller.typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        clickedPlan.getPlanType()))
                .forEach(type -> {
                    controller.typeCombo.getSelectionModel().select(type);
                });

        controller.datePicker.setValue(LocalDate.parse(clickedPlan.getPlanDate()));

        controller.taskTitleLabel.setText("修改任务：" + clickedPlan.getPlanName());
        controller.tabPane.getSelectionModel().select(controller.taskTab);
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
            contextMenu.show(controller.yearTable.getScene().getWindow());
        }
    }

    private void confirmStartPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定重启任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            startPlan(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void startPlan(SeisPlan seisPlan) {
        seisPlan.setPlanStatus(C.PLAN_STATUS_ING);
        dao.updateById(seisPlan);
    }

    private void confirmStopPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定终止任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            stopPlan(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void stopPlan(SeisPlan sixPlan) {
        sixPlan.setPlanStatus(C.PLAN_STATUS_STOP);
        dao.updateById(sixPlan);
    }

    private void confirmFinishPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认", "确定完成任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            finishPlan(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void finishPlan(SeisPlan seisPlan) {
        seisPlan.setPlanStatus(C.PLAN_STATUS_ED);
        dao.updateById(seisPlan);
    }

    public void initTab() {

        controller.yearTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        controller.monthTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        controller.weekTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        controller.seasonTab.selectedProperty().addListener(e -> {
            changeTab();
        });

        controller.tabPane.getSelectionModel().select(controller.weekTab);

        initTaskTab();
    }

    private void changeTab() {
        //TODO currentTab && controller.yearTab.isSelected() dao di na ge hao yong
        Tab selectTab = controller.tabPane.getSelectionModel().getSelectedItem();
        if (selectTab == controller.yearTab && controller.yearTab.isSelected()) {
            controller.tipLabel.setText("任务数量为：" + yearData.size());
        } else if (selectTab == controller.seasonTab && controller.seasonTab.isSelected()) {
            controller.tipLabel.setText("任务数量为：" + seasonData.size());
        } else if (selectTab == controller.monthTab && controller.monthTab.isSelected()) {
            controller.tipLabel.setText("任务数量为：" + monthData.size());
        } else if (selectTab == controller.weekTab && controller.weekTab.isSelected()) {
            controller.tipLabel.setText("任务数量为：" + weekData.size());
        }
    }

    public void initTaskTab() {
        thePlan = null;
        controller.taskTitleLabel.setText("添加新任务");
        controller.nameField.setText("");
        controller.typeCombo.getSelectionModel().selectFirst();
        controller.typeCombo.setDisable(false);
        controller.contentArea.setText("");
    }


    public void searchYear() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) controller.yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_YEAR);
        yearData.clear();
        yearData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchSeason() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) controller.seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_SEASON);
        seasonData.clear();
        seasonData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchMonth() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) controller.monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_MONTH);
        monthData.clear();
        monthData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchWeek() {
        SeisPlan sixPlan = new SeisPlan();

        String status = ((PlanStatus) controller.weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_WEEK);
        weekData.clear();
        weekData.addAll(dao.selectByPlan(sixPlan));
    }

    public void okPlan() {
        String name = controller.nameField.getText();
        if (StrUtil.isEmpty(name)) {
            controller.tipLabel.setText("任务名为空");
            controller.nameField.requestFocus();
            return;
        }

        String content = controller.contentArea.getText();

        String type = ((PlanType) controller.typeCombo.getSelectionModel()
                .getSelectedItem()).getTypeValue();

        boolean isInsert = false;
        if (null == thePlan) {
            isInsert = true;
            thePlan = new SeisPlan();
            thePlan.setPlanStatus(C.PLAN_STATUS_ING);
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(controller.timeField.getText());
        thePlan.setPlanDate(controller.datePicker.getValue().toString());
        thePlan.setPlanType(type);
        thePlan.setPlanContent(content);
        if (name.startsWith(C.PLAN_OUT_COUNT)) {
            thePlan.setPlanType(C.PLAN_TYPE_YEAR);
        }
        if (isInsert) {
            dao.insert(thePlan);
            controller.tipLabel.setText("添加任务成功：" + name);
            initTaskTab();
        } else {
            dao.updateById(thePlan);
            controller.tipLabel.setText("更新任务成功：" + name);
        }
        searchAllTableData();
    }
}
