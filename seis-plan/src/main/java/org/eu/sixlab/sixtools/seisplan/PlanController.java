/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.seisplan;

import com.sun.javafx.scene.control.skin.TableColumnHeader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.comun.bean.SeisPlan;
import org.eu.sixlab.sixtools.comun.dao.PlanDao;
import org.eu.sixlab.sixtools.comun.util.C;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanStatus;
import org.eu.sixlab.sixtools.comun.bean.ext.PlanType;
import org.eu.sixlab.sixutil.StrUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/5 9:31
 */
public class PlanController implements Initializable {

    private PlanService service = new PlanService(PlanController.this);

    @FXML
    public TabPane tabPane;
    @FXML
    public ComboBox yearStatusCombo;
    @FXML
    public TextField yearYearField;
    @FXML
    public DatePicker yearDatePicker;
    @FXML
    public TableView yearTable;
    @FXML
    public TableColumn yearIdColumn;
    @FXML
    public TableColumn yearNameColumn;
    @FXML
    public TableColumn yearTimeColumn;
    @FXML
    public TableColumn yearPerColumn;
    @FXML
    public TableColumn yearStatusColumn;
    @FXML
    public TableColumn yearParentColumn;
    @FXML
    public TableView seasonTable;
    @FXML
    public TableColumn seasonParentColumn;
    @FXML
    public TableColumn seasonStatusColumn;
    @FXML
    public TableColumn seasonPerColumn;
    @FXML
    public TableColumn seasonTimeColumn;
    @FXML
    public TableColumn seasonNameColumn;
    @FXML
    public TableColumn seasonIdColumn;
    @FXML
    public ComboBox seasonStatusCombo;
    @FXML
    public TextField seasonYearField;
    @FXML
    public TextField seasonSeasonField;
    @FXML
    public DatePicker seasonDatePicker;
    @FXML
    public TableView monthTable;
    @FXML
    public TableColumn monthIdColumn;
    @FXML
    public TableColumn monthParentColumn;
    @FXML
    public TableColumn monthStatusColumn;
    @FXML
    public TableColumn monthPerColumn;
    @FXML
    public TableColumn monthTimeColumn;
    @FXML
    public TableColumn monthNameColumn;
    @FXML
    public ComboBox monthStatusCombo;
    @FXML
    public TextField monthYearField;
    @FXML
    public TextField monthSeasonField;
    @FXML
    public TextField monthMonthField;
    @FXML
    public DatePicker monthDatePicker;
    @FXML
    public TableView weekTable;
    @FXML
    public TableColumn weekIdColumn;
    @FXML
    public TableColumn weekNameColumn;
    @FXML
    public TableColumn weekTimeColumn;
    @FXML
    public TableColumn weekPerColumn;
    @FXML
    public TableColumn weekStatusColumn;
    @FXML
    public TableColumn weekParentColumn;
    @FXML
    public ComboBox weekStatusCombo;
    @FXML
    public TextField weekYearField;
    @FXML
    public TextField weekSeasonField;
    @FXML
    public TextField weekMonthField;
    @FXML
    public TextField weekWeekField;
    @FXML
    public DatePicker weekDatePicker;
    @FXML
    public TextField nameField;
    @FXML
    public TextField timeField;
    @FXML
    public ComboBox typeCombo;
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextArea contentArea;
    @FXML
    public Tab yearTab;
    @FXML
    public Tab seasonTab;
    @FXML
    public Tab monthTab;
    @FXML
    public Tab weekTab;
    @FXML
    public Tab taskTab;
    @FXML
    public Label tipLabel;
    @FXML
    public Label taskTitleLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        service.initControlData();
        service.initControl();
//        initComboData();  initFieldData();
//        initCombo(yearStatusCombo, seasonStatusCombo, monthStatusCombo, weekStatusCombo);
//        initField();

//        initTable();
//        initTab();
    }

    /**
     * 初始化各种TextField的值改变事件。绑定回车事件
     */
    private void initField() {
        yearYearField.textProperty().addListener(e -> {
            searchYear();
        });
        seasonYearField.textProperty().addListener(e -> {
            searchSeason();
        });
        monthYearField.textProperty().addListener(e -> {
            searchMonth();
        });
        weekYearField.textProperty().addListener(e -> {
            searchWeek();
        });

        seasonSeasonField.textProperty().addListener(e -> {
            searchSeason();
        });
        monthSeasonField.textProperty().addListener(e -> {
            searchMonth();
        });
        weekSeasonField.textProperty().addListener(e -> {
            searchWeek();
        });

        monthMonthField.textProperty().addListener(e -> {
            searchMonth();
        });
        weekMonthField.textProperty().addListener(e -> {
            searchWeek();
        });

        weekWeekField.textProperty().addListener(e -> {
            searchWeek();
        });

        yearDatePicker.setOnAction(e -> {
            LocalDate localDate = yearDatePicker.getValue();
            yearYearField.setText(String.valueOf(localDate.getYear()));
            searchYear();
        });
        seasonDatePicker.setOnAction(e -> {
            LocalDate localDate = seasonDatePicker.getValue();
            seasonYearField.setText(String.valueOf(localDate.getYear()));
            seasonSeasonField.setText(String.valueOf(
                    (localDate.getMonthValue() + 2) / 3));
            searchSeason();
        });
        monthDatePicker.setOnAction(e -> {
            LocalDate localDate = monthDatePicker.getValue();
            monthYearField.setText(String.valueOf(localDate.getYear()));
            monthSeasonField.setText(String.valueOf(
                    (localDate.getMonthValue() + 2) / 3));
            monthMonthField.setText(String.valueOf(localDate.getMonthValue()));
            searchMonth();
        });
        weekDatePicker.setOnAction(e -> {
            LocalDate localDate = weekDatePicker.getValue();
            weekYearField.setText(String.valueOf(localDate.getYear()));
            weekSeasonField.setText(String.valueOf(
                    (localDate.getMonthValue() + 2) / 3));
            weekMonthField.setText(String.valueOf(localDate.getMonthValue()));
            weekWeekField.setText(String.valueOf(localDate.get(
                    IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
            searchWeek();
        });
    }

    /**
     * 设置Table每一行的颜色。
     */
    private void initTableCellFactory() {

        TableColumn[] tableColumns = new TableColumn[]{
                yearNameColumn, seasonNameColumn, monthNameColumn, weekNameColumn
        };

        for (TableColumn tableColumn : tableColumns) {
            tableColumn.setCellFactory(p -> {
                return new TableCell<SeisPlan, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        TableRow tableRow = this.getTableRow();
                        if (!empty && null != tableRow) {
                            SeisPlan sixPlan = (SeisPlan) tableRow.getItem();
                            if (null != sixPlan) {
                                tableRow.setStyle("");
                                setText(null);
                                if (C.PLAN_STATUS_ED.equals(sixPlan.getPlanStatus())) {
                                    tableRow.setStyle("-fx-background-color: #00A2E8");
                                } else if (C.PLAN_STATUS_STOP.equals(sixPlan.getPlanStatus())) {
                                    tableRow.setStyle("-fx-background-color: #FF7F27");
                                } else if (C.PLAN_STATUS_ING.equals(sixPlan.getPlanStatus())) {
                                    LocalDate localDate = LocalDate.now();
                                    int year = localDate.getYear();
                                    int month = localDate.getMonthValue();
                                    int season = (month + 2) / 3;
                                    int week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
                                    boolean outColor = false;
                                    boolean rightColor = false;
                                    if (year < Integer.valueOf(sixPlan.getPlanYear())) {
                                        outColor = true;
                                    } else if (C.PLAN_TYPE_YEAR.equals(sixPlan.getPlanType()) &&
                                            year == Integer.valueOf(sixPlan.getPlanYear())) {
                                        rightColor = true;
                                    } else if (C.PLAN_TYPE_SEASON.equals(sixPlan.getPlanType())) {
                                        if (season < Integer.valueOf(sixPlan.getPlanSeason())) {
                                            outColor = true;
                                        } else if (season == Integer.valueOf(sixPlan.getPlanSeason())) {
                                            rightColor = true;
                                        }
                                    } else if (C.PLAN_TYPE_MONTH.equals(sixPlan.getPlanType())) {
                                        if (month < Integer.valueOf(sixPlan.getPlanMonth())) {
                                            outColor = true;
                                        } else if (month == Integer.valueOf(sixPlan.getPlanMonth())) {
                                            rightColor = true;
                                        }
                                    } else if (C.PLAN_TYPE_WEEK.equals(sixPlan.getPlanType())) {
                                        if (week < Integer.valueOf(sixPlan.getPlanWeek())) {
                                            outColor = true;
                                        } else if (week == Integer.valueOf(sixPlan.getPlanWeek())) {
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
                };
            });
        }
    }

    /**
     * 将TableColumn与对象结合
     */
    private void initTableCellValueFactory() {
        yearIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        seasonIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        monthIdColumn.setCellValueFactory(new PropertyValueFactory("id"));
        weekIdColumn.setCellValueFactory(new PropertyValueFactory("id"));

        yearNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        seasonNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        monthNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        weekNameColumn.setCellValueFactory(new PropertyValueFactory("planName"));

        yearTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        seasonTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        monthTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        weekTimeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));

        yearPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        seasonPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        monthPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        weekPerColumn.setCellValueFactory(new PropertyValueFactory("planPer"));

        yearParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        seasonParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        monthParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        weekParentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));

        yearStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        seasonStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        monthStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
        weekStatusColumn.setCellValueFactory(new PropertyValueFactory("planStatusName"));
    }

    /**
     * Table右键点击后的菜单。
     *
     * @param table 所点击的Table
     * @param e     鼠标事件
     */
    private void showContextMenu(TableView table, MouseEvent e) {
        SeisPlan sixPlan = (SeisPlan) table.getSelectionModel().getSelectedItem();
        if (null != sixPlan) {
            List<MenuItem> itemArrayList = new ArrayList<>();

            MenuItem menu6 = new MenuItem("填写完成度");
            menu6.setOnAction(p -> {
                inputPlanPer(sixPlan);
            });
            itemArrayList.add(menu6);

            MenuItem menu1 = new MenuItem("修改任务");
            menu1.setOnAction(p -> {
                modifyPlan(sixPlan);
            });
            itemArrayList.add(menu1);

            if (C.PLAN_STATUS_ING.equals(sixPlan.getPlanStatus())) {
                MenuItem menu40 = new MenuItem("完成任务");
                menu40.setOnAction(p -> {
                    confirmFinishPlan(sixPlan);
                });
                itemArrayList.add(menu40);

                MenuItem menu4 = new MenuItem("终止任务");
                menu4.setOnAction(p -> {
                    confirmStopPlan(sixPlan);
                });
                itemArrayList.add(menu4);
            }

            if (!C.PLAN_TYPE_WEEK.equals(sixPlan.getPlanType()) && !sixPlan.getPlanName().startsWith(C.PLAN_OUT_COUNT)) {
                if (!C.PLAN_STATUS_ED.equals(sixPlan.getPlanStatus())) {
                    MenuItem menu2 = new MenuItem("创建子任务");
                    menu2.setOnAction(p -> {
                        addSubPlan(sixPlan);
                    });
                    itemArrayList.add(menu2);
                }
                MenuItem menu3 = new MenuItem("查看子任务");
                menu3.setOnAction(p -> {
                    viewSubPlan(sixPlan);
                });
                itemArrayList.add(menu3);
            }

            if (C.PLAN_TYPE_SEASON.equals(sixPlan.getPlanType()) || C.PLAN_TYPE_MONTH.equals(sixPlan.getPlanType())) {
                MenuItem menu5 = new MenuItem("查看父任务");
                menu5.setOnAction(p -> {
                    viewSuperPlan(sixPlan);
                });
                itemArrayList.add(menu5);
            }

            ContextMenu contextMenu = new ContextMenu(itemArrayList.toArray(new MenuItem[]{}));
            contextMenu.setX(e.getScreenX());
            contextMenu.setY(e.getScreenY());
            contextMenu.show(yearTable.getScene().getWindow());
        }
    }

    private void inputPlanPer(SeisPlan sixPlan) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label1 = new Label("任务：" + sixPlan.getPlanName());
        label1.setFont(Font.font(20));
        Label label2 = new Label("任务完成度：");
        Label label3 = new Label("");
        label3.setTextFill(Color.RED);

        TextField textField = new TextField();
        textField.setPromptText("请输入数字");
        textField.setText("100");
        Button okBtn = new Button(" 确定 ");
        Button koBtn = new Button(" 取消 ");

        okBtn.setOnAction(e -> {
            String text = textField.getText();
            if (StrUtil.isNotPositiveIntegralNumber(text)) {
                label3.setText("请输入数字");
                textField.requestFocus();
                return;
            } else {
                service.processPlan(sixPlan, Integer.valueOf(text));
                searchData();
                stage.hide();
            }
        });
        koBtn.setOnAction(e -> {
            stage.hide();
        });

        label1.setLayoutX(50);
        label1.setLayoutY(20);
        label2.setLayoutX(30);
        label2.setLayoutY(80);
        textField.setLayoutX(150);
        textField.setLayoutY(80);
        label3.setLayoutX(150);
        label3.setLayoutY(110);
        koBtn.setLayoutX(100);
        koBtn.setLayoutY(140);
        okBtn.setLayoutX(180);
        okBtn.setLayoutY(140);
        pane.getChildren().addAll(label1, label2, label3, textField, koBtn, okBtn);

        Scene scene = new Scene(pane, 300, 180);
        stage.setScene(scene);
        stage.setTitle("修改任务进度");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void modifyPlan(SeisPlan sixPlan) {
        thePlan = sixPlan;
        parentId = null;
        nameField.setText(sixPlan.getPlanName());
        timeField.setText(sixPlan.getPlanTime().toString());
        typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        sixPlan.getPlanType()))
                .forEach(type -> {
                    typeCombo.getSelectionModel().select(type);
                });
        typeCombo.setDisable(true);
        datePicker.setValue(LocalDate.of(sixPlan.getPlanYear(),
                sixPlan.getPlanMonth(), 1));
        contentArea.setText(sixPlan.getPlanContent());
        taskTitleLabel.setText("修改任务：" + sixPlan.getPlanName());
        tabPane.getSelectionModel().select(taskTab);
    }

    private void confirmFinishPlan(SeisPlan sixPlan) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label1 = new Label("确定完成任务：" + sixPlan.getPlanName());
        label1.setFont(Font.font(20));
        label1.setTextFill(Color.RED);

        Button okBtn = new Button(" 确定 ");
        Button koBtn = new Button(" 取消 ");

        okBtn.setOnAction(e -> {
            finishPlan(sixPlan);
            searchData();
            stage.hide();
        });
        koBtn.setOnAction(e -> {
            stage.hide();
        });

        label1.setLayoutX(50);
        label1.setLayoutY(20);
        koBtn.setLayoutX(100);
        koBtn.setLayoutY(140);
        okBtn.setLayoutX(180);
        okBtn.setLayoutY(140);
        pane.getChildren().addAll(label1, koBtn, okBtn);

        Scene scene = new Scene(pane, 300, 180);
        stage.setScene(scene);
        stage.setTitle("确认");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void finishPlan(SeisPlan sixPlan) {
        Integer id = sixPlan.getId();
        SeisPlan plan = new SeisPlan();
        plan.setParentId(id);
        if(C.PLAN_TYPE_MONTH < sixPlan.getPlanType()){
            List<SeisPlan> sixPlanList = dao.selectByPlan(plan);
            sixPlanList.stream().forEach(aPlan -> {
                finishPlan(aPlan);
            });
        }
        sixPlan.setPlanStatus(C.PLAN_STATUS_ED);
        dao.updateById(sixPlan);
    }

    private void confirmStopPlan(SeisPlan sixPlan) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label1 = new Label("确定终止任务：" + sixPlan.getPlanName());
        label1.setFont(Font.font(20));
        label1.setTextFill(Color.RED);

        Button okBtn = new Button(" 确定 ");
        Button koBtn = new Button(" 取消 ");

        okBtn.setOnAction(e -> {
            stopPlan(sixPlan);
            searchData();
            stage.hide();
        });
        koBtn.setOnAction(e -> {
            stage.hide();
        });

        label1.setLayoutX(50);
        label1.setLayoutY(20);
        koBtn.setLayoutX(100);
        koBtn.setLayoutY(140);
        okBtn.setLayoutX(180);
        okBtn.setLayoutY(140);
        pane.getChildren().addAll(label1, koBtn, okBtn);

        Scene scene = new Scene(pane, 300, 180);
        stage.setScene(scene);
        stage.setTitle("确认");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void stopPlan(SeisPlan sixPlan) {
        Integer id = sixPlan.getId();
        SeisPlan plan = new SeisPlan();
        plan.setParentId(id);
        if(C.PLAN_TYPE_MONTH < sixPlan.getPlanType()){
            List<SeisPlan> sixPlanList = dao.selectByPlan(plan);
            sixPlanList.stream().forEach(aPlan -> {
                stopPlan(aPlan);
            });
        }
        sixPlan.setPlanStatus(C.PLAN_STATUS_STOP);
        dao.updateById(sixPlan);
    }

    private void addSubPlan(SeisPlan sixPlan) {
        thePlan = null;
        parentId = sixPlan.getId();
        nameField.setText(sixPlan.getPlanName());
        timeField.setText(sixPlan.getPlanTime().toString());
        typeCombo.getItems().stream()
                .filter(type -> ((PlanType) type).getTypeValue().equals(
                        sixPlan.getPlanType() - 100))
                .forEach(type -> {
                    typeCombo.getSelectionModel().select(type);
                });
        if(C.PLAN_TYPE_MONTH.equals(sixPlan.getPlanType())){
            datePicker.setValue(LocalDate.now().plusDays(5));
        }else{
            datePicker.setValue(LocalDate.now().plusMonths(1));
        }
        contentArea.setText(sixPlan.getPlanContent());
        taskTitleLabel.setText("添加【子】任务：" + sixPlan.getPlanName());
        tabPane.getSelectionModel().select(taskTab);
    }

    private void viewSubPlan(SeisPlan sixPlan) {
        SeisPlan plan = new SeisPlan();
        plan.setParentId(sixPlan.getId());
        List<SeisPlan> sixPlanList = dao.selectByPlan(plan);
        tabPane.getSelectionModel().selectNext();
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if (selectTab == seasonTab) {
            source = seasonTable;
        } else if (selectTab == monthTab) {
            source = monthTable;
        } else if (selectTab == weekTab) {
            source = weekTable;
        }
        source.getItems().clear();
        source.getItems().addAll(sixPlanList);
    }

    private void viewSuperPlan(SeisPlan sixPlan) {
        SeisPlan superPlan = dao.queryById(sixPlan.getParentId());
        tabPane.getSelectionModel().selectPrevious();
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if (selectTab == yearTab) {
            source = yearTable;
        } else if (selectTab == seasonTab) {
            source = seasonTable;
        } else if (selectTab == monthTab) {
            source = monthTable;
        }
        source.getItems().clear();
        source.getItems().add(superPlan);
    }

    private void initTab() {

        yearTab.selectedProperty().addListener(e -> {changeTab();});

        monthTab.selectedProperty().addListener(e -> {changeTab();});

        weekTab.selectedProperty().addListener(e -> {changeTab();});

        seasonTab.selectedProperty().addListener(e -> {changeTab();});

        tabPane.getSelectionModel().select(weekTab);
        initTaskTab();
    }

    private void initTaskTab() {
        thePlan = null;
        parentId = null;
        taskTitleLabel.setText("添加新任务");
        nameField.setText("");
        timeField.setText("");
        typeCombo.getSelectionModel().selectFirst();
        typeCombo.setDisable(false);
        contentArea.setText("");
    }

    private void changeTab() {
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        if (selectTab == yearTab && yearTab.isSelected()) {
            tipLabel.setText("任务数量为：" + yearData.size());
        } else if (selectTab == seasonTab && seasonTab.isSelected()) {
            tipLabel.setText("任务数量为：" + seasonData.size());
        } else if (selectTab == monthTab && monthTab.isSelected()) {
            tipLabel.setText("任务数量为：" + monthData.size());
        } else if (selectTab == weekTab && weekTab.isSelected()) {
            tipLabel.setText("任务数量为：" + weekData.size());
        }
    }

    private void searchData() {
        searchYear();
        searchSeason();
        searchMonth();
        searchWeek();
    }

    public void searchYear() {
        SeisPlan sixPlan = new SeisPlan();

        String year = yearYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(Integer.valueOf(year));
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        }

        Integer status = ((PlanStatus) yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_YEAR);
        yearData.clear();
        yearData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchSeason() {
        SeisPlan sixPlan = new SeisPlan();

        String season = seasonSeasonField.getText();
        if (StrUtil.isPositiveIntegralNumber(season)) {
            sixPlan.setPlanSeason(Integer.valueOf(season));
        } else if (StrUtil.isNotEmpty(season)) {
            return;
        }

        String year = seasonYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(Integer.valueOf(year));
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        }

        Integer status = ((PlanStatus) seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_SEASON);
        seasonData.clear();
        seasonData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchMonth() {
        SeisPlan sixPlan = new SeisPlan();

        String month = monthMonthField.getText();
        if (StrUtil.isPositiveIntegralNumber(month)) {
            sixPlan.setPlanMonth(Integer.valueOf(month));
        } else if (StrUtil.isNotEmpty(month)) {
            return;
        }

        String year = monthYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(Integer.valueOf(year));
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        } else if (StrUtil.isEmpty(year)) {
            String season = monthSeasonField.getText();
            if (StrUtil.isPositiveIntegralNumber(season)) {
                sixPlan.setPlanSeason(Integer.valueOf(season));
            } else if (StrUtil.isNotEmpty(season)) {
                return;
            }
        }

        Integer status = ((PlanStatus) monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_MONTH);
        monthData.clear();
        monthData.addAll(dao.selectByPlan(sixPlan));
    }

    public void searchWeek() {
        SeisPlan sixPlan = new SeisPlan();

        String week = weekWeekField.getText();
        if (StrUtil.isPositiveIntegralNumber(week)) {
            sixPlan.setPlanWeek(Integer.valueOf(week));
        } else if (StrUtil.isNotEmpty(week)) {
            return;
        }

        String year = weekYearField.getText();
        if (StrUtil.isPositiveIntegralNumber(year)) {
            sixPlan.setPlanYear(Integer.valueOf(year));
        } else if (StrUtil.isNotEmpty(year)) {
            return;
        } else if (StrUtil.isEmpty(year)) {
            String season = weekSeasonField.getText();
            if (StrUtil.isPositiveIntegralNumber(season)) {
                sixPlan.setPlanSeason(Integer.valueOf(season));
            } else if (StrUtil.isNotEmpty(season)) {
                return;
            } else if (StrUtil.isEmpty(season)) {
                String month = weekMonthField.getText();
                if (StrUtil.isPositiveIntegralNumber(month)) {
                    sixPlan.setPlanMonth(Integer.valueOf(month));
                } else if (StrUtil.isNotEmpty(month)) {
                    return;
                }
            }
        }

        Integer status = ((PlanStatus) weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(C.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(C.PLAN_TYPE_WEEK);
        weekData.clear();
        weekData.addAll(dao.selectByPlan(sixPlan));
    }

    public void okPlan() {
        String name = nameField.getText();
        if (StrUtil.isEmpty(name)) {
            tipLabel.setText("任务名为空");
            nameField.requestFocus();
            return;
        }

        String timeStr = timeField.getText();
        if (StrUtil.isNotNaturalNumber(timeStr)) {
            tipLabel.setText("日期请输入自然数");
            timeField.requestFocus();
            return;
        }

        LocalDate localDate = datePicker.getValue();
        if (null == localDate) {
            tipLabel.setText("日期为空");
            datePicker.requestFocus();
            return;
        }

        String content = contentArea.getText();

        Integer type = ((PlanType) typeCombo.getSelectionModel()
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
            thePlan.setPlanStatus(C.PLAN_STATUS_ING);
            thePlan.setSourceId(0);
            thePlan.setPlanPer(0);
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(Integer.valueOf(timeStr));
        thePlan.setPlanType(type);
        thePlan.setPlanYear(localDate.getYear());
        thePlan.setPlanMonth(localDate.getMonthValue());
        thePlan.setPlanSeason((localDate.getMonthValue() + 2) / 3);
        thePlan.setPlanWeek(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        thePlan.setPlanContent(content);
        if(name.startsWith(C.PLAN_OUT_COUNT)){
            thePlan.setPlanType(C.PLAN_TYPE_YEAR);
            type = C.PLAN_TYPE_YEAR;
            thePlan.setSourceId(-1);
            thePlan.setParentId(0);
        }
        if (isInsert) {
            if (thePlan.getParentId() == 0 && type != C.PLAN_TYPE_YEAR) {
                thePlan.setId(0);
                for (int i = C.PLAN_TYPE_YEAR; i >= type; i -= 100) {
                    thePlan.setPlanType(i);
                    thePlan.setParentId(thePlan.getId());
                    thePlan.setId(null);
                    dao.insert(thePlan);
                }
            } else {
                dao.insert(thePlan);
            }
            tipLabel.setText("添加任务成功：" + name);
            initTaskTab();
        } else {
            dao.updateById(thePlan);
            tipLabel.setText("更新任务成功：" + name);
        }
        searchData();
    }

    public void resetPlan() {
        initTaskTab();
    }

//*************************************************************************************/

    public void typeChange(ActionEvent event) {
        service.typeChange(typeCombo, datePicker);
    }
}
