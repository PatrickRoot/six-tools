package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

@Table("tool_meta")
public class ToolMeta {

    @Id
    private int id;
    @Column("tool_key")
    private String toolKey;
    @Column("tool_value")
    private String toolValue;
    @Column("tool_flag")
    private String toolFlag;
    @Column("tool_remark")
    private String toolRemark;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToolKey () {
        return toolKey;
    }

    public void setToolKey(String toolKey) {
        this.toolKey = toolKey;
    }

    public String getToolValue () {
        return toolValue;
    }

    public void setToolValue(String toolValue) {
        this.toolValue = toolValue;
    }

    public String getToolFlag () {
        return toolFlag;
    }

    public void setToolFlag(String toolFlag) {
        this.toolFlag = toolFlag;
    }

    public String getToolRemark () {
        return toolRemark;
    }

    public void setToolRemark(String toolRemark) {
        this.toolRemark = toolRemark;
    }

}