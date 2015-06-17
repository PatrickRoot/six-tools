/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.dao;

import cn.sixlab.sixtools.comun.bean.SeisPunto;
import cn.sixlab.sixtools.comun.mapper.SeisPuntoMapper;
import cn.sixlab.sixtools.comun.util.Du;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 22:24
 */
public class PuntoDao extends Du {

    public void insert(SeisPunto seisPunto) {
        try (SqlSession ss = factory.openSession()) {
            SeisPuntoMapper seisPuntoMapper = getMapper(ss);
            seisPuntoMapper.insert(seisPunto);
            ss.commit();
        }
    }

    public List<SeisPunto> queryAll() {
        try (SqlSession ss = factory.openSession()) {
            SeisPuntoMapper seisPuntoMapper = getMapper(ss);
            List<SeisPunto> sixPlanList = seisPuntoMapper.queryAll();

            return sixPlanList;
        }
    }

    public Double queryPunto() {
        try (SqlSession ss = factory.openSession()) {
            SeisPuntoMapper seisPuntoMapper = getMapper(ss);
            return seisPuntoMapper.queryPunto();
        }
    }

    @Override
    public SeisPuntoMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisPuntoMapper.class);
    }
}
