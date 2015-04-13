/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.dao;

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
public class MovieDao {

    public void insert(SeisPelicula record){
        try{
            SeisPeliculaMapper movieRecordMapper = Du.getMapper(SeisPeliculaMapper.class);
            movieRecordMapper.insert(record);
        }finally {
            Du.close();
        }
    }

    public List<SeisPelicula> getAllMovies() {
        try{
            SeisPeliculaMapper movieRecordMapper = Du.getMapper(SeisPeliculaMapper.class);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectAll();
            return movieRecordList;
        }finally {
            Du.close();
        }
    }

    public List<SeisPelicula> getMoviesByKeyword(String keyword) {
        try{
            SeisPeliculaMapper movieRecordMapper = Du.getMapper(SeisPeliculaMapper.class);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectByKeyword(keyword);
            return movieRecordList;
        }finally {
            Du.close();
        }
    }

    public void update(SeisPelicula record) {
        try{
            SeisPeliculaMapper movieRecordMapper = Du.getMapper(SeisPeliculaMapper.class);
            movieRecordMapper.update(record);
        }finally {
            Du.close();
        }
    }

    public List<SeisPelicula> getMoviesByMovieName(String name) {
        try{
            SeisPeliculaMapper movieRecordMapper = Du.getMapper(SeisPeliculaMapper.class);
            List<SeisPelicula> movieRecordList = movieRecordMapper.selectByMovieName(name);

            return movieRecordList;
        }finally {
            Du.close();
        }
    }
}
