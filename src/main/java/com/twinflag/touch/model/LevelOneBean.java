package com.twinflag.touch.model;

import java.util.Date;

public class LevelOneBean {

    private Integer id;

    private String normalPic;

    private String selectedPic;

    private String url;

    private Integer programId;

    private Integer levelOrder;

    private Integer updateUserId;

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

    public String getSelectePic() {
        return selectedPic;
    }

    public void setSelectePic(String selectePic) {
        this.selectedPic = selectePic;
    }

    public Integer getLevelOrder() {
        return levelOrder;
    }

    public void setLevelOrder(Integer levelOrder) {
        this.levelOrder = levelOrder;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
