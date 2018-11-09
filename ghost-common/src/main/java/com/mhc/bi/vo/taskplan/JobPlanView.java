package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

/**
 * @author baiyan
 * @date 2018/10/30
 * @description 这个类用来提供给 2.2
 */
public class JobPlanView {
    private int id;
    private String name;
    private String gmtModify;
    private String owner;
    private String executeRate;
    private String type;

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


    public String getExecuteRate() {
        return executeRate;
    }

    public void setExecuteRate(String executeRate) {
        this.executeRate = executeRate;
    }

    public String getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void initByJobPlan(JobPlan jobPlan) {
        this.id = jobPlan.getId();
        this.name = jobPlan.getName();
        this.gmtModify = jobPlan.getGmtModify();
        this.owner = jobPlan.getOwner();
        this.executeRate = jobPlan.getExecuteRate();
        this.type = "hiveshell";
    }


}
