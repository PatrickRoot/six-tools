/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixplan.dao;

import org.eu.sixlab.sixtools.common.beans.SixPlan;
import org.eu.sixlab.sixtools.common.dao.SixPlanMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * //TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/3 19:49
 */
public class Dao {

    public static void insert(SixPlan sixPlan) {
        SixPlanMapper sixPlanMapper = SixDaoUtil.getMapper(SixPlanMapper.class);
        sixPlanMapper.insertSelective(sixPlan);
        SixDaoUtil.close();
    }

    public static void updateById(SixPlan sixPlan) {
        SixPlanMapper sixPlanMapper = SixDaoUtil.getMapper(SixPlanMapper.class);
        sixPlanMapper.updateByPrimaryKeySelective(sixPlan);
        SixDaoUtil.close();
    }
    
    public static List<SixPlan> selectBySixPlan(SixPlan sixPlan) {
        SixPlanMapper sixPlanMapper = SixDaoUtil.getMapper(SixPlanMapper.class);

        List<SixPlan> sixPlanList = sixPlanMapper.selectBySixPlan(sixPlan);

        SixDaoUtil.close();
        return sixPlanList;
    }
}
