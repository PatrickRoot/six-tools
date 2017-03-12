/**
 * @Copyright © Sixlab 2017
 * @author 六楼的雨/loki
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.site.update.crawler;

import cn.sixlab.sixtools.dao.bean.sqlite.ToolsSiteContent;
import cn.sixlab.sixtools.site.update.Crawler;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author 六楼的雨/loki
 * @date 2017/03/12 22:09
 */
public class CrawlerImplWaitSun extends Crawler {
    
    /**
     * 构造方法
     *
     * @param siteCode
     */
    public CrawlerImplWaitSun(String siteCode) {
        super(siteCode);
    }
    
    /**
     * @return
     */
    @Override
    public String fetchNew() {
        String html = null;
        
        String site = "http://www.waitsun.com/";
        Connection connect = Jsoup.connect(site);
        
        try {
            List<ToolsSiteContent> contentList = new ArrayList<>();
            
            // 第一页
            Document doc = connect.get();
            Elements p1Elements = doc.select(".masonry .item .caption dl a");
            contentList.addAll(elements2List(p1Elements));
            // 第二页
            Elements p2Elements = doc.select(".table-striped tr .pull-left h1 a");
            contentList.addAll(elements2List(p2Elements));
            
            if(p2Elements.size()==12){
                //后边的数据
                for (int i = 3; i < 11; i++) {
                    Connection conn = Jsoup.connect("http://www.waitsun.com/page/" + i);
                    Document document = conn.get();
                    Elements elements = document.select(".table-striped tr .pull-left h1 a");
                    contentList.addAll(elements2List(elements));
                    if(elements.size()<12){
                        break;
                    }
                }
            }
            
            insert(contentList);
            html = list2Html(contentList);
        } catch (IOException e) {
            html = "读取网页错误";
            e.printStackTrace();
        }
        
        return html;
    }
    
    @Override
    protected List<ToolsSiteContent> elements2List(Elements elements){
        List<ToolsSiteContent> contentList = new ArrayList<>();
        for (Element element : elements) {
            String href = element.attr("href");
            String text = element.text();
            
            if (repeat(href)){
                break;
            }
        
            ToolsSiteContent content = new ToolsSiteContent();
            content.setUrl(href);
            content.setName(text);
            content.setPublishDate("APP");
            contentList.add(content);
        }
        return contentList;
    }
}
