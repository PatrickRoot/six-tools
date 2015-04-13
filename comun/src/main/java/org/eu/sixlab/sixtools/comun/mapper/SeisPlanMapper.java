package org.eu.sixlab.sixtools.comun.mapper;

import org.eu.sixlab.sixtools.comun.bean.SeisPlan;

import java.util.List;

public interface SeisPlanMapper {

    int insert(SeisPlan record);

    SeisPlan selectById(Integer id);

    int update(SeisPlan record);

    List<SeisPlan> selectByPlan(SeisPlan record);

}