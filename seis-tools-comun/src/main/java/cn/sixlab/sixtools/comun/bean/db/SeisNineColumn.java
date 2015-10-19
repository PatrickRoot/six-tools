/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.bean.db;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015-07-16T23:04:28.649
 */
@Table("seis_nine_column")
public class SeisNineColumn {

    @Id
    private int id;
    @Column("group_id")
    private int groupId;
    @Column("column_name")
    private String columnName;
    @Column("column_type")
    private int columnType;
    @Column("column_order")
    private String columnOrder;
    @Column("column_date")
    private String columnDate;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId () {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getColumnName () {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getColumnType () {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public String getColumnOrder () {
        return columnOrder;
    }

    public void setColumnOrder(String columnOrder) {
        this.columnOrder = columnOrder;
    }

    public String getColumnDate () {
        return columnDate;
    }

    public void setColumnDate(String columnDate) {
        this.columnDate = columnDate;
    }

}