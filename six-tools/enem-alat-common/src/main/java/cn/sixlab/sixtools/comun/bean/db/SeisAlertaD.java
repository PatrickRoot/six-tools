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
 * @date 2015-07-06T22:35:38.914
 */
@Table("seis_alerta_d")
public class SeisAlertaD {

    @Id
    private int id;
    @Column("task_id")
    private int taskId;
    @Column("num")
    private int num;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId () {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getNum () {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}