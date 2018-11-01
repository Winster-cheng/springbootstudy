package com.mhc.bi.vo.taskinstance;

import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class TaskInstanceDependency {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildren;
    private List<Integer> parentList;
    private List<Integer> childrenList;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
}
