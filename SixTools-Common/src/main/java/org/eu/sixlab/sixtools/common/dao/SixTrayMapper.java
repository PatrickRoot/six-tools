/*
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/16 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;

import java.util.List;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/16
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public interface SixTrayMapper {

    public void insert(SixTray sixTray);
    public List<SixTray> selectAll();
    public List<SixTray> selectByOne(SixTray sixTray);

    List<SixTray> selectToolFolders();

    void delete(Integer id);
}
