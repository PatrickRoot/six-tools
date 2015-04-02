/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.sixtray.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.dao.SixTrayMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * SixTray类的Dao操作
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public class SixTrayDao {

    public static List<SixTray> getSubTrays(Integer parentId){
        SixTray sixTray = new SixTray();
        sixTray.setParentId(parentId);
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        List<SixTray> sixTrayList = sixTrayMapper.selectByOne(sixTray);
        SixDaoUtil.close();
        return sixTrayList;
    }

    public static List<SixTray> getToolFolders(){
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        List<SixTray> sixTrayList = sixTrayMapper.selectToolFolders();
        SixDaoUtil.close();
        return sixTrayList;
    }
    
    public static List<SixTray> getAll() {
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        List<SixTray> sixTrayList = sixTrayMapper.selectAll();
        SixDaoUtil.close();
        return sixTrayList;
    }

    public static void delete(Integer id) {
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        sixTrayMapper.delete(id);
        SixDaoUtil.close();
    }

    public static void insert(SixTray sixTray) {
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        sixTrayMapper.insert(sixTray);
        SixDaoUtil.close();
    }

    public static void update(SixTray sixTray) {
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        sixTrayMapper.update(sixTray);
        SixDaoUtil.close();
    }
}
