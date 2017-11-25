package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_level_one")
public class LevelOneBean {

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
    private ProgramBean program;

    @Column(name = "level_order")
    private Integer levelOrder;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private UserBean updateUser;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private UserBean createUser;

    @Column(name = "updatetime")
    private Date updateTime;

    @Column(name = "createtime")
    private Date createTime;

    @OneToMany(mappedBy = "levelOne")
    private Set<LevelTwoBean> levelTwos;

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

    public ProgramBean getProgram() {
        return program;
    }

    public void setProgram(ProgramBean program) {
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

    public UserBean getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UserBean updateUser) {
        this.updateUser = updateUser;
    }

    public UserBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UserBean createUser) {
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

    public Set<LevelTwoBean> getLevelTwos() {
        return levelTwos;
    }

    public void setLevelTwos(Set<LevelTwoBean> levelTwos) {
        this.levelTwos = levelTwos;
    }
}
