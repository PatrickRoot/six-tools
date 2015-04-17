/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

import org.eu.sixlab.sixtools.comun.bean.SeisBandeja;
import org.eu.sixlab.sixtools.comun.bean.SeisTools;
import org.eu.sixlab.sixtools.comun.mapper.SeisToolsMapper;
import org.eu.sixlab.sixtools.comun.util.Du;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 19:24
 */
public class ToolsDao {

    private List<SeisBandeja> allTools;

    public List<SeisTools> getEnableTools() {
        try{
            SeisToolsMapper seisToolsMapper = Du.getMapper(SeisToolsMapper.class);
            List<SeisTools> seisToolsList = seisToolsMapper.getEnableTools();

            return seisToolsList;
        }finally {
            Du.close();
        }
    }

    public List<SeisTools> getAllTools() {
        try{
            SeisToolsMapper seisToolsMapper = Du.getMapper(SeisToolsMapper.class);
            List<SeisTools> seisToolsList = seisToolsMapper.getEnableTools();

            return seisToolsList;
        }finally {
            Du.close();
        }
    }
}
