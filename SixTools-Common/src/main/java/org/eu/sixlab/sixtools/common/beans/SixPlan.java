/**
 * @Copyright © Sixlab 2015
 * @author 六楼的雨/loki
 * @Email <nianqinianyi@163.com>
 */
package org.eu.sixlab.sixtools.common.beans;

/**
 * SixPlan映射Bean
 *
 * @author 六楼的雨/loki
 * @date 2015/4/1 16:21
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
