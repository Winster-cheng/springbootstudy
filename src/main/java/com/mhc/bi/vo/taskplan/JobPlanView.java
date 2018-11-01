package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/30
 * @description 这个类用来提供给 2.1
 */
public class JobPlanView {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildre;
    private List<Integer> parentList;
    private List<Integer> childrenList;
    private boolean isTop;
    private List<JobPlanView> list;

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

    public boolean isHasChildre() {
        return hasChildre;
    }

    public void setHasChildre(boolean hasChildre) {
        this.hasChildre = hasChildre;
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

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public List<JobPlanView> getList() {
        return list;
    }

    public void setList(List<JobPlanView> list) {
        this.list = list;
    }

    public void initByJobPlan(JobPlan jobPlan) {
        this.name=jobPlan.getName();
        this.id=jobPlan.getId();
//        this
    }
}
