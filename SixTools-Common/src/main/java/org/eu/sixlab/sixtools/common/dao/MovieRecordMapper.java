/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/21 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.MovieRecord;

import java.util.List;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/21
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public interface MovieRecordMapper {

    public void insert(MovieRecord movieRecord);
    List<MovieRecord> selectAll();

    List<MovieRecord> selectByKeyword(String keyword);
    public void update(MovieRecord movieRecord);

    List<MovieRecord> selectByMovieName(String name);
}
