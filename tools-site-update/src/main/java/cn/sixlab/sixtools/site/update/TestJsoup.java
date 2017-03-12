/* 
 **************************************************************************************
 * Copyright www.ebidding.com.cn 2017/3/12 Authors: *
 **************************************************************************************
 */
package cn.sixlab.sixtools.site.update;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 创建时间：2017/3/12
 * 功能描述：
 */
public class TestJsoup {
    public static void main(String[] args) throws IOException {
    
        String site = "http://www.6vhao.net/";
        Connection connect = Jsoup.connect(site);
        Document doc = connect.get();
        Elements elements = doc.select(".tnlist").eq(0).select("li");
        for (Element element : elements) {
            System.out.println(element.text());
        }
        System.out.println(elements.size());
    }
}
