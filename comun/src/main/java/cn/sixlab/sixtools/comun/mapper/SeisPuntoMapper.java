/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.mapper;

import cn.sixlab.sixtools.comun.bean.SeisPunto;

import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 22:08
 */
public interface SeisPuntoMapper {

    int insert(SeisPunto seisPunto);
    List<SeisPunto> queryAll();
    Double queryPunto();
}
