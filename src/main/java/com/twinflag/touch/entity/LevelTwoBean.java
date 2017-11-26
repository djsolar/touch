package com.twinflag.touch.entity;

import java.util.List;

public class LevelTwoBean {

    private String label;

    private boolean isMany;

    private String title;

    private String url;

    private List<ContentBean> contentBeans;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isMany() {
        return isMany;
    }

    public void setMany(boolean many) {
        isMany = many;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ContentBean> getContentBeans() {
        return contentBeans;
    }

    public void setContentBeans(List<ContentBean> contentBeans) {
        this.contentBeans = contentBeans;
    }
}
