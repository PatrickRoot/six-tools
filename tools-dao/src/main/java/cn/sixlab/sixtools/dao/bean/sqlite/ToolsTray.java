package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tools_tray")
public class ToolsTray {
    @Id
    private Integer id;
    @Column("tray_name")
    private String trayName;
    @Column("command")
    private String command;
    @Column("tool_type")
    private String toolType;
    @Column("tool_order")
    private String toolOrder;
    @Column("parent_id")
    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrayName() {
        return trayName;
    }

    public void setTrayName(String trayName) {
        this.trayName = trayName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
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

    @Override
    public String toString() {
        return trayName;
    }
}