package cn.sixlab.sixtools.dao.bean.mysql;

import java.util.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Date;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

@Table("tools_record_val")
public class ToolsRecordVal {

    @Id
    private int id;
    @Column("item_id")
    private int itemId;
    @Column("record_date")
    private Date recordDate;
    @Column("record_val")
    private String recordVal;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId () {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Date getRecordDate () {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getRecordVal () {
        return recordVal;
    }

    public void setRecordVal(String recordVal) {
        this.recordVal = recordVal;
    }

}