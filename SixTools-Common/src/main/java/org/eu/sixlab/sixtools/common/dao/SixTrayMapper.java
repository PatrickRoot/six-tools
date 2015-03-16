package org.eu.sixlab.sixtools.common.dao;

import org.eu.sixlab.sixtools.common.beans.SixTray;

import java.util.List;

public interface SixTrayMapper {

    public void insert(SixTray sixTray);
    public List<SixTray> selectAll();
    public List<SixTray> selectByOne(SixTray sixTray);
}
