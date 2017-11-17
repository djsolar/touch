package com.twinflag.touch.model;

import java.util.Date;

public class ProgramBean {

    private Integer id;

    private String programName;

    private String programXml;

    private String zipPath;

    private String sourcePath;

    private int type;

    private UserBean createUser;

    private UserBean updateUser;

    private Date updateTime;

    private ProgramBean template;

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

    public ProgramBean getTemplate() {
        return template;
    }

    public void setTemplate(ProgramBean template) {
        this.template = template;
    }
}
