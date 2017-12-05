package com.twinflag.touch.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "t_content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String label;

    private String title;

    private int type;

    @ManyToOne
    @JoinColumn(name = "level_two_id")
    private LevelTwo levelTwo;

    @Column(name = "updatetime")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "createtime")
    @CreatedDate
    private Date createTime;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "content")
    private List<Source> sources;

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

    public LevelTwo getLevelTwo() {
        return levelTwo;
    }

    public void setLevelTwo(LevelTwo levelTwo) {
        this.levelTwo = levelTwo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
