package cn.sixlab.sixtools.comun.bean;

public class SeisBandeja {
    private Integer id;

    private String trayName;

    private String command;

    private String toolType;

    private String toolOrder;

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