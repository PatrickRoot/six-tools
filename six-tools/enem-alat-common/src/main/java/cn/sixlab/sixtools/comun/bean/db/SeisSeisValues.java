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
 * @date 2015-07-16T23:17:36.952
 */
@Table("seis_seis_values")
public class SeisSeisValues {

    @Id
    private int id;
    @Column("code")
    private String code;
    @Column("value")
    private String value;
    @Column("flag")
    private int flag;
    @Column("type")
    private int type;
    @Column("tags")
    private String tags;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode () {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue () {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getFlag () {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getType () {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTags () {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}