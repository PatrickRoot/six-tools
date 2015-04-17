package org.eu.sixlab.sixtools.comun.mapper;

import org.eu.sixlab.sixtools.comun.bean.SeisTools;

import java.util.List;

public interface SeisToolsMapper {

    int insert(SeisTools record);

    int update(SeisTools record);

    SeisTools selectById(Integer id);

    List<SeisTools> getEnableTools();
}