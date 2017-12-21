package com.twinflag.touch.entity;

import lombok.Data;

import java.util.List;

@Data
public class ContentBean {

    // 内容标签
    private String label;

    // 内容标题
    private String title;

    // 内容路径
    private List<String> paths;

    // 内容类型 1.图片 2.文本
    private int mediaType;

    private List<MaterialBean> materials;
}
