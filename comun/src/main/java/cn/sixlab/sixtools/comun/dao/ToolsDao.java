/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.dao;

import cn.sixlab.sixtools.comun.bean.SeisTools;
import cn.sixlab.sixtools.comun.util.Du;
import org.apache.ibatis.session.SqlSession;
import cn.sixlab.sixtools.comun.bean.SeisBandeja;
import cn.sixlab.sixtools.comun.mapper.SeisToolsMapper;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 19:24
 */
public class ToolsDao extends Du {

    private List<SeisBandeja> allTools;

    public List<SeisTools> getEnableTools() {
        try(SqlSession ss = factory.openSession()){
            SeisToolsMapper seisToolsMapper = getMapper(ss);
            List<SeisTools> seisToolsList = seisToolsMapper.getEnableTools();
            return seisToolsList;
        }
    }

    public List<SeisTools> getAllTools() {
        try(SqlSession ss = factory.openSession()){
            SeisToolsMapper seisToolsMapper = getMapper(ss);
            List<SeisTools> seisToolsList = seisToolsMapper.getEnableTools();
            return seisToolsList;
        }
    }

    @Override
    public SeisToolsMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisToolsMapper.class);
    }
}
