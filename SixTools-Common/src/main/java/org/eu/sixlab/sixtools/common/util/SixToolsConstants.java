/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/2/17 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.util;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/2/17
 * 功能描述：
 * 版本：1.0-SNAPSHOT
 */
public class SixToolsConstants {

    public static final Integer ROOT_PARENT_ID =0;

    public static final String TOOL_TYPE_FOLDER = "D";
    public static final String TOOL_TYPE_FILE = "F";
    public static final String TOOL_TYPE_WEBSITE = "W";
    public static final String TOOL_TYPE_COMMAND = "C";
    public static final String TOOL_TYPE_TRAY_FOLDER = "TF";
    public static final String TOOL_TYPE_COPY_TOOL = "CT";

    public static final String WINDOWS_DEFAULT_IE_COMMAND = " rundll32 url.dll,FileProtocolHandler ";
    public static final String WINDOWS_EXPLORER_COMMAND = " explorer ";

    public static final String BAIDU_SEARCH_STRING = "http://www.baidu.com/s?word=";
    public static final String DOUBAN_SEARCH_STRING = "http://movie.douban.com/subject_search?search_text=";
}
