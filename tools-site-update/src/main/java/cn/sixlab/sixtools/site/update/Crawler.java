/**
 * @Copyright © Sixlab 2017
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.site.update;

import cn.sixlab.sixtools.dao.bean.sqlite.ToolsSiteContent;
import cn.sixlab.sixtools.dao.util.Collection;
import cn.sixlab.sixtools.dao.util.D;
import cn.sixlab.sixtools.site.update.crawler.CrawlerImpl6vHao;
import cn.sixlab.sixtools.site.update.crawler.CrawlerImplWaitSun;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2017/03/12 22:09
 */
public abstract class Crawler {
    
    protected Dao dao = D.dao;
    protected String siteCode;
    
    /**
     * 构造方法
     *
     * @param siteCode
     */
    protected Crawler(String siteCode) {
        this.siteCode = siteCode;
    }
    
    /**
     * 获取爬虫实例
     * @param siteCode
     * @return
     */
    public static Crawler init(String siteCode){
        if (Site.WAIT_SUN.equals(siteCode)) {
            return new CrawlerImplWaitSun(siteCode);
        }
        if (Site.V6_HAO.equals(siteCode)) {
            return new CrawlerImpl6vHao(siteCode);
        }
        
        
        return null;
    }
    
    /**
     * 将所有数据转为 html
     *
     * @param contentList
     * @return
     */
    protected String list2Html(List<ToolsSiteContent> contentList){
        StringBuffer sb = new StringBuffer();
        sb.append("<ul>");
        for (ToolsSiteContent content : contentList) {
            sb.append(item2Str(content));
        }
        sb.append("</ul>");
        return sb.toString();
    }
    
    /**
     * 将一条数据转为 html
     *
     * @param content
     * @return
     */
    protected String item2Str(ToolsSiteContent content) {
        String url = content.getUrl();
        String name = content.getName();
        //return "<li><a href='" + url + "'>" + name + "</a></li>";
        String date = content.getPublishDate();
        return "<li><a href='" + url + "'>【" + date + "】" + name + "</a></li>";
    }
    
    /**
     * 判断数据是否已经存在
     *
     * @param url
     * @return
     */
    protected boolean repeat(String url) {
        Condition contentCnd = Cnd.where("site", "=", siteCode).and("url", "=", url);
        List<ToolsSiteContent> contentList = dao.query(ToolsSiteContent.class, contentCnd);
        return Collection.isNotEmpty(contentList);
    }
    
    /**
     * 将新数据入库
     *
     * @param contentList
     */
    protected void insert(List<ToolsSiteContent> contentList) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(date);
        for (ToolsSiteContent content : contentList) {
            content.setSite(siteCode);
            content.setInDate(str);
        }
        dao.fastInsert(contentList);
    }
    
    /**
     * 获取所有的旧数据
     *
     * @return
     */
    public String fetchOld(){
        StringBuffer sb = new StringBuffer();
        //查询所有日期
        FieldFilter ff = FieldFilter.create(ToolsSiteContent.class, "^inDate$");
        Condition dateCnd = Cnd.where("site", "=", siteCode).groupBy("inDate").asc("inDate");
        List<ToolsSiteContent> dateList = dao.query(ToolsSiteContent.class, dateCnd);
        
        //循环所有日期，取出每个日期下的所有内容
        if(Collection.isNotEmpty(dateList)){
            for (ToolsSiteContent date : dateList) {
                //先加个标题
                sb.append("<h2>" + date.getInDate() + "</h2>");
                //取出时间下所有内容
                Condition contentCnd = Cnd.where("site", "=", siteCode).and("in_date","=",date.getInDate()).asc("id");
                List<ToolsSiteContent> contentList = dao.query(ToolsSiteContent.class, contentCnd);
                //循环所有日期
                sb.append(list2Html(contentList));
            }
        }
        return sb.toString();
    }
    
    /**
     * 获取新数据，每个网站不一样，所以在实现类中实现
     *
     * @return
     */
    public abstract String fetchNew();
    
    /**
     * 获取新数据，每个网站不一样，所以在实现类中实现
     *
     * @return
     */
    protected abstract List<ToolsSiteContent> elements2List(Elements elements);
}
