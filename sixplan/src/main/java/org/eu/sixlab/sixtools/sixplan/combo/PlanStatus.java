/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan.combo;

import org.eu.sixlab.sixtools.common.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 15:38
 */
public class PlanStatus {

    private Integer statusValue;
    private String statusName;

    public PlanStatus(){
        super();
    }

    public PlanStatus(Integer statusValue, String statusName){
        this.statusName = statusName;
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return statusName;
    }

    public static List<PlanStatus> allStatus(){
        List<PlanStatus> statusList = new ArrayList<>();
        statusList.add(new PlanStatus(Constant.PLAN_STATUS_ALL, "所有"));
        statusList.add(new PlanStatus(Constant.PLAN_STATUS_ING,"进行中"));
        statusList.add(new PlanStatus(Constant.PLAN_STATUS_ED, "完成"));
//        statusList.add(new PlanStatus(Constant.PLAN_STATUS_TEMP, "暂存"));
        statusList.add(new PlanStatus(Constant.PLAN_STATUS_STOP, "未完成"));
        return statusList;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(Integer statusValue) {
        this.statusValue = statusValue;
    }
}
