/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 22:08
 */
@Table("tools_point")
public class ToolsPoint {
    @Id
    private Integer id;
    @Column("punto")
    private Double punto;
    @Column("reason")
    private String reason;
    @Column("indate")
    private String indate;

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPunto() {
        return punto;
    }

    public void setPunto(Double punto) {
        this.punto = punto;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
