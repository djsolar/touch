package com.twinflag.touch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LevelTwoBean {

    private Integer id;

    private String label;

    private boolean isMany;

    private String title;

    private String url;

    private int mediaType;

    private MaterialBean urlMaterial;

    private List<ContentBean> contentBeans;

}
