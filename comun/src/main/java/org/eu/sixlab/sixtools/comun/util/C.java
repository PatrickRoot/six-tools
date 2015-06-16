/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.comun.util;

/**
 * SixTools中的静态变量
 *
 * @author 六楼的雨/loki
 * @date 2015/2/17 19:46
 */
public class C {

    //Six-Tools 的ID
    public static final int TOOLS_PLAN_ID =0;
    public static final int TOOLS_PELICULA_ID = 1;
    public static final int TOOLS_PUNTO_ID = 2;
    public static final int TOOLS_TOMCAT_ID = 3;

    //Six-Tray
    public static final Integer ROOT_PARENT_ID =0;

    public static final String TOOL_TYPE_TRAY_FOLDER = "TF";
    public static final String TOOL_TYPE_COMMAND     = "C";
    public static final String TOOL_TYPE_WEBSITE     = "W";
    public static final String TOOL_TYPE_COPY_TOOL   = "CT";
    public static final String TOOL_TYPE_FILE        = "F";
    public static final String TOOL_TYPE_FOLDER      = "D";

    public static final String WINDOWS_DEFAULT_IE_COMMAND = " rundll32 url.dll,FileProtocolHandler ";
    public static final String WINDOWS_EXPLORER_COMMAND = " explorer ";

    public static final String BAIDU_SEARCH_STRING = "http://www.baidu.com/s?word=";
    public static final String DOUBAN_SEARCH_STRING = "http://movie.douban.com/subject_search?search_text=";

    //Six-Plan
    public static final String PLAN_TYPE_ALL = "999";
    public static final String PLAN_TYPE_YEAR = "400";
    public static final String PLAN_TYPE_SEASON = "300";
    public static final String PLAN_TYPE_MONTH = "200";
    public static final String PLAN_TYPE_WEEK = "100";

    public static final String PLAN_STATUS_ALL = "999";
    public static final String PLAN_STATUS_ING = "400";
    public static final String PLAN_STATUS_ED = "300";
//    public static final Integer PLAN_STATUS_TEMP = 200;
    public static final String PLAN_STATUS_STOP = "100";

    public static final String PLAN_OUT_COUNT = "[plan]";

    private C(){
        super();
    }
}
