/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

import org.apache.ibatis.session.SqlSession;
import org.eu.sixlab.sixtools.comun.bean.SeisPelicula;
import org.eu.sixlab.sixtools.comun.mapper.SeisPeliculaMapper;
import org.eu.sixlab.sixtools.comun.util.Du;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/13 16:43
 */
public class MovieDao extends Du{

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

    public static void main(String[] args) {
        MovieDao dao = new MovieDao();

        System.out.println(dao.getAllMovies().size());
        SeisPelicula pelicula = new SeisPelicula();
        pelicula.setMovieName("ttttt");
        pelicula.setViewDate("20");
        dao.insert(pelicula);
        System.out.println(dao.getAllMovies().size());
    }
}
