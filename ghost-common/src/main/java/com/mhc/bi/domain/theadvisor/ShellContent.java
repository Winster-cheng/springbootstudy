package com.mhc.bi.domain.theadvisor;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
public class ShellContent {
    private int id;
    private String shellName;
    private String shellContent;
    private String shellType;
    private String gmtCreate;
    private String gmtModify;

    public ShellContent() {
    }

    public ShellContent(String shellName, String shellContent, String shellType, String gmtCreate) {
        this.shellName = shellName;
        this.shellContent = shellContent;
        this.shellType = shellType;
        this.gmtCreate = gmtCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShellName() {
        return shellName;
    }

    public void setShellName(String shellName) {
        this.shellName = shellName;
    }

    public String getShellContent() {
        return shellContent;
    }

    public void setShellContent(String shellContent) {
        this.shellContent = shellContent;
    }

    public String getShellType() {
        return shellType;
    }

    public void setShellType(String shellType) {
        this.shellType = shellType;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }
}