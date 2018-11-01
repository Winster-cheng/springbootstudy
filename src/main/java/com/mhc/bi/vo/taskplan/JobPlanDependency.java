package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description 2.2接口使用 点击任务名返回依赖图
 */
public class JobPlanDependency {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildren;
    List<Integer> parentList;
    List<Integer> childrenList;

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

    public List<Integer> getParentList() {
        return parentList;
    }

    public void setParentList(List<Integer> parentList) {
        this.parentList = parentList;
    }

    public List<Integer> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Integer> childrenList) {
        this.childrenList = childrenList;
    }

    public void init(JobPlan jobPlan, List<Integer> parentList, boolean hasParent, List<Integer> childrenList, boolean hasChildren) {
        this.childrenList = childrenList;
        this.parentList = parentList;
        this.id = jobPlan.getId();
        this.name = jobPlan.getName();
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
    }
}
