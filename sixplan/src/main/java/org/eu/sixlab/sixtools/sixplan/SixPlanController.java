/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.eu.sixlab.sixtools.common.beans.SixPlan;
import org.eu.sixlab.sixtools.common.util.Constant;
import org.eu.sixlab.sixtools.sixplan.combo.PlanStatus;
import org.eu.sixlab.sixtools.sixplan.combo.PlanType;
import org.eu.sixlab.sixtools.sixplan.dao.Dao;
import org.eu.sixlab.sixutil.ObjUtil;
import org.eu.sixlab.sixutil.StrUtil;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Six Plan 管理查看的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 14:58
 */
public class SixPlanController implements Initializable {

    @FXML
    public ComboBox statusCombo;
    @FXML
    public ComboBox loopCombo;
    @FXML
    public TextField yearText;
    @FXML
    public TextField seasonText;
    @FXML
    public TextField monthText;
    @FXML
    public TextField weekText;
    @FXML
    public Label tipsUp;
    @FXML
    public Label tipsDown;
    @FXML
    public TableView taskTable;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn nameColumn;
    @FXML
    public TableColumn ratioColumn;
    @FXML
    public TableColumn timeColumn;
    @FXML
    public TableColumn parentColumn;
    @FXML
    public TableColumn statusColumn;

    private static final ObservableList<SixPlan> data = FXCollections
            .observableArrayList();

    private static final ObservableList<PlanStatus> statusData = FXCollections
            .observableArrayList();

