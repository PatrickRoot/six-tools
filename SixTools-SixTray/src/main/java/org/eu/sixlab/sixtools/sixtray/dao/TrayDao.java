/* 
 **************************************************************************************
 * Copyright www.ebidding.com.cn 2015/2/17 Authors: 曹林伟 <caolinwei@ebidding.com.cn>*
 **************************************************************************************
 */
package org.eu.sixlab.sixtools.sixtray.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;
import org.eu.sixlab.sixtools.common.dao.SixTrayMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * 作者：曹林伟
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：2.0.1
 */
public class TrayDao {

    public static List<SixTray> getSubTrays(Integer parentId){
        SixTray sixTray = new SixTray();
        sixTray.setParentId(parentId);
        SixTrayMapper sixTrayMapper = SixDaoUtil.getMapper(SixTrayMapper.class);
        List<SixTray> sixTrayList = sixTrayMapper.selectByOne(sixTray);
        SixDaoUtil.close();
        return sixTrayList;
    }
    
}
