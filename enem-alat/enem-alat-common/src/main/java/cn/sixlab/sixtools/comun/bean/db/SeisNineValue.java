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
@Table("seis_nine_value")
public class SeisNineValue {

    @Id
    private int id;
    @Column("group_id")
    private int groupId;
    @Column("column_id")
    private int columnId;
    @Column("value")
    private String value;
    @Column("value_date")
    private String valueDate;

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

    public int getColumnId () {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public String getValue () {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueDate () {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

}