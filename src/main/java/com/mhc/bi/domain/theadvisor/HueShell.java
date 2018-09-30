package com.mhc.bi.domain.theadvisor;

import org.springframework.stereotype.Service;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description 对应hue中的desktop_document2，相对于JsonPlan+ShellContent
 */
@Service
public class HueShell {
    private int id;
    private String name;
    private String type;
    private String input;
    private String output;
    private String gmtCreate;
    private String gmtModify;
    private String executeTime;
    private String executeRate;
    private String shellName;
    private String shellContent;
    private String paraments;
    private int isHistory;
    public HueShell() {
        super();
    }

    public HueShell(int id, String name, String type, String input,String output,String paraments ,String gmtCreate, String gmtModify, String executeTime, String executeRate, String shellName, String shellContent,int isHistory) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.input = input;
        this.output=output;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
        this.executeTime = executeTime;
        this.executeRate = executeRate;
        this.shellName = shellName;
        this.shellContent = shellContent;
        this.paraments=paraments;
        this.isHistory=isHistory;
    }

    public String getParaments() {
        return paraments;
    }

    public void setParaments(String paraments) {
        this.paraments = paraments;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getExecuteRate() {
        return executeRate;
    }

    public void setExecuteRate(String executeRate) {
        this.executeRate = executeRate;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
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

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
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

    public int getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(int isHistory) {
        this.isHistory = isHistory;
    }
}