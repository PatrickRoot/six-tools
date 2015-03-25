/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/16 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.beans;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/16
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class SixTray {

    private Integer id;
    private String trayName;
    private String path;
    private String command;
    private String params;
    //当前这个tool是什么类型的tool
    private String toolType;
    //当前这个tool在tray中是父节点还是叶子节点
    private String trayType;
    private String toolOrder;
    private Integer parentId;
    private String parentName;
    private String createTime;
    private String updateTime;

    @Override
    public String toString() {
        return this.trayName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTrayType() {
        return trayType;
    }

    public void setTrayType(String trayType) {
        this.trayType = trayType;
    }

    public String getTrayName() {
        return trayName;
    }

    public void setTrayName(String trayName) {
        this.trayName = trayName;
    }

    public String getToolOrder() {
        return toolOrder;
    }

    public void setToolOrder(String toolOrder) {
        this.toolOrder = toolOrder;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
