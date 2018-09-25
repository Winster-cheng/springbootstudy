package com.mhc.bi.domain;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description id(自增)
 */
public class JobPlan {
    private int id;
    private String name;
    private String shellName;
    private String input;
    private String executeRate;
    private String executeTime;
    private String output;
    private String paraments;
    private String gmtCreate;
    private String gmtModify;


    public JobPlan() {
        super();
    }

    public JobPlan(String name, String input,String shellName, String output,String executeRate, String executeTime,  String paraments, String gmtCreate) {
        this.name = name;
        this.shellName=name;
        this.input = input;
        this.executeRate = executeRate;
        this.executeTime = executeTime;
        this.output = output;
        this.paraments = paraments;
        this.gmtCreate = gmtCreate;
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

    public String getExecuteRate() {
        return executeRate;
    }

    public void setExecuteRate(String executeRate) {
        this.executeRate = executeRate;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getParaments() {
        return paraments;
    }

    public void setParaments(String paraments) {
        this.paraments = paraments;
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
