package cn.sixlab.sixtools.comun.mapper;

import cn.sixlab.sixtools.comun.bean.SeisPlan;

import java.util.List;

public interface SeisPlanMapper {

    int insert(SeisPlan record);

    SeisPlan selectById(Integer id);

    int update(SeisPlan record);

    List<SeisPlan> selectByPlan(SeisPlan record);
}