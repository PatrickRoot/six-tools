/* 
 ********************************************************************************
 * Copyright sixlab.eu.org 2015/4/2 Authors: 六楼的雨/loki <nianqinianyi@163.com>*
 ********************************************************************************
 */
package org.eu.sixlab.sixtools.common.beans;

/**
 * 作者：六楼的雨/loki
 * 创建时间：2015/4/2
 * 功能描述：
 * 版本：1.0-snapshot
 */
public class SixPlan {

    private Integer id;
    private String planName;
    //计划完成百分比
    private Integer planPer;
    private String planContent;
    private Integer superId;
    private Integer planType;
    //计划需要时间，小时
    private Integer planTime;
}
