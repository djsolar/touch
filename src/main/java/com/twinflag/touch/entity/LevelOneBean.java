package com.twinflag.touch.entity;

import java.util.List;

public class LevelOneBean {

    // 未选中图片路径
    private String normalPic;

    // 选中图片路径
    private String selectedPic;

    // 资源路径
    private String url;

    private List<LevelTwoBean> twoContent;


    public String getNormalPic() {
        return normalPic;
    }

    public void setNormalPic(String normalPic) {
        this.normalPic = normalPic;
    }

    public String getSelectedPic() {
        return selectedPic;
    }

    public void setSelectedPic(String selectedPic) {
        this.selectedPic = selectedPic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<LevelTwoBean> getTwoContent() {
        return twoContent;
    }

    public void setTwoContent(List<LevelTwoBean> twoContent) {
        this.twoContent = twoContent;
    }
}
