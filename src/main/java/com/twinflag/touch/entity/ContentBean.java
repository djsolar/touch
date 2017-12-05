package com.twinflag.touch.entity;

import java.util.List;

public class ContentBean {

    // 内容标签
    private String label;

    // 内容标题
    private String title;

    // 内容路径
    private List<String> paths;

    // 内容类型 1.图片 2.文本
    private int type;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
