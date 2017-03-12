package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tools_site_config")
public class ToolsSiteConfig {

    @Id
    private int id;
    @Column("name")
    private String name;
    @Column("url")
    private String url;
    @Column("site_code")
    private String siteCode;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getSiteCode() {
        return siteCode;
    }
    
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
}