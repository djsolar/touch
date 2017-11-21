package com.twinflag.touch.model;

import java.util.Date;

public class ContentBean {

    private Integer id;

    private String label;

    private int type;

    private Integer levelTwoId;

    private Date updateTime;

    private Integer updateUserId;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getLevelTwoId() {
        return levelTwoId;
    }

    public void setLevelTwoId(Integer levelTwoId) {
        this.levelTwoId = levelTwoId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
