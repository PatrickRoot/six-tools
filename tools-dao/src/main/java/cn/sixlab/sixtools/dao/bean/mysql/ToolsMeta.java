package cn.sixlab.sixtools.dao.bean.mysql;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Column;

@Table("tools_meta")
public class ToolsMeta {

    @Id
    private int id;
    @Column("key_id")
    private int keyId;
    @Column("meta_key")
    private String metaKey;
    @Column("meta_value")
    private String metaValue;
    @Column("meta_flag")
    private String metaFlag;
    @Column("meta_remark")
    private String metaRemark;

    public int getId () {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKeyId () {
        return keyId;
    }

    public void setKeyId(int keyId) {
        this.keyId = keyId;
    }

    public String getMetaKey () {
        return metaKey;
    }

    public void setMetaKey(String metaKey) {
        this.metaKey = metaKey;
    }

    public String getMetaValue () {
        return metaValue;
    }

    public void setMetaValue(String metaValue) {
        this.metaValue = metaValue;
    }

    public String getMetaFlag () {
        return metaFlag;
    }

    public void setMetaFlag(String metaFlag) {
        this.metaFlag = metaFlag;
    }

    public String getMetaRemark () {
        return metaRemark;
    }

    public void setMetaRemark(String metaRemark) {
        this.metaRemark = metaRemark;
    }

}