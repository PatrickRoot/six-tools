/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.mapper;

import org.eu.sixlab.sixtools.comun.bean.SeisPelicula;

import java.util.List;

/**
 * MovieRecord对应的Mapper文件
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public interface SeisPeliculaMapper {

    int insert(SeisPelicula record);

    void update(SeisPelicula record);

    List<SeisPelicula> selectAll();

    List<SeisPelicula> selectByKeyword(String keyword);

    List<SeisPelicula> selectByMovieName(String name);
}
