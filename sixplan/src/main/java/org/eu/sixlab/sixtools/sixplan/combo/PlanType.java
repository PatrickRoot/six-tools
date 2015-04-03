/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan.combo;

import org.eu.sixlab.sixtools.common.util.SixToolsConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * Six Plan 中plan type 的类
 * @author 六楼的雨/loki
 * @date 2015/4/3 12:43
 */
public class PlanType {

    private String typeValue;
    private String typeName;

    public PlanType() {
        super();
    }

    public PlanType(String typeValue, String typeName) {
        this.typeValue = typeValue;
        this.typeName = typeName;
    }

    public static List<PlanType> allTypes(Boolean hasAll){
        List<PlanType> typeList = new ArrayList<>();
        if(hasAll){
            typeList.add(new PlanType(SixToolsConstants.PLAN_TYPE_ALL,"所有"));
        }
        typeList.add(new PlanType(SixToolsConstants.PLAN_TYPE_YEAR, "年"));
        typeList.add(new PlanType(SixToolsConstants.PLAN_TYPE_SEASON, "季度"));
        typeList.add(new PlanType(SixToolsConstants.PLAN_TYPE_MONTH, "月"));
        typeList.add(new PlanType(SixToolsConstants.PLAN_TYPE_WEEK, "周"));
        return typeList;
    }

    @Override
    public String toString() {
        return typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
