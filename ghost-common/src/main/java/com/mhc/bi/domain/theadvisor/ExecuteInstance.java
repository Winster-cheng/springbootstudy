package com.mhc.bi.domain.theadvisor;

/**
 * @author baiyan
 * @date 2018/09/19
 * @description
 */
public class ExecuteInstance {
    private int id;
    private int parentId;
    private String startTime;
    private String endTime;
    private String gmtCreate;
    private String gmtModify;
    private int status;
    private String logLink;

    public ExecuteInstance() {
        super();
    }

    public ExecuteInstance(int id, int parentId, String startTime, String endTime, String gmtCreate, String gmtModify, int status, String longLink) {
        this.id = id;
        this.parentId = parentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.status = status;
        this.logLink = longLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLogLink() {
        return logLink;
    }

    public void setLogLink(String logLink) {
        this.logLink = logLink;
    }
}
