/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

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
public class TrayDao {

    public List<SeisBandeja> getSubTrays(Integer parentId){
        try{
            SeisBandeja sixTray = new SeisBandeja();
            sixTray.setParentId(parentId);

            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectByOne(sixTray);
            return sixTrayList;
        }finally {
            Du.close();
        }
    }

    public List<SeisBandeja> getToolFolders(){
        try{
            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectToolFolders();
            return sixTrayList;
        }finally {
            Du.close();
        }
    }

    public List<SeisBandeja> getAll() {
        try{
            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            List<SeisBandeja> sixTrayList = seisBandejaMapper.selectAll();
            return sixTrayList;
        }finally {
            Du.close();
        }
    }

    public void insert(SeisBandeja sixTray) {
        try{
            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            seisBandejaMapper.insert(sixTray);
        }finally {
            Du.close();
        }
    }

    public void delete(Integer id) {
        try{
            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            seisBandejaMapper.delete(id);
        }finally {
            Du.close();
        }
    }

    public void update(SeisBandeja sixTray) {
        try{
            SeisBandejaMapper seisBandejaMapper = Du.getMapper(SeisBandejaMapper.class);
            seisBandejaMapper.update(sixTray);
        }finally {
            Du.close();
        }
    }
}
