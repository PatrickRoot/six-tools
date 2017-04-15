/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.bean;

import cn.sixlab.sixtools.dao.util.C;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/4/3 15:38
 */
public class PlanStatus {

    private String statusValue;
    private String statusName;

    public PlanStatus(){
        super();
    }

    public PlanStatus(String statusValue, String statusName){
        this.statusName = statusName;
        this.statusValue = statusValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return statusName;
    }

    public static List<PlanStatus> allStatus(){
        List<PlanStatus> statusList = new ArrayList<>();
        statusList.add(new PlanStatus(C.PLAN_STATUS_ALL, "所有"));
        statusList.add(new PlanStatus(C.PLAN_STATUS_ING,"进行中"));
        statusList.add(new PlanStatus(C.PLAN_STATUS_ED, "完成"));
        statusList.add(new PlanStatus(C.PLAN_STATUS_STOP, "未完成"));
        return statusList;
    }
}
