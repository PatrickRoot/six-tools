/**
 * @Copyright © Sixlab 2015
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @email <nianqinianyi@163.com>
 */
package cn.sixlab.sixtools.dao.util;

import java.io.FileWriter;

/**
 * TODO
 *
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @date 2015/6/28 13:38
 */
public class DUtil1 {

    public static void main(String[] args) throws Exception {
        genBeanFile();
        System.out.println("end");
    }

    private static void genBeanFile() throws Exception {
        StringBuffer javaFile = new StringBuffer();
    
        for (int i = 4; i < 26; i++) {
            javaFile.append("\n" +
                    "<div class='row' row='"+i+"'>\n" +
                    "    <div row='"+i+"' column='1' class='column'></div>\n" +
                    "    <div row='"+i+"' column='2' class='column'></div>\n" +
                    "    <div row='"+i+"' column='3' class='column'></div>\n" +
                    "    <div row='"+i+"' column='4' class='column'></div>\n" +
                    "    <div row='"+i+"' column='5' class='column'></div>\n" +
                    "    <div row='"+i+"' column='6' class='column'></div>\n" +
                    "    <div row='"+i+"' column='7' class='column'></div>\n" +
                    "    <div row='"+i+"' column='8' class='column'></div>\n" +
                    "    <div row='"+i+"' column='9' class='column'></div>\n" +
                    "    <div row='"+i+"' column='10' class='column'></div>\n" +
                    "    <div row='"+i+"' column='11' class='column'></div>\n" +
                    "    <div row='"+i+"' column='12' class='column'></div>\n" +
                    "    <div row='"+i+"' column='13' class='column'></div>\n" +
                    "    <div row='"+i+"' column='14' class='column'></div>\n" +
                    "    <div row='"+i+"' column='15' class='column'></div>\n" +
                    "    <div row='"+i+"' column='16' class='column'></div>\n" +
                    "    <div row='"+i+"' column='17' class='column'></div>\n" +
                    "    <div row='"+i+"' column='18' class='column'></div>\n" +
                    "    <div row='"+i+"' column='19' class='column'></div>\n" +
                    "    <div row='"+i+"' column='20' class='column'></div>\n" +
                    "    <div row='"+i+"' column='21' class='column'></div>\n" +
                    "    <div row='"+i+"' column='22' class='column'></div>\n" +
                    "    <div row='"+i+"' column='23' class='column'></div>\n" +
                    "    <div row='"+i+"' column='24' class='column'></div>\n" +
                    "    <div row='"+i+"' column='25' class='column'></div>\n" +
                    "</div>");
        }


        javaFile.append("");
        String filePath = "E:/a.txt";
        FileWriter writer = new FileWriter(filePath);
        writer.write(javaFile.toString());
        writer.flush();
        writer.close();
    }

}
