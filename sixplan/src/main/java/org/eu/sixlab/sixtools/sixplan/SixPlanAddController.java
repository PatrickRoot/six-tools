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
import javafx.scene.control.*;
import org.eu.sixlab.sixtools.common.beans.SixPlan;
import org.eu.sixlab.sixtools.common.util.Constant;
import org.eu.sixlab.sixtools.sixplan.combo.PlanType;
import org.eu.sixlab.sixtools.sixplan.dao.Dao;
import org.eu.sixlab.sixutil.StrUtil;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Six Plan 添加的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 15:14
 */
public class SixPlanAddController implements Initializable {

    public static SixPlan oldPlan;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Label tipsLabel;
    @FXML
    private TextArea contentArea;
    @FXML
    private ComboBox loopCombo;
    @FXML
    private TextField timeField;
    @FXML
    private TextField nameField;
    private ObservableList<PlanType> typeData = FXCollections
            .observableArrayList();
    private Integer oldId;
    private Integer parentId;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTypeCombo();
        initValue();
    }

    private void initValue() {
        if( null == oldPlan ){
            loopCombo.getSelectionModel().selectFirst();
            loopCombo.setDisable(true);
            tipsLabel.setText("添加任务，任务名、计划用时、周期数三项为必填项。");
        }else{
            if( null == oldPlan.getId() ){
                parentId = oldPlan.getParentId();
                tipsLabel.setText("修改任务：" + oldPlan.getPlanName());
            }else{
                oldId = oldPlan.getId();
                tipsLabel.setText("修改任务：" + oldPlan.getPlanName());
            }

            nameField.setText(oldPlan.getPlanName());
            timeField.setText(oldPlan.getPlanTime().toString());
            contentArea.setText(oldPlan.getPlanContent());
            Integer year = oldPlan.getPlanYear();
            Integer month = oldPlan.getPlanMonth();
            if( null == year || year.equals(0) ){
                year = LocalDate.now().getYear();
            }
            if( null == month || month.equals(0) ){
                month = 1;
            }
            datePicker.setValue(LocalDate.of(year, month, 1));
        }
    }

    private void initTypeCombo() {

        List<PlanType> typeList = PlanType.allTypes();
        typeData.clear();
        typeData.addAll(typeList);
        loopCombo.setItems(typeData);

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

        if( null != oldPlan ){
            Integer type = oldPlan.getPlanType();
            int i = 0;
            for( PlanType s : typeList ){
                if( type.equals(s.getTypeValue()) ){
                    break;
                }
                i++;
            }
            loopCombo.getSelectionModel().select(i);
            loopCombo.setDisable(true);
        }
    }

    public void addCloseBtn() {
        SixPlan sixPlan = new SixPlan();
        //计划名字
        String planName = nameField.getText();
        if( StrUtil.isEmpty(planName) ){
            tipsLabel.setText("计划名 为必填项！");
            nameField.requestFocus();
            return;
        }
        sixPlan.setPlanName(planName);

        //计划用时
        String planTime = timeField.getText();
        if( StrUtil.isEmpty(planTime) ){
            tipsLabel.setText("计划用时 为必填项！");
            timeField.requestFocus();
            return;
        }
        if( StrUtil.isNotPositiveIntegralNumber(planTime) ){
            tipsLabel.setText("计划用时 必需为正整数！");
            timeField.requestFocus();
            return;
        }
        sixPlan.setPlanTime(Integer.valueOf(planTime));

        //计划周期类型
        Integer planType = Integer.valueOf(
                ((PlanType)(loopCombo.getValue())).getTypeValue());
        sixPlan.setPlanType(planType);

        //计划周期数
        LocalDate planDate = datePicker.getValue();
        if( null == planDate ){
            tipsLabel.setText("周期数 为必填项！");
            datePicker.requestFocus();
            return;
        }else{
            sixPlan.setPlanYear(planDate.getYear());

            if( Constant.PLAN_TYPE_YEAR > planType ){
                sixPlan.setPlanSeason((planDate.getMonthValue() - 1) / 3 + 1);
            }else{
                sixPlan.setPlanSeason(0);
            }

            if( Constant.PLAN_TYPE_SEASON > planType ){
                sixPlan.setPlanMonth(planDate.getMonthValue());
            }else{
                sixPlan.setPlanMonth(0);
            }

            if( Constant.PLAN_TYPE_MONTH > planType ){
                sixPlan.setPlanWeek(planDate.get(
                        IsoFields.WEEK_OF_WEEK_BASED_YEAR));
            }else{
                sixPlan.setPlanWeek(0);
            }
        }

        sixPlan.setPlanContent(contentArea.getText());
        sixPlan.setPlanPer(0);
        sixPlan.setSourceId(0);

        if( null == oldPlan ){
            sixPlan.setParentId(0);
            sixPlan.setPlanStatus(Constant.PLAN_STATUS_ING);
            Dao.insert(sixPlan);
            tipsLabel.setText("添加任务成功：" + planName + "。");
        }else if( null == oldId ){
            sixPlan.setParentId(parentId);
            sixPlan.setPlanStatus(Constant.PLAN_STATUS_ING);
            Dao.insert(sixPlan);
            tipsLabel.setText("添加子任务成功：" + planName + "。");
        }else{
            oldPlan.setPlanName(sixPlan.getPlanName());
            oldPlan.setPlanTime(sixPlan.getPlanTime());
            oldPlan.setPlanType(sixPlan.getPlanType());
            oldPlan.setPlanYear(sixPlan.getPlanYear());
            oldPlan.setPlanSeason(sixPlan.getPlanSeason());
            oldPlan.setPlanMonth(sixPlan.getPlanMonth());
            oldPlan.setPlanWeek(sixPlan.getPlanWeek());
            oldPlan.setPlanContent(sixPlan.getPlanContent());
            sixPlan.setId(oldId);
            Dao.updateById(sixPlan);
            tipsLabel.setText("更新任务成功：" + planName);
        }
        close();
    }

    public void close() {
        oldPlan = null;
        tipsLabel.getScene().getWindow().hide();
    }
}


