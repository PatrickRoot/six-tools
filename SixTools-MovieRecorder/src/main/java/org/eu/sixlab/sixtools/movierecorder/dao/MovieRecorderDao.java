/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/3/21 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.movierecorder.dao;

import org.eu.sixlab.sixtools.common.beans.MovieRecord;
import org.eu.sixlab.sixtools.common.dao.MovieRecordMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/3/21
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class MovieRecorderDao {

    private static MovieRecordMapper mapper(){
        return SixDaoUtil.getMapper(MovieRecordMapper.class);
    }

    private static void close(){
        SixDaoUtil.close();
    }

    public static void insertMovie(MovieRecord movieRecord){
        mapper().insert(movieRecord);
        close();
    }

    public static List<MovieRecord> getAllMovies() {
        List<MovieRecord> movieRecordList = mapper().selectAll();
        close();
        return movieRecordList;
    }

    public static List<MovieRecord> getMoviesByKeyword(String keyword) {
        List<MovieRecord> movieRecordList = mapper().selectByKeyword(keyword);
        close();
        return movieRecordList;
    }
    
    public static void update(MovieRecord movieRecord) {
        mapper().update(movieRecord);
        close();
    }
    
    public static List<MovieRecord> getMoviesByMovieName(String name) {
        List<MovieRecord> movieRecordList = mapper().selectByMovieName(name);
        close();
        return movieRecordList;
    }
}
