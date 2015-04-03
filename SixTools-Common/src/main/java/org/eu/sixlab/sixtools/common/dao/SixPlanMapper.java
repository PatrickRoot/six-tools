package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.SixPlan;

import java.util.List;

public interface SixPlanMapper {

//    int deleteByPrimaryKey(Integer id);

    int insert(SixPlan record);

    int insertSelective(SixPlan record);

    SixPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SixPlan record);

    int updateByPrimaryKey(SixPlan record);

    List<SixPlan> selectBySixPlan(SixPlan record);
}