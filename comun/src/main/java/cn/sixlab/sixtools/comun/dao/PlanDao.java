/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.dao;

import cn.sixlab.sixtools.comun.mapper.SeisPlanMapper;
import cn.sixlab.sixtools.comun.util.Du;
import org.apache.ibatis.session.SqlSession;
import cn.sixlab.sixtools.comun.bean.SeisPlan;

import java.util.List;

/**
 * SeisPlan Dao操作
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 11:36
 */
public class PlanDao extends Du {

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

    @Override
    public SeisPlanMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisPlanMapper.class);
    }
}
