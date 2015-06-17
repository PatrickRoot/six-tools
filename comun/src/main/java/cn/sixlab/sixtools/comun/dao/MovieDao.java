/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.dao;

import cn.sixlab.sixtools.comun.util.Du;
import org.apache.ibatis.session.SqlSession;
import cn.sixlab.sixtools.comun.bean.SeisPelicula;
import cn.sixlab.sixtools.comun.mapper.SeisPeliculaMapper;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 16:43
 */
public class MovieDao extends Du {

    @Override
    public SeisPeliculaMapper getMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SeisPeliculaMapper.class);
    }

    public void insert(SeisPelicula record){
        try(SqlSession ss = factory.openSession()){
            SeisPeliculaMapper movieRecordMapper = getMapper(ss);
            movieRecordMapper.insert(record);
            ss.commit();
        }
    }

    public List<SeisPelicula> getAllMovies() {
        try(SqlSession ss = factory.openSession()){
            SeisPeliculaMapper movieRecordMapper = getMapper(ss);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectAll();
            return movieRecordList;
        }
    }

    public List<SeisPelicula> getMoviesByKeyword(String keyword) {
        try(SqlSession ss = factory.openSession()){
            SeisPeliculaMapper movieRecordMapper = getMapper(ss);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectByKeyword(keyword);
            return movieRecordList;
        }
    }

    public void update(SeisPelicula record) {
        try(SqlSession ss = factory.openSession()){
            SeisPeliculaMapper movieRecordMapper = getMapper(ss);
            movieRecordMapper.update(record);
            ss.commit();
        }
    }

    public List<SeisPelicula> getMoviesByMovieName(String name) {
        try(SqlSession ss = factory.openSession()){
            SeisPeliculaMapper movieRecordMapper = getMapper(ss);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectByMovieName(name);
            return movieRecordList;
        }
    }
}
