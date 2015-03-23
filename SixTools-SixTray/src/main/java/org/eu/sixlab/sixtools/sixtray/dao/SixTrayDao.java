/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.dao.SixTrayMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
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
}
