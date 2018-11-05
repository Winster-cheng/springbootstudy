package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

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

    public void init(JobPlan jobPlan, List<Integer> parentList, List<Integer> childrenList) {
        this.name = jobPlan.getName();
        this.id = jobPlan.getId();
        if (parentList.size() != 0) {
            this.hasParent = true;
        }
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        }
    }

}
