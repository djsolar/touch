package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_content")
public class ContentBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String label;

    private int type;

    @Column(name = "level_two_id")
    private Integer levelTwoId;

    @Column(name = "updatetime")
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private UserBean updateUser;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private UserBean createUser;

    private String url;

    @Column(name = "createtime")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getLevelTwoId() {
        return levelTwoId;
    }

    public void setLevelTwoId(Integer levelTwoId) {
        this.levelTwoId = levelTwoId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
