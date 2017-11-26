package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_level_one")
public class LevelOne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "normal_pic")
    private String normalPic;

    @Column(name = "selected_pic")
    private String selectedPic;

    private String url;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @Column(name = "level_order")
    private Integer levelOrder;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private User updateUser;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private User createUser;

    @Column(name = "updatetime")
    private Date updateTime;

    @Column(name = "createtime")
    private Date createTime;

    @OneToMany(mappedBy = "levelOne")
    private Set<LevelTwo> levelTwos;

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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSelectedPic() {
        return selectedPic;
    }

    public void setSelectedPic(String selectedPic) {
        this.selectedPic = selectedPic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<LevelTwo> getLevelTwos() {
        return levelTwos;
    }

    public void setLevelTwos(Set<LevelTwo> levelTwos) {
        this.levelTwos = levelTwos;
    }
}
