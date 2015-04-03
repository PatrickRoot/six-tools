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
import org.eu.sixlab.sixtools.common.util.SixToolsConstants;
import org.eu.sixlab.sixtools.sixplan.combo.PlanType;
import org.eu.sixlab.sixtools.sixplan.dao.SixPlanDao;
import org.eu.sixlab.sixutil.StringUtil;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Six Plan 添加的Controller
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 15:14
 */
public class SixPlanAddController implements Initializable{

    public DatePicker datePicker;
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

    private ObservableList<PlanType> typeData = FXCollections.observableArrayList();

    private SixPlan oldPlan;
    private Integer oldId;
    private Integer parentId;
    private Integer sourceId;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PlanType> typeList = PlanType.allTypes(false);
        typeData.clear();
        typeData.addAll(typeList);
        loopCombo.setItems(typeData);

        if(null == oldPlan){
            loopCombo.getSelectionModel().selectFirst();
            loopCombo.setDisable(true);
            tipsLabel.setText("添加任务，任务名、计划用时、周期数三项为必填项。");
        }else{
            oldId = oldPlan.getId();
            parentId = oldPlan.getParentId();
            sourceId = oldPlan.getSourceId();

            Integer type = oldPlan.getPlanType();
            int i = -1;
            for (PlanType s:typeList){
                i++;
                if(type.equals(s.getTypeValue())){
                    break;
                }
            }
            loopCombo.getSelectionModel().select(i);
            loopCombo.setDisable(true);

//            dateField.setText(oldPlan.getPlanDate().toString());
//            dateField.setDisable(true);

            nameField.setText(oldPlan.getPlanName());
            timeField.setText(oldPlan.getPlanTime().toString());
            contentArea.setText(oldPlan.getPlanContent());

            tipsLabel.setText("修改任务：" + oldPlan.getPlanName());
        }

        loopCombo.setCellFactory(p -> {
            return new ListCell<PlanType>() {
                @Override
                protected void updateItem(PlanType planType, boolean bln) {
                    super.updateItem(planType, bln);
                    if (planType != null) {
                        setText(planType.getTypeName());
                    } else {
                        setText(null);
                    }
                }
            };
        });
    }

    public void close() {
        tipsLabel.getScene().getWindow().hide();
    }

    public void addPlan() {
        String planName = nameField.getText();
        if( StringUtil.isEmpty(planName)){
            tipsLabel.setText("计划名 为必填项！");
            nameField.requestFocus();
            return;
        }

        String planTime = timeField.getText();
        if(StringUtil.isEmpty(planTime)){
            tipsLabel.setText("计划用时 为必填项！");
            timeField.requestFocus();
            return;
        }
        if(StringUtil.isNotPositiveIntegralNumber(planTime)){
            tipsLabel.setText("计划用时 必需为正整数！");
            timeField.requestFocus();
            return;
        }

        LocalDate planDate = datePicker.getValue();
        if( null== planDate ){
            tipsLabel.setText("周期数 为必填项！");
            datePicker.requestFocus();
            return;
        }

        Integer planType = Integer.valueOf(((PlanType)(loopCombo.getValue())).getTypeValue());

        SixPlan sixPlan = new SixPlan();
        sixPlan.setPlanName(planName);
        sixPlan.setPlanTime(Integer.valueOf(planTime));
        sixPlan.setPlanType(planType);
        sixPlan.setPlanDate(SixPlanUtil.date2Str(planDate));
        sixPlan.setPlanContent(contentArea.getText());

        if(null==oldId){
            sixPlan.setPlanStatus(SixToolsConstants.PLAN_STATUS_ING);
            SixPlanDao.insert(sixPlan);
            tipsLabel.setText("添加任务成功："+planName+"。继续添加？");
        }else{
            sixPlan.setId(oldId);
            SixPlanDao.updateById(sixPlan);
            tipsLabel.setText("更新任务成功："+planName);
        }
    }

    public void addCloseBtn() {
        addPlan();
        close();
    }
}


