package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_content")
public class Content {

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
    private User updateUser;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private User createUser;

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
