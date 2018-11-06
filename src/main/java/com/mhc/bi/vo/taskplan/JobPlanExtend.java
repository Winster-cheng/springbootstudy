package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class JobPlanExtend {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildren;
    private List<Integer> input;
    private List<Integer> output;

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

    public boolean isHasParent() {
        return hasParent;
    }

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public List<Integer> getInput() {
        return input;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }

    public List<Integer> getOutput() {
        return output;
    }

    public void setOutput(List<Integer> output) {
        this.output = output;
    }

    public void initAsParent(JobPlan jobPlan, List<JobPlan> parentList, List<JobPlan> childrenList) {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        this.name = jobPlan.getName();
        this.id = jobPlan.getId();
        if (parentList.size() != 0) {
            this.hasParent = true;
        } else this.hasParent = false;
        for (JobPlan jobPlan1 : parentList) {
            input.add(jobPlan1.getId());
        }
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        } else this.hasChildren = false;

    }

    public void initAsChildren(JobPlan jobPlan, List<JobPlan> parentList, List<JobPlan> childrenList) {
        this.input = new ArrayList<>();
        this.output = new ArrayList<>();
        this.name = jobPlan.getName();
        this.id = jobPlan.getId();
        if (parentList.size() != 0) {
            this.hasParent = true;
        } else this.hasParent = false;
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        } else this.hasChildren = false;
        for (JobPlan jobPlan1 : childrenList) {
            this.output.add(jobPlan1.getId());
        }
    }
}
