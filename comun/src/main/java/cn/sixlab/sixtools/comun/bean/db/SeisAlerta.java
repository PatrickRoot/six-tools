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
@Table("seis_alerta")
public class SeisAlerta {

    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("content")
    private String content;
    @Column("type")
    private String type;
    @Column("time")
    private String time;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent () {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType () {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime () {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}