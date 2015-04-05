/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.movierecorder.dao;

import org.eu.sixlab.sixtools.common.beans.MovieRecord;
import org.eu.sixlab.sixtools.common.dao.MovieRecordMapper;
import org.eu.sixlab.sixtools.common.util.SixDaoUtil;

import java.util.List;

/**
 * MovieRecorder类的Dao操作
 *
 * @author 六楼的雨/loki
 * @date 2015/3/21 19:46
 */
public class Dao {

    private Dao(){
        super();
    }

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
