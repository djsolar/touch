package com.twinflag.touch.model;

import java.util.Date;

public class LevelOneBean {

    private Integer id;

    private String normalPic;

    private String url;

    private Integer programId;

    private String levelOneHtml;

    private String levelOneXml;

    private Integer order;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNormalPic() {
        return normalPic;
    }

    public void setNormalPic(String normalPic) {
        this.normalPic = normalPic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    public String getLevelOneHtml() {
        return levelOneHtml;
    }

    public void setLevelOneHtml(String levelOneHtml) {
        this.levelOneHtml = levelOneHtml;
    }

    public String getLevelOneXml() {
        return levelOneXml;
    }

    public void setLevelOneXml(String levelOneXml) {
        this.levelOneXml = levelOneXml;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
