package com.twinflag.touch.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import javax.persistence.*;
import java.io.DataOutput;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_program")
public class Program implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(DataTablesOutput.View.class)
    private Integer id;

    @Column(name = "program_name")
    @JsonView(DataTablesOutput.View.class)
    private String programName;

    @Column(name = "zip_path")
    private String zipPath;

    @Column(name = "source_path")
    private String sourcePath;

    @JsonView(DataTablesOutput.View.class)
    private int type;

    @ManyToOne
    @JoinColumn(name = "create_user")
    @JsonView(DataTablesOutput.View.class)
    private User createUser;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private User updateUser;

    @Column(name = "updatetime")
    @LastModifiedDate
    @JsonView(DataTablesOutput.View.class)
    private Date updateTime;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "createtime")
    @CreatedDate
    private Date createTime;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "program")
    private Set<LevelOne> levelOnes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<LevelOne> getLevelOnes() {
        return levelOnes;
    }

    public void setLevelOnes(Set<LevelOne> levelOnes) {
        this.levelOnes = levelOnes;
    }
}
