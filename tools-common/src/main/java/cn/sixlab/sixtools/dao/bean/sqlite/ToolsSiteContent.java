package cn.sixlab.sixtools.dao.bean.sqlite;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("tools_site_content")
public class ToolsSiteContent {

    @Id
    private int id;
    @Column("url")
    private String url;
    @Column("name")
    private String name;
    @Column("publish_date")
    private String publishDate;
    @Column("site")
    private String site;
    @Column("in_date")
    private String inDate;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPublishDate() {
        return publishDate;
    }
    
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
    
    public String getSite() {
        return site;
    }
    
    public void setSite(String site) {
        this.site = site;
    }
    
    public String getInDate() {
        return inDate;
    }
    
    public void setInDate(String inDate) {
        this.inDate = inDate;
    }
}