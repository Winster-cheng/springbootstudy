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
        return this.input;
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

    public void initAsParent(JobPlan jobPlan, List<Integer> parentList, List<Integer> childrenList) {
        boolean hasParent = false;
        boolean hasChildren = false;
        if (parentList.size() != 0) hasParent = true;
        if (childrenList.size() != 0) hasChildren = true;

        this.input = new ArrayList<>();
        this.output = childrenList;
        this.name = jobPlan.getName();
        this.id = jobPlan.getId();
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
    }

    public void initAsChildren(JobPlan jobPlan, List<Integer> parentList, List<Integer> childrenList) {
        boolean hasParent = false;
        boolean hasChildren = false;
        if (parentList.size() != 0) hasParent = true;
        if (childrenList.size() != 0) hasChildren = true;

        this.input = parentList;
        this.output = new ArrayList<>();
        this.name = jobPlan.getName();
        this.id = jobPlan.getId();
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
    }
}
