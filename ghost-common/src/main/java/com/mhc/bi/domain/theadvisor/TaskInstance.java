package com.mhc.bi.domain.theadvisor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description 任务实例表(TaskInstance)：
 * id
 * 任务名称
 * 任务父依赖
 * 任务子依赖
 * 任务执行时间（如果执行时间是天那么默认凌晨0点执行）
 * name
 * in
 * out
 * gmt_create
 * status
 * executetime
 * paraments
 */
@Component
public class TaskInstance {
    private int id;
    private String name;
    private String shellName;
    private String input;
    private String output;
    private String gmtCreate;
    private String gmtModify;
    private int status;
    private String executeTime;
    private String executeDay;
    private String paraments;
    private String startTime;
    private String endTime;
    private String owner;
    private String type;

    public TaskInstance() {

    }

    public TaskInstance(int id, String name, String input, String output, String gmtCreate, String gmtModify, int status, String executeTime, String executeDay, String paraments, String owner) {
        this.id = id;
        this.name = name;
        this.input = input;
        this.output = output;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.status = status;
        this.executeTime = executeTime;
        this.executeDay = executeDay;
        this.paraments = paraments;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteDay() {
        return executeDay;
    }

    public void setExecuteDay(String executeDay) {
        this.executeDay = executeDay;
    }

    public String getParaments() {
        return paraments;
    }

    public void setParaments(String paraments) {
        this.paraments = paraments;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTme(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getShellName() {
        return shellName;
    }

    public void setShellName(String shellName) {
        this.shellName = shellName;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRealName() {
        //获取去除了时间后缀的task任务名
        if (this.name.substring(0, this.name.length() - 2).endsWith("_")) {
            return this.name.substring(0, this.name.length() - 3);
        } else {
            return this.name.substring(0, this.name.length() - 2);
        }

    }
}