    private static final ObservableList<PlanType> typeData = FXCollections
            .observableArrayList();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initStatusCombo();
        initLoopCombo();
        initField();
        initTable();
        search();
    }

    private void initTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("planName"));
        ratioColumn.setCellValueFactory(new PropertyValueFactory("planPer"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("planTime"));
        parentColumn.setCellValueFactory(new PropertyValueFactory("parentId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory(
                "planStatusValue"));

        taskTable.setItems(data);
        taskTable.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if( e.getButton().equals(MouseButton.PRIMARY) && e
                    .getClickCount() == 2 ){
                SixPlan sixPlan = (SixPlan)taskTable.getSelectionModel()
                                                    .getSelectedItem();
                processPlan(sixPlan);
            }else if( e.getButton().equals(MouseButton.SECONDARY) ){
                SixPlan sixPlan = (SixPlan)taskTable.getSelectionModel()
                                                    .getSelectedItem();
                try{
                    addSubPlan(sixPlan);
                }catch(IOException e1){
                    e1.printStackTrace();
                }
            }
        });
    }

    private void addSubPlan(SixPlan oldPlan) throws IOException {
        if(Constant.PLAN_TYPE_WEEK.equals(oldPlan.getPlanType())){
            tipsDown.setText("周任务无法建立子任务");
            return;
        }
        SixPlan sixPlan = new SixPlan();
        ObjUtil.copyBean(sixPlan, oldPlan);

        sixPlan.setParentId(sixPlan.getId());
        sixPlan.setId(null);
        sixPlan.setPlanStatus(null);
        sixPlan.setPlanType(sixPlan.getPlanType()-100);
        sixPlan.setPlanPer(0);

        SixPlanAddController.oldPlan = sixPlan;
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Parent parent = FXMLLoader.load(getClass().getResource("add.fxml"));

        Scene scene = new Scene(parent, 320, 230);

        stage.setScene(scene);
        stage.setTitle("添加子任务");
        stage.show();
    }

    private void processPlan(SixPlan sixPlan) {
        Stage stage = new Stage();
        Label label = new Label("请输入任务完成度：" + sixPlan.getPlanName() + "");
        label.setFont(Font.font(14));
        Label tipLa = new Label();
        tipLa.setTextFill(Color.RED);
        TextField textField = new TextField();
        textField.setPromptText("请输入数字");

        Button okBtn = new Button("确定");
        Button koBtn = new Button("取消");
        okBtn.setOnAction(e -> {
            String text = textField.getText();
            if( StrUtil.isNotPositiveIntegralNumber(text) ){
                tipLa.setText("请输入数字");
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

        label.setLayoutY(25);
        label.setLayoutX(30);
        textField.setLayoutY(65);
        textField.setLayoutX(90);
        tipLa.setLayoutY(95);
        tipLa.setLayoutX(125);
        koBtn.setLayoutY(120);
        koBtn.setLayoutX(110);
        okBtn.setLayoutY(120);
        okBtn.setLayoutX(170);

        Group parent = new Group();
        parent.getChildren().addAll(label,tipLa,textField,okBtn,koBtn);
        Scene scene = new Scene(parent, 300, 160);
        stage.setScene(scene);
        stage.setTitle("修改任务进度");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    private void initStatusCombo() {
        List<PlanStatus> statusList = PlanStatus.allStatus();
        statusData.clear();
        statusData.addAll(statusList);
        statusCombo.setItems(statusData);
        statusCombo.getSelectionModel().select(1);

        statusCombo.setCellFactory(p -> {
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
        loopCombo.setOnAction(e->{search();});
    }

    private void initLoopCombo() {

        List<PlanType> typeList = PlanType.allTypes(false);
        typeData.clear();
        typeData.addAll(typeList);
        loopCombo.setItems(typeData);
        loopCombo.getSelectionModel().selectLast();

        loopCombo.setCellFactory(p -> {
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

        loopCombo.setOnAction(e->{search();});
    }

    private void initField() {
        LocalDate localDate = LocalDate.now();

        Integer year = localDate.getYear();
        Integer month = localDate.getMonthValue();
        Integer season = (month - 1) / 3 + 1;
        Integer week = localDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

        yearText.setText(year.toString());
        seasonText.setText(season.toString());
        monthText.setText(month.toString());
        weekText.setText(week.toString());

        yearText.setOnAction(e->{search();});
        seasonText.setOnAction(e->{search();});
        monthText.setOnAction(e->{search();});
        weekText.setOnAction(e->{search();});

        yearText.textProperty().addListener(e->{search();});
        seasonText.textProperty().addListener(e -> {
            search();
        });
        monthText.textProperty().addListener(e -> {
            search();
        });
        weekText.textProperty().addListener(e -> {
            search();
        });
    }

    public void addPlan() throws IOException {
        Stage stage = new Stage();

        Parent parent = FXMLLoader.load(getClass().getResource("add.fxml"));

        Scene scene = new Scene(parent, 320, 230);

        stage.setScene(scene);
        stage.setTitle("Add Plan");
        stage.show();
    }

    public void stopPlan() {
        ObservableList<SixPlan> selectedItems = taskTable.getSelectionModel()
                .getSelectedItems();
        for( SixPlan selectedItem : selectedItems ){
            selectedItem.setPlanStatus(Constant.PLAN_STATUS_STOP);
            Dao.updateById(selectedItem);
        }
    }

    public void search() {
        SixPlan sixPlan = new SixPlan();
        String tips = "";
        Integer loop = ((PlanType)loopCombo.getValue()).getTypeValue();
        if( Constant.PLAN_TYPE_ALL.equals(loop) ){
            sixPlan.setPlanType(null);
        }else{
            sixPlan.setPlanType(loop);

            String yearValue = yearText.getText();
            if( loop < Constant.PLAN_STATUS_ALL ){
                if( StrUtil.isEmpty(yearValue) ){
                    tipsDown.setText("年份 为必填项！");
                    yearText.requestFocus();
                    return;
                }
                tips+=(yearValue+" 年 ");
                sixPlan.setPlanYear(Integer.valueOf(yearValue));
            }

            String seasonValue = seasonText.getText();
            if( loop < Constant.PLAN_TYPE_YEAR ){
                if( StrUtil.isEmpty(seasonValue) ){
                    tipsDown.setText("季度 为必填项！");
                    seasonText.requestFocus();
                    return;
                }
                tips+=(seasonValue+" 季度 ");
                sixPlan.setPlanSeason(Integer.valueOf(seasonValue));
            }

            String monthValue = monthText.getText();
            if( loop < Constant.PLAN_TYPE_SEASON ){
                if( StrUtil.isEmpty(monthValue) ){
                    tipsDown.setText("月份 为必填项！");
                    monthText.requestFocus();
                    return;
                }
                tips+=(monthValue+" 月 ");
                sixPlan.setPlanMonth(Integer.valueOf(monthValue));
            }

            String weekValue = weekText.getText();
            if( loop < Constant.PLAN_TYPE_MONTH ){
                if( StrUtil.isEmpty(weekValue) ){
                    tipsDown.setText("周 为必填项！");
                    weekText.requestFocus();
                    return;
                }
                tips+=(weekValue+" 周 ");
                sixPlan.setPlanWeek(Integer.valueOf(weekValue));
            }
        }

        Integer status = ((PlanStatus)statusCombo.getValue()).getStatusValue();
        status = (Constant.PLAN_STATUS_ALL.equals(status) ? null : status);
        sixPlan.setPlanStatus(status);

        List<SixPlan> sixPlanList = Dao.selectBySixPlan(sixPlan);

        if(Constant.PLAN_STATUS_ING.equals(status)){
            tips+="进行中";
        }else if(Constant.PLAN_STATUS_ED.equals(status)){
            tips+="完成";
        }else if(Constant.PLAN_STATUS_STOP.equals(status)){
            tips+="未完成";
        }else{
            tips+="所有";
        }
        tips+=(" 的任务量为："+sixPlanList.size());
        tipsUp.setText(tips);
        data.clear();
        data.addAll(sixPlanList);
    }

    public void modifyPlan() throws IOException {
        SixPlan sixPlan = (SixPlan)taskTable.getSelectionModel().getSelectedItem();
        SixPlanAddController.oldPlan = sixPlan;
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Parent parent = FXMLLoader.load(getClass().getResource("add.fxml"));

        Scene scene = new Scene(parent, 320, 230);

        stage.setScene(scene);
        stage.setTitle("修改任务");
        stage.show();
    }
}
