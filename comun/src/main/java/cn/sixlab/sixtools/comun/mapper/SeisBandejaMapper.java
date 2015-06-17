/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.mapper;

import cn.sixlab.sixtools.comun.bean.SeisBandeja;

import java.util.List;

/**
 * SixTray对应的Mapper文件
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public interface SeisBandejaMapper {

    int insert(SeisBandeja record);

    void update(SeisBandeja record);

    void delete(Integer id);

    List<SeisBandeja> selectAll();

    List<SeisBandeja> selectByOne(SeisBandeja record);

    List<SeisBandeja> selectToolFolders();

}
