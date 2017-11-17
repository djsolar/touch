package com.twinflag.touch.model;

import java.util.Date;

public class LevelTwoBean {

    private String id;

    private String levelTwoXml;

    private String levelTwoHtml;

    private Date updateTime;

    private Integer levelOneId;

    private Integer updateUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevelTwoXml() {
        return levelTwoXml;
    }

    public void setLevelTwoXml(String levelTwoXml) {
        this.levelTwoXml = levelTwoXml;
    }

    public String getLevelTwoHtml() {
        return levelTwoHtml;
    }

    public void setLevelTwoHtml(String levelTwoHtml) {
        this.levelTwoHtml = levelTwoHtml;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLevelOneId() {
        return levelOneId;
    }

    public void setLevelOneId(Integer levelOneId) {
        this.levelOneId = levelOneId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
