/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.eu.sixlab.sixtools.common.beans.SixPlan;
import org.eu.sixlab.sixtools.common.util.Constant;
import org.eu.sixlab.sixtools.sixplan.combo.PlanStatus;
import org.eu.sixlab.sixtools.sixplan.combo.PlanType;
import org.eu.sixlab.sixtools.sixplan.dao.Dao;
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
public class MainController implements Initializable {

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

    private final ObservableList<SixPlan> yearData = FXCollections
            .observableArrayList();

    private final ObservableList<SixPlan> seasonData = FXCollections
            .observableArrayList();

    private final ObservableList<SixPlan> monthData = FXCollections
            .observableArrayList();

    private final ObservableList<SixPlan> weekData = FXCollections
            .observableArrayList();

    private final ObservableList<PlanStatus> statusData = FXCollections
            .observableArrayList();

    private final ObservableList<PlanType> typeData = FXCollections
            .observableArrayList();

    private Integer parentId = null;

    private SixPlan thePlan;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initComboData();
        initCombo(yearStatusCombo, seasonStatusCombo, monthStatusCombo,
                  weekStatusCombo);
        initFieldData();
        initField();
        initTable();
        initTab();
    }

    private void initComboData() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);

        List<PlanType> typeList = PlanType.allTypes();
        typeData.clear();
        typeData.addAll(typeList);
    }

    private void initCombo(ComboBox... statusCombo) {
        for( ComboBox comboBox : statusCombo ){
            comboBox.setItems(statusData);
            comboBox.getSelectionModel().selectFirst();

            comboBox.setCellFactory(p->{
                return new ListCell<PlanStatus>() {
                    @Override
                    protected void updateItem(PlanStatus planStatus, boolean bln) {
                        super.updateItem(planStatus, bln);
                        if( planStatus != null ){
                            setText(planStatus.getStatusName());
                        }else{
                            setText(null);
                        }
                    }
                };
            });
        }

        typeCombo.setItems(typeData);
        typeCombo.getSelectionModel().selectFirst();

        typeCombo.setCellFactory(p->{
            return new ListCell<PlanType>() {
                @Override
                protected void updateItem(PlanType planType, boolean bln) {
                    super.updateItem(planType, bln);
                    if( planType != null ){
                        setText(planType.getTypeName());
                    }else{
                        setText(null);
                    }
                }
            };
        });
    }

    private void initFieldData() {
        LocalDate localDate = LocalDate.now();

        Integer year = localDate.getYear();
        Integer month = localDate.getMonthValue();
        Integer season = (month + 2)/3;
        Integer week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        yearYearField.setText(year.toString());
        seasonYearField.setText(year.toString());
        monthYearField.setText(year.toString());
        weekYearField.setText(year.toString());

        seasonSeasonField.setText(season.toString());
        monthSeasonField.setText(season.toString());
        weekSeasonField.setText(season.toString());

        monthMonthField.setText(month.toString());
        weekMonthField.setText(month.toString());

        weekWeekField.setText(week.toString());

        yearDatePicker.setValue(localDate);
        seasonDatePicker.setValue(localDate);
        monthDatePicker.setValue(localDate);
        weekDatePicker.setValue(localDate);
    }

    private void initField() {
        yearYearField.textProperty().addListener(e->{searchYear();});
        seasonYearField.textProperty().addListener(e->{searchSeason();});
        monthYearField.textProperty().addListener(e->{searchMonth();});
        weekYearField.textProperty().addListener(e->{searchWeek();});

        seasonSeasonField.textProperty().addListener(e->{searchSeason();});
        monthSeasonField.textProperty().addListener(e->{searchMonth();});
        weekSeasonField.textProperty().addListener(e->{searchWeek();});

        monthMonthField.textProperty().addListener(e->{searchMonth();});
        weekMonthField.textProperty().addListener(e->{searchWeek();});

        weekWeekField.textProperty().addListener(e->{searchWeek();});

        yearDatePicker.setOnAction(e->{
            LocalDate localDate = yearDatePicker.getValue();
            yearYearField.setText(String.valueOf(localDate.getYear()));
            searchYear();
        });
        seasonDatePicker.setOnAction(e->{
            LocalDate localDate = seasonDatePicker.getValue();
            seasonYearField.setText(String.valueOf(localDate.getYear()));
            seasonSeasonField.setText(String.valueOf((localDate.getMonthValue()+2)/3));
            searchSeason();
        });
        monthDatePicker.setOnAction(e->{
            LocalDate localDate = monthDatePicker.getValue();
            monthYearField.setText(String.valueOf(localDate.getYear()));
            monthSeasonField.setText(String.valueOf((localDate.getMonthValue()+2)/3));
            monthMonthField.setText(String.valueOf(localDate.getMonthValue()));
            searchMonth();
        });
        weekDatePicker.setOnAction(e->{
            LocalDate localDate = weekDatePicker.getValue();
            weekYearField.setText(String.valueOf(localDate.getYear()));
            weekSeasonField.setText(String.valueOf((localDate.getMonthValue()+2)/3));
            weekMonthField.setText(String.valueOf(localDate.getMonthValue()));
            weekWeekField.setText(String.valueOf(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
            searchWeek();
        });
    }

    private void initTable() {
        initTableCellValueFactory();

        yearTable.setItems(yearData);
        seasonTable.setItems(seasonData);
        monthTable.setItems(monthData);
        weekTable.setItems(weekData);

        yearTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if( e.getButton().equals(MouseButton.SECONDARY) ){
                showContextMenu(yearTable, e);
            }
        });

        seasonTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
            if( e.getButton().equals(MouseButton.SECONDARY) ){
                showContextMenu(seasonTable, e);
            }
        });

        monthTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
            if( e.getButton().equals(MouseButton.SECONDARY) ){
                showContextMenu(monthTable, e);
            }
        });

        weekTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e->{
            if( e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() == 2 ){
                SixPlan sixPlan = (SixPlan)weekTable.getSelectionModel().getSelectedItem();
                processPlan(sixPlan);
            }else if( e.getButton().equals(MouseButton.SECONDARY) ){
                showContextMenu(weekTable, e);
            }
        });


        searchWeek();
        searchMonth();
        searchSeason();
        searchYear();
    }

    private void showContextMenu(TableView table, MouseEvent e) {
        SixPlan sixPlan = (SixPlan)table.getSelectionModel().getSelectedItem();
        if(null!=sixPlan){
            List<MenuItem> itemArrayList = new ArrayList<>();

            if(Constant.PLAN_TYPE_WEEK.equals(sixPlan.getPlanType())){
                MenuItem menu6 = new MenuItem("填写完成度");
                menu6.setOnAction(p -> {
                    processPlan(sixPlan);
                });
                itemArrayList.add(menu6);
            }

            MenuItem menu1 = new MenuItem("修改任务");
            MenuItem menu4 = new MenuItem("终止任务");
            menu1.setOnAction(p -> {
                modifyPlan(sixPlan);
            });
            menu4.setOnAction(p -> {
                confirmStopPlan(sixPlan);
            });
            itemArrayList.add(menu1);
            itemArrayList.add(menu4);

            if(!Constant.PLAN_TYPE_WEEK.equals(sixPlan.getPlanType())){
                MenuItem menu2 = new MenuItem("创建子任务");
                menu2.setOnAction(p -> {
                    addSubPlan(sixPlan);
                });
                MenuItem menu3 = new MenuItem("查看子任务");
                menu3.setOnAction(p -> {
                    viewSubPlan(sixPlan);
                });
                itemArrayList.add(menu2);
                itemArrayList.add(menu3);
            }

            if(!Constant.PLAN_TYPE_YEAR.equals(sixPlan.getPlanType())){
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

    private void processPlan(SixPlan sixPlan) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label1 = new Label("任务："+sixPlan.getPlanName());
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
            if( StrUtil.isNotPositiveIntegralNumber(text) ){
                label3.setText("请输入数字");
                textField.requestFocus();
                return;
            }else{
                sixPlan.setPlanPer(Integer.valueOf(text));
                Dao.updateById(sixPlan);
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
        pane.getChildren().addAll(label1,label2,label3,textField,koBtn,okBtn);

        Scene scene = new Scene(pane, 300, 180);
        stage.setScene(scene);
        stage.setTitle("修改任务进度");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void modifyPlan(SixPlan sixPlan) {
        thePlan = sixPlan;
        parentId= null;
        nameField.setText(sixPlan.getPlanName());
        timeField.setText(sixPlan.getPlanTime().toString());
        typeCombo.getItems().stream()
                 .filter(type -> ((PlanType)type).getTypeValue().equals(
                         sixPlan.getPlanType()))
                 .forEach(type -> {
                     typeCombo.getSelectionModel().select(type);
                 });
        datePicker.setValue(LocalDate.of(sixPlan.getPlanYear(),
                                         sixPlan.getPlanMonth(), 1));
        contentArea.setText(sixPlan.getPlanContent());
        taskTitleLabel.setText("修改任务："+sixPlan.getPlanName());
        tabPane.getSelectionModel().select(taskTab);
    }

    private void confirmStopPlan(SixPlan sixPlan) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Label label1 = new Label("确定终止任务："+sixPlan.getPlanName());
        label1.setFont(Font.font(20));
        label1.setTextFill(Color.RED);

        Button okBtn = new Button(" 确定 ");
        Button koBtn = new Button(" 取消 ");

        okBtn.setOnAction(e -> {
            stopPlan(sixPlan);
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
        pane.getChildren().addAll(label1,koBtn,okBtn);

        Scene scene = new Scene(pane, 300, 180);
        stage.setScene(scene);
        stage.setTitle("确认");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void stopPlan(SixPlan sixPlan) {
        Integer id = sixPlan.getId();
        SixPlan plan = new SixPlan();
        plan.setParentId(id);
        List<SixPlan> sixPlanList = Dao.selectBySixPlan(plan);
        sixPlanList.stream().forEach(aPlan->{
            stopPlan(aPlan);
        });
        sixPlan.setPlanStatus(Constant.PLAN_STATUS_STOP);
        Dao.updateById(sixPlan);
    }

    private void addSubPlan(SixPlan sixPlan) {
        thePlan = null;
        parentId= sixPlan.getId();
        nameField.setText(sixPlan.getPlanName());
        timeField.setText(sixPlan.getPlanTime().toString());
        typeCombo.getItems().stream()
                 .filter(type -> ((PlanType)type).getTypeValue().equals(
                         sixPlan.getPlanType()-100))
                 .forEach(type -> {
                     typeCombo.getSelectionModel().select(type);
                 });
        datePicker.setValue(LocalDate.of(sixPlan.getPlanYear(),
                                         sixPlan.getPlanMonth(), 1));
        contentArea.setText(sixPlan.getPlanContent());
        taskTitleLabel.setText("添加子任务："+sixPlan.getPlanName());
        tabPane.getSelectionModel().select(taskTab);
    }

    private void viewSubPlan(SixPlan sixPlan) {
        SixPlan plan = new SixPlan();
        plan.setParentId(sixPlan.getId());
        List<SixPlan> sixPlanList = Dao.selectBySixPlan(plan);
        tabPane.getSelectionModel().selectNext();
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if(selectTab == seasonTab){
            source = seasonTable;
        }else if(selectTab == monthTab){
            source = monthTable;
        }else if(selectTab == weekTab){
            source = weekTable;
        }
        source.getItems().clear();
        source.getItems().addAll(sixPlanList);
    }

    private void viewSuperPlan(SixPlan sixPlan) {
        SixPlan superPlan = Dao.queryById(sixPlan.getParentId());
        tabPane.getSelectionModel().selectPrevious();
        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
        TableView source = new TableView();
        if(selectTab == yearTab){
            source = yearTable;
        }else if(selectTab == seasonTab){
            source = seasonTable;
        }else if(selectTab == monthTab){
            source = monthTable;
        }
        source.getItems().clear();
        source.getItems().add(superPlan);
    }

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
        seasonStatusColumn.setCellValueFactory(new PropertyValueFactory(
                "planStatusName"));
        monthStatusColumn.setCellValueFactory(new PropertyValueFactory(
                "planStatusName"));
        weekStatusColumn.setCellValueFactory(new PropertyValueFactory(
                "planStatusName"));
    }

    private void initTab() {
        tabPane.selectionModelProperty().addListener(e->{changeTab();});
        tabPane.getSelectionModel().select(weekTab);
        initTaskTab();
    }

    private void initTaskTab() {
        thePlan = null;
        parentId= null;
        taskTitleLabel.setText("添加新任务");
        nameField.setText("");
        timeField.setText("");
        typeCombo.getSelectionModel().selectFirst();
        datePicker.setValue(LocalDate.now());
        contentArea.setText("");
    }

    private void changeTab() {
//        Tab selectTab = tabPane.getSelectionModel().getSelectedItem();
//        if(selectTab == yearTab){
//            searchYear();
//        }else if(selectTab == seasonTab){
//            if(isNextLevel){
//
//            }else{
//                searchSeason();
//            }
//        }else if(selectTab == monthTab){
//            if(isNextLevel){
//
//            }else{
//                searchMonth();
//            }
//        }else if(selectTab == weekTab){
//            if(isNextLevel){
//
//            }else{
//                searchWeek();
//            }
//        }else if(selectTab == taskTab){
//            //
//        }
    }

    public void searchYear() {
        SixPlan sixPlan = new SixPlan();

        String year = yearYearField.getText();
        if( StrUtil.isPositiveIntegralNumber(year)){
            sixPlan.setPlanYear(Integer.valueOf(year));
        }else if(StrUtil.isNotEmpty(year)){
            return;
        }

        Integer status = ((PlanStatus)yearStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(Constant.PLAN_STATUS_ALL.equals(status) ? null : status);

        sixPlan.setPlanType(Constant.PLAN_TYPE_YEAR);
        yearData.clear();
        yearData.addAll(Dao.selectBySixPlan(sixPlan));
    }

    public void searchSeason() {
        SixPlan sixPlan = new SixPlan();

        String season = seasonSeasonField.getText();
        if( StrUtil.isPositiveIntegralNumber(season)){
            sixPlan.setPlanSeason(Integer.valueOf(season));
        }else if(StrUtil.isNotEmpty(season)){
            return;
        }

        String year = seasonYearField.getText();
        if( StrUtil.isPositiveIntegralNumber(year)){
            sixPlan.setPlanYear(Integer.valueOf(year));
        }else if(StrUtil.isNotEmpty(year)){
            return;
        }

        Integer status = ((PlanStatus)seasonStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(Constant.PLAN_STATUS_ALL.equals(status)?null:status);

        sixPlan.setPlanType(Constant.PLAN_TYPE_SEASON);
        seasonData.clear();
        seasonData.addAll(Dao.selectBySixPlan(sixPlan));
    }

    public void searchMonth() {
        SixPlan sixPlan = new SixPlan();

        String month = monthMonthField.getText();
        if( StrUtil.isPositiveIntegralNumber(month)){
            sixPlan.setPlanMonth(Integer.valueOf(month));
        }else if(StrUtil.isNotEmpty(month)){
            return;
        }

        String year = monthYearField.getText();
        if( StrUtil.isPositiveIntegralNumber(year)){
            sixPlan.setPlanYear(Integer.valueOf(year));
        }else if(StrUtil.isNotEmpty(year)){
            return;
        }else if(StrUtil.isEmpty(year)){
            String season = monthSeasonField.getText();
            if( StrUtil.isPositiveIntegralNumber(season)){
                sixPlan.setPlanSeason(Integer.valueOf(season));
            }else if(StrUtil.isNotEmpty(season)){
                return;
            }
        }

        Integer status = ((PlanStatus)monthStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(Constant.PLAN_STATUS_ALL.equals(status)?null:status);

        sixPlan.setPlanType(Constant.PLAN_TYPE_MONTH);
        monthData.clear();
        monthData.addAll(Dao.selectBySixPlan(sixPlan));
    }

    public void searchWeek() {
        SixPlan sixPlan = new SixPlan();

        String week = weekWeekField.getText();
        if( StrUtil.isPositiveIntegralNumber(week)){
            sixPlan.setPlanWeek(Integer.valueOf(week));
        }else if(StrUtil.isNotEmpty(week)){
            return;
        }

        String year = weekYearField.getText();
        if( StrUtil.isPositiveIntegralNumber(year)){
            sixPlan.setPlanYear(Integer.valueOf(year));
        }else if(StrUtil.isNotEmpty(year)){
            return;
        }else if(StrUtil.isEmpty(year)){
            String season = weekSeasonField.getText();
            if( StrUtil.isPositiveIntegralNumber(season)){
                sixPlan.setPlanSeason(Integer.valueOf(season));
            }else if(StrUtil.isNotEmpty(season)){
                return;
            }else if(StrUtil.isEmpty(season)){
                String month = weekMonthField.getText();
                if( StrUtil.isPositiveIntegralNumber(month)){
                    sixPlan.setPlanMonth(Integer.valueOf(month));
                }else if(StrUtil.isNotEmpty(month)){
                    return;
                }
            }
        }

        Integer status = ((PlanStatus)weekStatusCombo.getSelectionModel().getSelectedItem()).getStatusValue();
        sixPlan.setPlanStatus(Constant.PLAN_STATUS_ALL.equals(status)?null:status);

        sixPlan.setPlanType(Constant.PLAN_TYPE_WEEK);
        weekData.clear();
        weekData.addAll(Dao.selectBySixPlan(sixPlan));
    }

    public void okPlan() {
        String name = nameField.getText();
        if( StrUtil.isEmpty(name) ){
            return;
        }

        String timeStr = timeField.getText();
        if( StrUtil.isNotPositiveIntegralNumber(timeStr) ){
            return;
        }

        LocalDate localDate = datePicker.getValue();
        if( null==localDate ){
            return;
        }

        String content = contentArea.getText();

        Integer type = ((PlanType)typeCombo.getSelectionModel()
                                           .getSelectedItem()).getTypeValue();

        boolean isInsert = false;
        if( null == thePlan ){
            isInsert = true;
            thePlan = new SixPlan();
            if( null!=parentId ){
                thePlan.setParentId(parentId);
            }else{
                thePlan.setParentId(0);
            }
            thePlan.setPlanStatus(Constant.PLAN_STATUS_ING);
            thePlan.setSourceId(0);
            thePlan.setPlanPer(0);
        }
        thePlan.setPlanName(name);
        thePlan.setPlanTime(Integer.valueOf(timeStr));
        thePlan.setPlanType(type);
        thePlan.setPlanYear(localDate.getYear());
        thePlan.setPlanMonth(localDate.getMonthValue());
        thePlan.setPlanSeason((localDate.getMonthValue() + 2)/3);
        thePlan.setPlanWeek(localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        thePlan.setPlanContent(content);
        if(isInsert){
            if(thePlan.getParentId()==0 && type != Constant.PLAN_TYPE_YEAR){
                thePlan.setId(0);
                for( int i=Constant.PLAN_TYPE_YEAR;i>=type; i-=100 ){
                    thePlan.setParentId(thePlan.getId());
                    thePlan.setId(null);
                    Dao.insert(thePlan);
                }
            }else{
                Dao.insert(thePlan);
            }
            tipLabel.setText("添加任务成功：" + name);
            initTaskTab();
        }else{
            Dao.updateById(thePlan);
            tipLabel.setText("更新任务成功：" + name);
        }
    }

    public void resetPlan() {
        initTaskTab();
    }
}
