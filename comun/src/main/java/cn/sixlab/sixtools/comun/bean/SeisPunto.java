/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.comun.bean;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2015/5/22 22:08
 */
public class SeisPunto {
    private Integer id;
    private Double punto;
    private String reason;
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
