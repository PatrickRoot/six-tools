package cn.sixlab.sixtools.dao.bean.mysql;

import java.util.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

@Table("tools_record_item")
public class ToolsRecordItem {

    @Id
    private int id;
    @Column("item_name")
    private String itemName;
    @Column("order")
    private int order;
    @Column("remark")
    private String remark;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName () {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getOrder () {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getRemark () {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}