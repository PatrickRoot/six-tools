/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

import org.apache.ibatis.session.SqlSession;
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
public class PlanDao extends Du{

    public void insert(SeisPlan sixPlan) {
        try(SqlSession ss = factory.openSession()){
            SeisPlanMapper seisPlanMapper = getMapper(ss);
            seisPlanMapper.insert(sixPlan);
            ss.commit();
        }
    }

    public void updateById(SeisPlan sixPlan) {
        try(SqlSession ss = factory.openSession()){
            SeisPlanMapper seisPlanMapper = getMapper(ss);
            seisPlanMapper.update(sixPlan);
            ss.commit();
        }
    }

    public List<SeisPlan> selectByPlan(SeisPlan sixPlan) {
        try(SqlSession ss = factory.openSession()){
            SeisPlanMapper seisPlanMapper = getMapper(ss);
            List<SeisPlan> sixPlanList = seisPlanMapper.selectByPlan(sixPlan);

            return sixPlanList;
        }
    }

    public SeisPlan queryById(Integer parentId) {
        try(SqlSession ss = factory.openSession()){
            SeisPlanMapper seisPlanMapper = getMapper(ss);
            SeisPlan sixPlan = seisPlanMapper.selectById(parentId);
            return sixPlan;
        }
    }

    public Integer queryTimeLeft(Integer id) {
        try(SqlSession ss = factory.openSession()){
            SeisPlanMapper seisPlanMapper = getMapper(ss);
            return seisPlanMapper.queryTimeLeft(id);
        }
    }

    @Override
    public SeisPlanMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisPlanMapper.class);
    }
}
