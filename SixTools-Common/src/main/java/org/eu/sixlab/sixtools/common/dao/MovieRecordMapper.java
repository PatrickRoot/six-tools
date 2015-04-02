/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.MovieRecord;

import java.util.List;

/**
 * MovieRecord对应的Mapper文件
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public interface MovieRecordMapper {

    public void insert(MovieRecord movieRecord);
    List<MovieRecord> selectAll();

    List<MovieRecord> selectByKeyword(String keyword);
    public void update(MovieRecord movieRecord);

    List<MovieRecord> selectByMovieName(String name);
}
