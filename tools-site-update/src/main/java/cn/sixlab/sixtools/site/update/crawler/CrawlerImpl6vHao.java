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
public class CrawlerImpl6vHao extends Crawler {
    
    /**
     * 构造方法
     *
     * @param siteCode
     */
    public CrawlerImpl6vHao(String siteCode) {
        super(siteCode);
    }
    
    /**
     * @return
     */
    @Override
    public String fetchNew() {
        String html = null;
        
        String site = "http://www.6vhao.net/";
        Connection connect = Jsoup.connect(site);
        
        try {
            List<ToolsSiteContent> contentList = new ArrayList<>();
            
            Document doc = connect.get();
            Elements elements = doc.select(".tnlist").eq(0).select("li");
            contentList.addAll(elements2List(elements));
            
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
            Element span = element.getElementsByTag("span").get(0);
            Element a = element.getElementsByTag("a").get(0);
            
            String date = span.text();
            String href = a.attr("href");
            String text = a.text();
            
            if (repeat(href)){
                break;
            }
        
            ToolsSiteContent content = new ToolsSiteContent();
            content.setUrl(href);
            content.setName(text);
            content.setPublishDate(date);
            contentList.add(content);
        }
        return contentList;
    }
}
