/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

import org.apache.ibatis.session.SqlSession;
import org.eu.sixlab.sixtools.comun.bean.SeisBandeja;
import org.eu.sixlab.sixtools.comun.mapper.SeisBandejaMapper;
import org.eu.sixlab.sixtools.comun.util.Du;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 16:52
 */
public class TrayDao extends Du{

    public List<SeisBandeja> getSubTrays(Integer parentId){
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            SeisBandeja sixTray = new SeisBandeja();
            sixTray.setParentId(parentId);

            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectByOne(sixTray);
            return sixTrayList;
        }
    }

    public List<SeisBandeja> getToolFolders(){
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectToolFolders();
            return sixTrayList;
        }
    }

    public List<SeisBandeja> getAll() {
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectAll();
            return sixTrayList;
        }
    }

    public void insert(SeisBandeja sixTray) {
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            seisBandejaMapper.insert(sixTray);
            ss.commit();
        }
    }

    public void delete(Integer id) {
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            seisBandejaMapper.delete(id);
            ss.commit();
        }
    }

    public void update(SeisBandeja sixTray) {
        try(SqlSession ss = factory.openSession()){
            SeisBandejaMapper seisBandejaMapper = getMapper(ss);
            seisBandejaMapper.update(sixTray);
            ss.commit();
        }
    }

    @Override
    public SeisBandejaMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisBandejaMapper.class);
    }
}
