package com.twinflag.touch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LevelOneBean {

    private Integer id;

    // 未选中图片路径
    private String normalPic;

    // 选中图片路径
    private String selectedPic;

    private MaterialBean normalMaterial;

    private MaterialBean selectedMaterial;

    private int mediaType;

    // 资源路径
    private String url;

    private List<LevelTwoBean> twoContent;
}
