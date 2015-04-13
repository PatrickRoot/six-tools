/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

import org.eu.sixlab.sixtools.comun.bean.SeisPlan;
import org.eu.sixlab.sixtools.comun.mapper.SeisPlanMapper;
import org.eu.sixlab.sixtools.comun.util.Du;

import java.util.List;

/**
 * SeisPlan Dao操作
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 11:36
 */
public class PlanDao {
    public void insert(SeisPlan sixPlan) {
        try{
            SeisPlanMapper seisPlanMapper = Du.getMapper(SeisPlanMapper.class);
            seisPlanMapper.insert(sixPlan);

        }finally {
            Du.close();
        }
    }

    public void updateById(SeisPlan sixPlan) {
        try{
            SeisPlanMapper seisPlanMapper = Du.getMapper(SeisPlanMapper.class);
            seisPlanMapper.update(sixPlan);

        }finally {
            Du.close();
        }
    }

    public List<SeisPlan> selectByPlan(SeisPlan sixPlan) {
        try{
            SeisPlanMapper seisPlanMapper = Du.getMapper(SeisPlanMapper.class);
            List<SeisPlan> sixPlanList = seisPlanMapper.selectByPlan(sixPlan);

            return sixPlanList;
        }finally {
            Du.close();
        }
    }

    public SeisPlan queryById(Integer parentId) {
        try{
            SeisPlanMapper seisPlanMapper = Du.getMapper(SeisPlanMapper.class);
            SeisPlan sixPlan = seisPlanMapper.selectById(parentId);

            return sixPlan;
        }finally {
            Du.close();
        }
    }
}
