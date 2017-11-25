package com.twinflag.touch.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_program")
public class ProgramBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "program_name")
    private String programName;

    @Column(name = "program_xml")
    private String programXml;

    @Column(name = "zip_path")
    private String zipPath;

    @Column(name = "source_path")
    private String sourcePath;

    private int type;

    @ManyToOne
    @JoinColumn(name = "create_user")
    private UserBean createUser;

    @ManyToOne
    @JoinColumn(name = "update_user")
    private UserBean updateUser;

    @Column(name = "updatetime")
    private Date updateTime;

    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "createtime")
    private Date createTime;

    @OneToMany(mappedBy = "program")
    private Set<LevelOneBean> levelOnes;

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

    public String getProgramXml() {
        return programXml;
    }

    public void setProgramXml(String programXml) {
        this.programXml = programXml;
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

    public UserBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(UserBean createUser) {
        this.createUser = createUser;
    }

    public UserBean getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(UserBean updateUser) {
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

    public Set<LevelOneBean> getLevelOnes() {
        return levelOnes;
    }

    public void setLevelOnes(Set<LevelOneBean> levelOnes) {
        this.levelOnes = levelOnes;
    }
}
