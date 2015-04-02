/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;

import java.util.List;

/**
 * SixTray对应的Mapper文件
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public interface SixTrayMapper {

    public void insert(SixTray sixTray);
    public List<SixTray> selectAll();
    public List<SixTray> selectByOne(SixTray sixTray);

    List<SixTray> selectToolFolders();

    void delete(Integer id);

    void update(SixTray sixTray);
}
