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
 * @date 2015/4/4 11:41
 */
public class SixPlan {
    private Integer id;

    private String planName;

    private Integer planTime;

    private Integer planPer;

    private Integer planStatus;
    private String planStatusValue;

    private String planContent;

    private Integer planType;

    private Integer planYear;

    private Integer planSeason;

    private Integer planMonth;

    private Integer planWeek;

    private Integer parentId;

    private Integer sourceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public Integer getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Integer planTime) {
        this.planTime = planTime;
    }

    public Integer getPlanPer() {
        return planPer;
    }

    public void setPlanPer(Integer planPer) {
        this.planPer = planPer;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    public Integer getPlanSeason() {
        return planSeason;
    }

    public void setPlanSeason(Integer planSeason) {
        this.planSeason = planSeason;
    }

    public Integer getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(Integer planMonth) {
        this.planMonth = planMonth;
    }

    public Integer getPlanWeek() {
        return planWeek;
    }

    public void setPlanWeek(Integer planWeek) {
        this.planWeek = planWeek;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getPlanStatusValue() {
        return planStatusValue;
    }

    public void setPlanStatusValue(String planStatusValue) {
        this.planStatusValue = planStatusValue;
    }
}