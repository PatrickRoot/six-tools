/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.bean.ext;

import cn.sixlab.sixtools.comun.util.C;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/4/14 14:01
 */
public class ToolType{

    private String toolType;
    private String toolTypeName;

    public ToolType() {
        super();
    }

    public ToolType(String toolType, String toolTypeName) {
        this.toolType = toolType;
        this.toolTypeName = toolTypeName;
    }

    public static List<ToolType> allToolTypes(){
        List<ToolType> toolTypeList = new ArrayList<>();
        toolTypeList.add(new ToolType(C.TOOL_TYPE_FOLDER,"目录"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_FILE,"文件"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_WEBSITE,"网址"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_COMMAND, "命令"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_TRAY_FOLDER,"Tray文件夹"));
        toolTypeList.add(new ToolType(C.TOOL_TYPE_COPY_TOOL,"复制工具"));
        return toolTypeList;
    }

    @Override
    public String toString() {
        return toolTypeName;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolTypeName() {
        return toolTypeName;
    }

    public void setToolTypeName(String toolTypeName) {
        this.toolTypeName = toolTypeName;
    }
}