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
@Table("seis_nine_group")
public class SeisNineGroup {

    @Id
    private int id;
    @Column("group_name")
    private String groupName;
    @Column("group_order")
    private int groupOrder;
    @Column("group_date")
    private String groupDate;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName () {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupOrder () {
        return groupOrder;
    }

    public void setGroupOrder(int groupOrder) {
        this.groupOrder = groupOrder;
    }

    public String getGroupDate () {
        return groupDate;
    }

    public void setGroupDate(String groupDate) {
        this.groupDate = groupDate;
    }

}