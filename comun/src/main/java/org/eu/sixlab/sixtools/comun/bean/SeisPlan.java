package org.eu.sixlab.sixtools.comun.bean;

public class SeisPlan {
    private Integer id;

    private String planName;

    private String planTime;

    private String planPer;

    private String planStatus;
    private String planStatusName;

    private String planContent;

    private String planType;

    private String planYear;

    private String planSeason;

    private String planMonth;

    private String planWeek;

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

    public String getPlanTime() {
        return planTime;
    }

    public void setPlanTime(String planTime) {
        this.planTime = planTime;
    }

    public String getPlanPer() {
        return planPer;
    }

    public void setPlanPer(String planPer) {
        this.planPer = planPer;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getPlanStatusName() {
        return planStatusName;
    }

    public void setPlanStatusName(String planStatusName) {
        this.planStatusName = planStatusName;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanYear() {
        return planYear;
    }

    public void setPlanYear(String planYear) {
        this.planYear = planYear;
    }

    public String getPlanSeason() {
        return planSeason;
    }

    public void setPlanSeason(String planSeason) {
        this.planSeason = planSeason;
    }

    public String getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(String planMonth) {
        this.planMonth = planMonth;
    }

    public String getPlanWeek() {
        return planWeek;
    }

    public void setPlanWeek(String planWeek) {
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
}