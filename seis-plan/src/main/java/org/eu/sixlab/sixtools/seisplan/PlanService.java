/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seisplan;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import org.eu.sixlab.sixtools.comun.bean.SeisPlan;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanStatus;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanType;
import org.eu.sixlab.sixtools.comun.dao.PlanDao;
import org.eu.sixlab.sixtools.comun.util.C;
import org.eu.sixlab.sixutil.StrUtil;
import org.eu.sixlab.sixutil.sixfx.ConfirmDialogExt;
import org.eu.sixlab.sixutil.sixfx.InputDialogExt;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
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
    private Integer parentId = null;
    private SeisPlan thePlan = null;
    private Integer taskTabStatus = 0;
    private Tab currentTab = null;

    public PlanService(PlanController planController) {
        this.controller = planController;
    }

    public void initCombo() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<PlanType> typeList = PlanType.allTypes();
        typeData.clear();
        typeData.addAll(typeList);

        ComboBox[] statusCombo = new ComboBox[]{
                controller.yearStatusCombo, controller.monthStatusCombo,
                controller.seasonStatusCombo, controller.weekStatusCombo
        };

        for (ComboBox comboBox : statusCombo) {
            comboBox.setItems(statusData);
            comboBox.getSelectionModel().selectFirst();
            comboBox.setCellFactory(param -> new ListCell<PlanStatus>() {
                @Override
                protected void updateItem(PlanStatus planStatus, boolean bln) {
                    super.updateItem(planStatus, bln);
                    if (planStatus != null) {
                        //TODO zai zhe li sou suo shi yi shi
                        setText(planStatus.getStatusName());
                    } else {
                        setText(null);
                    }
                }
            });
        }

        controller.typeCombo.setItems(typeData);
        controller.typeCombo.getSelectionModel().selectLast();
        controller.typeCombo.setCellFactory(p -> new ListCell<PlanType>() {
            @Override
            protected void updateItem(PlanType planType, boolean bln) {
                super.updateItem(planType, bln);
                if (planType != null) {
                    //TODO zai zhe li sou suo shi yi shi
                    setText(planType.getTypeName());
                } else {
                    setText(null);
                }
            }
        });
    }
    
    public void initField() {
        LocalDate date = LocalDate.now();

        String year = String.valueOf(date.getYear());
        Integer monthValue = date.getMonthValue();
        String month = String.valueOf(monthValue);
        String season = String.valueOf((monthValue + 2) / 3);
        String week = String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));

        controller.yearYearField.setText(year.toString());
        controller.seasonYearField.setText(year.toString());
        controller.monthYearField.setText(year.toString());
        controller.weekYearField.setText(year.toString());

        controller.seasonSeasonField.setText(season.toString());
        controller.monthSeasonField.setText(season.toString());
        controller.weekSeasonField.setText(season.toString());

        controller.monthMonthField.setText(month.toString());
        controller.weekMonthField.setText(month.toString());

        controller.weekWeekField.setText(week.toString());

        controller.yearDatePicker.setValue(date);
        controller.seasonDatePicker.setValue(date);
        controller.monthDatePicker.setValue(date);
        controller.weekDatePicker.setValue(date);
        controller.datePicker.setValue(date);


        controller.yearYearField.textProperty().addListener(e -> {
            controller.searchYear();
        });
        controller.seasonYearField.textProperty().addListener(e -> {
            controller.searchSeason();
        });
        controller.monthYearField.textProperty().addListener(e -> {
            controller.searchMonth();
        });
        controller.weekYearField.textProperty().addListener(e -> {
            controller.searchWeek();
        });

        controller.seasonSeasonField.textProperty().addListener(e -> {
            controller.searchSeason();
        });
        controller.monthSeasonField.textProperty().addListener(e -> {
            controller.searchMonth();
        });
        controller.weekSeasonField.textProperty().addListener(e -> {
            controller.searchWeek();
        });

        controller.monthMonthField.textProperty().addListener(e -> {
            controller.searchMonth();
        });
        controller.weekMonthField.textProperty().addListener(e -> {
            controller.searchWeek();
        });

        controller.weekWeekField.textProperty().addListener(e -> {
            controller.searchWeek();
        });

        controller.yearDatePicker.setOnAction(e -> {
            LocalDate localDate = controller.yearDatePicker.getValue();
            controller.yearYearField.setText(String.valueOf(localDate.getYear()));
            controller.searchYear();
        });
        controller.seasonDatePicker.setOnAction(e -> {
            LocalDate localDate = controller.seasonDatePicker.getValue();
            controller.seasonYearField.setText(String.valueOf(localDate.getYear()));
            controller.seasonSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            controller.searchSeason();
        });
        controller.monthDatePicker.setOnAction(e -> {
            LocalDate localDate = controller.monthDatePicker.getValue();
            controller.monthYearField.setText(String.valueOf(localDate.getYear()));
            controller.monthSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            controller.monthMonthField.setText(String.valueOf(localDate.getMonthValue()));
            controller.searchMonth();
        });
        controller.weekDatePicker.setOnAction(e -> {
            LocalDate localDate = controller.weekDatePicker.getValue();
            controller.weekYearField.setText(String.valueOf(localDate.getYear()));
            controller.weekSeasonField.setText(String.valueOf((localDate.getMonthValue() + 2) / 3));
            controller.weekMonthField.setText(String.valueOf(localDate.getMonthValue()));
            controller.weekWeekField.setText(String.valueOf(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
            controller.searchWeek();
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

        controller.yearTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.seasonTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.monthTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        controller.weekTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));

        controller.yearPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        controller.seasonPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        controller.monthPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        controller.weekPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));

        controller.yearParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        controller.seasonParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        controller.monthParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        controller.weekParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));

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
        return p->new TableCell<SeisPlan, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    //TODO ce shi yi xia dao di dai ma fu gai ru he
                    //can save same code?
                    TableRow tableRow = this.getTableRow();
                    if (null != tableRow) {
                        SeisPlan colorPlan = (SeisPlan) tableRow.getItem();
                        if (null != colorPlan) {
                            tableRow.setStyle("");
                            setText(null);
                            if (C.PLAN_STATUS_ED.equals(colorPlan.getPlanStatus())) {
                                tableRow.setStyle("-fx-background-color: #00A2E8");
                            } else if (C.PLAN_STATUS_STOP.equals(colorPlan.getPlanStatus())) {
                                tableRow.setStyle("-fx-background-color: #FF7F27");
                            } else if (C.PLAN_STATUS_ING.equals(colorPlan.getPlanStatus())) {
                                LocalDate localDate = LocalDate.now();
                                int year = localDate.getYear();
                                int month = localDate.getMonthValue();
                                int season = (month + 2) / 3;
                                int week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                                boolean outColor = false;
                                boolean rightColor = false;
                                if (year < Integer.valueOf(colorPlan.getPlanYear())) {
                                    outColor = true;
                                } else if (C.PLAN_TYPE_YEAR.equals(colorPlan.getPlanType()) &&
                                        year == Integer.valueOf(colorPlan.getPlanYear())) {
                                    rightColor = true;
                                } else if (C.PLAN_TYPE_SEASON.equals(colorPlan.getPlanType())) {
                                    if (season < Integer.valueOf(colorPlan.getPlanSeason())) {
                                        outColor = true;
                                    } else if (season == Integer.valueOf(colorPlan.getPlanSeason())) {
                                        rightColor = true;
                                    }
                                } else if (C.PLAN_TYPE_MONTH.equals(colorPlan.getPlanType())) {
                                    if (month < Integer.valueOf(colorPlan.getPlanMonth())) {
                                        outColor = true;
                                    } else if (month == Integer.valueOf(colorPlan.getPlanMonth())) {
                                        rightColor = true;
                                    }
                                } else if (C.PLAN_TYPE_WEEK.equals(colorPlan.getPlanType())) {
                                    if (week < Integer.valueOf(colorPlan.getPlanWeek())) {
                                        outColor = true;
                                    } else if (week == Integer.valueOf(colorPlan.getPlanWeek())) {
                                        rightColor = true;
                                    }
                                }

                                if (rightColor) {
                                    tableRow.setStyle("-fx-background-color: #BEE02A");
                                }

                                if (outColor) {
                                    tableRow.setStyle("-fx-background-color: #F3705E");
                                }
                            }
                            setText(item);
                            return;
                        }
                    }
                    tableRow.setStyle("");
                    setText(null);
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
        taskTabStatus = PlanC.T_STATUS_MOD;
        thePlan = clickedPlan;
        parentId = null;

        controller.nameField.setText(clickedPlan.getPlanName());
        controller.timeField.setText(clickedPlan.getPlanTime());
        controller.contentArea.setText(clickedPlan.getPlanContent());

        controller.typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        clickedPlan.getPlanType()))
                .forEach(type -> {
                    controller.typeCombo.getSelectionModel().select(type);
                });
        controller.typeCombo.setDisable(true);

        Integer year = Integer.valueOf(clickedPlan.getPlanYear());
        Integer month = Integer.valueOf(clickedPlan.getPlanMonth());
        Integer day = 1;
        if(C.PLAN_TYPE_WEEK.equals(clickedPlan.getPlanType())){
            LocalDate localDate = LocalDate.of(year, month, 1);
            int firstDay = localDate.getDayOfWeek().getValue();
            int firstWeek = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
            int oldWeek = Integer.valueOf(clickedPlan.getPlanWeek());
            day = 7 * (oldWeek - firstWeek) + (7-firstDay + 1) ;
        }

        controller.datePicker.setValue(LocalDate.of(year, month, day));

        controller.taskTitleLabel.setText("修改任务：" + clickedPlan.getPlanName());
        controller.tabPane.getSelectionModel().select(controller.taskTab);
    }

    private void showContextMenu(TableView table, MouseEvent e) {
        SeisPlan selectedPlan = (SeisPlan) table.getSelectionModel().getSelectedItem();
        if (null != selectedPlan) {
            ContextMenu contextMenu = new ContextMenu();
            ObservableList<MenuItem> items = contextMenu.getItems();

            MenuItem menu6 = new MenuItem("填写完成度");
            menu6.setOnAction(p -> {
                inputPlanPer(selectedPlan);
            });
            items.add(menu6);

            MenuItem menu1 = new MenuItem("修改任务");
            menu1.setOnAction(p -> {
                modifyPlan(selectedPlan);
            });
            items.add(menu1);

            if (C.PLAN_STATUS_ING.equals(selectedPlan.getPlanStatus())) {
                MenuItem menu40 = new MenuItem("完成任务");
                menu40.setOnAction(p -> {
                    confirmFinishPlan(selectedPlan);
                });
                items.add(menu40);

                MenuItem menu4 = new MenuItem("终止任务");
                menu4.setOnAction(p -> {
                    confirmStopPlan(selectedPlan);
                });
                items.add(menu4);
            }

            if (!C.PLAN_TYPE_WEEK.equals(selectedPlan.getPlanType())
                    && !selectedPlan.getPlanName().startsWith(C.PLAN_OUT_COUNT)) {

                if (!C.PLAN_STATUS_ED.equals(selectedPlan.getPlanStatus())) {
                    MenuItem menu2 = new MenuItem("创建子任务");
                    menu2.setOnAction(p -> {
                        addSubPlan(selectedPlan);
                    });
                    items.add(menu2);
                }
                MenuItem menu3 = new MenuItem("查看子任务");
                menu3.setOnAction(p -> {
                    viewSubPlan(selectedPlan);
                });
                items.add(menu3);
            }

            if (C.PLAN_TYPE_SEASON.equals(selectedPlan.getPlanType())
                    || C.PLAN_TYPE_MONTH.equals(selectedPlan.getPlanType())) {

                MenuItem menu5 = new MenuItem("查看父任务");
                menu5.setOnAction(p -> {
                    viewSuperPlan(selectedPlan);
                });
                items.add(menu5);
            }

            contextMenu.setX(e.getScreenX());
            contextMenu.setY(e.getScreenY());
            contextMenu.show(controller.yearTable.getScene().getWindow());
        }
    }

    private void viewSubPlan(SeisPlan seisPlan) {
        SeisPlan plan = new SeisPlan();
        plan.setParentId(seisPlan.getId());
        List<SeisPlan> subPlanList = dao.selectByPlan(plan);
        controller.tabPane.getSelectionModel().selectNext();
        Tab selectTab = controller.tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if (selectTab == controller.seasonTab) {
            source = controller.seasonTable;
        } else if (selectTab == controller.monthTab) {
            source = controller.monthTable;
        } else if (selectTab == controller.weekTab) {
            source = controller.weekTable;
        }
        source.getItems().clear();
        source.getItems().addAll(subPlanList);
    }

    private void viewSuperPlan(SeisPlan seisPlan) {
        SeisPlan superPlan = dao.queryById(seisPlan.getParentId());
        controller.tabPane.getSelectionModel().selectPrevious();
        Tab selectTab = controller.tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if (selectTab == controller.yearTab) {
            source = controller.yearTable;
        } else if (selectTab == controller.seasonTab) {
            source = controller.seasonTable;
        } else if (selectTab == controller.monthTab) {
            source = controller.monthTable;
        }
        source.getItems().clear();
        source.getItems().add(superPlan);
    }

    private void addSubPlan(SeisPlan seisPlan) {
        taskTabStatus = PlanC.T_STATUS_SUB;
        thePlan = null;
        parentId = seisPlan.getId();
        controller.nameField.setText(seisPlan.getPlanName());
        //TODO count all sub task and count the result of the time
        controller.timeField.setText(seisPlan.getPlanTime().toString());
        controller.typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        Integer.valueOf(seisPlan.getPlanType()) - 100))
                .forEach(type -> {
                    controller.typeCombo.getSelectionModel().select(type);
                });
        if(C.PLAN_TYPE_MONTH.equals(seisPlan.getPlanType())){
            controller.datePicker.setValue(LocalDate.now().plusDays(5));
        }else{
            controller.datePicker.setValue(LocalDate.now().plusMonths(1));
        }
        controller.contentArea.setText(seisPlan.getPlanContent());
        controller.taskTitleLabel.setText("添加【子】任务：" + seisPlan.getPlanName());
        controller.tabPane.getSelectionModel().select(controller.taskTab);
    }

    private void confirmStopPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认" , "确定终止任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            stopPlan(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void stopPlan(SeisPlan sixPlan) {
        if(C.PLAN_TYPE_YEAR.equals(sixPlan.getPlanType())
                || C.PLAN_TYPE_SEASON.equals(sixPlan.getPlanType())){

            Integer id = sixPlan.getId();
            SeisPlan plan = new SeisPlan();
            plan.setParentId(id);
            List<SeisPlan> subPlanList = dao.selectByPlan(plan);
            subPlanList.stream().forEach(aPlan -> {
                stopPlan(aPlan);
            });
        }

        sixPlan.setPlanStatus(C.PLAN_STATUS_STOP.toString());
        dao.updateById(sixPlan);
    }

    private void confirmFinishPlan(SeisPlan seisPlan) {
        ConfirmDialogExt dialogExt = new ConfirmDialogExt("确认" , "确定完成任务：" + seisPlan.getPlanName());

        dialogExt.setOnAction(e -> {
            finishPlan(seisPlan);
            searchAllTableData();
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());

        dialogExt.show();
    }

    private void finishPlan(SeisPlan seisPlan) {

        if(C.PLAN_TYPE_YEAR.equals(seisPlan.getPlanType())
                || C.PLAN_TYPE_SEASON.equals(seisPlan.getPlanType()) ){
            Integer id = seisPlan.getId();
            SeisPlan plan = new SeisPlan();
            plan.setParentId(id);
            List<SeisPlan> subPlanList = dao.selectByPlan(plan);
            subPlanList.stream().forEach(aPlan -> {
                finishPlan(aPlan);
            });
        }
        seisPlan.setPlanStatus(C.PLAN_STATUS_ED.toString());
        dao.updateById(seisPlan);
    }

    private void inputPlanPer(SeisPlan seisPlan) {
        InputDialogExt dialogExt = new InputDialogExt("修改任务进度","[ "+seisPlan.getPlanName()+" ]进度");
        TextField textField = dialogExt.getTextField();
        dialogExt.setOnAction(e->{
            String text = textField.getText();
            if (StrUtil.isNotPositiveIntegralNumber(text)) {
                controller.tipLabel.setText("请输入数字");
            } else {
                processPlanPer(seisPlan, text);
                searchAllTableData();
            }
        });
        dialogExt.setTipContent(seisPlan.getPlanContent());
        dialogExt.show();
    }

    public void processPlanPer(SeisPlan seisPlan, String newPer) {

        if (C.PLAN_TYPE_YEAR.equals(seisPlan.getPlanType())
                || C.PLAN_TYPE_WEEK.equals(seisPlan.getPlanType()) ) {

            seisPlan.setPlanPer(newPer);
            dao.updateById(seisPlan);
        }else{
            Integer parentId = seisPlan.getParentId();
            Integer oldPer = Integer.valueOf(seisPlan.getPlanPer());
            SeisPlan parentPlan = dao.queryById(parentId);

            double dividend = Double.valueOf(seisPlan.getPlanTime());
            double divisor = Double.valueOf(parentPlan.getPlanTime());
            double multiple = dividend / divisor;

            Integer per = (int) (Integer.valueOf(parentPlan.getPlanPer()) + multiple * (Integer.valueOf(newPer)-oldPer));
            processPlanPer(parentPlan, String.valueOf(per));
        }
    }

    public void initTab() {

        controller.yearTab.selectedProperty().addListener(e -> {changeTab();});

        controller.monthTab.selectedProperty().addListener(e -> {changeTab();});

        controller.weekTab.selectedProperty().addListener(e -> {changeTab();});

        controller.seasonTab.selectedProperty().addListener(e -> {changeTab();});

        controller.tabPane.getSelectionModel().select(controller.weekTab);
        currentTab = controller.weekTab;
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
        parentId = null;
        controller.taskTitleLabel.setText("添加新任务");
        controller.nameField.setText("");
        controller.timeField.setText("");
        controller.typeCombo.getSelectionModel().selectFirst();
        controller.typeCombo.setDisable(false);
        controller.contentArea.setText("");
    }



    public void searchYear() {
        SeisPlan sixPlan = new SeisPlan();

        String year = controller.yearYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(year);
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        }

        Integer status = ((PlanStatus) controller.yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status.toString());

        sixPlan.setPlanType(C.PLAN_TYPE_YEAR.toString());
        yearData.clear();
        yearData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchSeason() {
        SeisPlan sixPlan = new SeisPlan();

        String season = controller.seasonSeasonField.getText();
        if (StrUtil.isPositiveIntegralNumber(season)) {
            sixPlan.setPlanSeason(season);
        } else if (StrUtil.isNotEmpty(season)) {
            return;
        }

        String year = controller.seasonYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(year);
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        }

        Integer status = ((PlanStatus) controller.seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status.toString());

        sixPlan.setPlanType(C.PLAN_TYPE_SEASON.toString());
        seasonData.clear();
        seasonData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchMonth() {
        SeisPlan sixPlan = new SeisPlan();

        String month = controller.monthMonthField.getText();
        if (StrUtil.isPositiveIntegralNumber(month)) {
            sixPlan.setPlanMonth(month);
        } else if (StrUtil.isNotEmpty(month)) {
            return;
        }

        String year = controller.monthYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(year);
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        } else if (StrUtil.isEmpty(year)) {
            String season = controller.monthSeasonField.getText();
            if (StrUtil.isPositiveIntegralNumber(season)) {
                sixPlan.setPlanSeason(season);
            } else if (StrUtil.isNotEmpty(season)) {
                return;
            }
        }

        Integer status = ((PlanStatus) controller.monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status.toString());

        sixPlan.setPlanType(C.PLAN_TYPE_MONTH.toString());
        monthData.clear();
        monthData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchWeek() {
        SeisPlan sixPlan = new SeisPlan();

        String week = controller.weekWeekField.getText();
        if (StrUtil.isPositiveIntegralNumber(week)) {
            sixPlan.setPlanWeek(week);
        } else if (StrUtil.isNotEmpty(week)) {
            return;
        }

        String year = controller.weekYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(year);
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        } else if (StrUtil.isEmpty(year)) {
            String season = controller.weekSeasonField.getText();
            if (StrUtil.isPositiveIntegralNumber(season)) {
                sixPlan.setPlanSeason(season);
            } else if (StrUtil.isNotEmpty(season)) {
                return;
            } else if (StrUtil.isEmpty(season)) {
                String month = controller.weekMonthField.getText();
                if (StrUtil.isPositiveIntegralNumber(month)) {
                    sixPlan.setPlanMonth(month);
                } else if (StrUtil.isNotEmpty(month)) {
                    return;
                }
            }
        }

        Integer status = ((PlanStatus) controller.weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status.toString());

        sixPlan.setPlanType(C.PLAN_TYPE_WEEK.toString());
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

        String timeStr = controller.timeField.getText();
        if (StrUtil.isNotNaturalNumber(timeStr)) {
            controller.tipLabel.setText("日期请输入自然数");
            controller.timeField.requestFocus();
            return;
        }

        LocalDate localDate = controller.datePicker.getValue();
        if (null == localDate) {
            controller.tipLabel.setText("日期为空");
            controller.datePicker.requestFocus();
            return;
        }

        String content = controller.contentArea.getText();

        Integer type = ((PlanType) controller.typeCombo.getSelectionModel()
                .getSelectedItem()).getTypeValue();

        boolean isInsert = false;
        if (null == thePlan) {
            isInsert = true;
            thePlan = new SeisPlan();
            if (null != parentId) {
                thePlan.setParentId(parentId);
            } else {
                thePlan.setParentId(0);
            }
            thePlan.setPlanStatus(C.PLAN_STATUS_ING.toString());
            thePlan.setSourceId(0);
            thePlan.setPlanPer("0");
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(timeStr);
        thePlan.setPlanType(type.toString());
        thePlan.setPlanYear(String.valueOf(localDate.getYear()));
        thePlan.setPlanMonth(String.valueOf(localDate.getMonthValue()));
        thePlan.setPlanSeason(String.valueOf((localDate.getMonthValue() + 2) / 3));
        thePlan.setPlanWeek(String.valueOf(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
        thePlan.setPlanContent(content);
        if(name.startsWith(C.PLAN_OUT_COUNT)){
            thePlan.setPlanType(C.PLAN_TYPE_YEAR.toString());
            type = C.PLAN_TYPE_YEAR;
            thePlan.setSourceId(-1);
            thePlan.setParentId(0);
        }
        if (isInsert) {
            if (thePlan.getParentId() == 0 && type != C.PLAN_TYPE_YEAR) {
                thePlan.setId(0);
                for (int i = C.PLAN_TYPE_YEAR; i >= type; i -= 100) {
                    thePlan.setPlanType(String.valueOf(i));
                    thePlan.setParentId(thePlan.getId());
                    thePlan.setId(null);
                    dao.insert(thePlan);
                }
            } else {
                dao.insert(thePlan);
            }
            controller.tipLabel.setText("添加任务成功：" + name);
            initTaskTab();
        } else {
            dao.updateById(thePlan);
            controller.tipLabel.setText("更新任务成功：" + name);
        }
        searchAllTableData();
    }

    public void typeChange() {
        Integer type = ((PlanType)(controller.typeCombo.getSelectionModel().getSelectedItem())).getTypeValue();
        if( C.PLAN_TYPE_WEEK.equals(type)){
            controller.datePicker.setValue(LocalDate.now().plusDays(5));
        }else{
            controller.datePicker.setValue(LocalDate.now().plusMonths(1));
        }
    }
}
