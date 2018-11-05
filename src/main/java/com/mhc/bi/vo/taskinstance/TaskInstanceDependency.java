package com.mhc.bi.vo.taskinstance;

import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import javafx.concurrent.Task;

import java.util.ArrayList;
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


    public void init(TaskInstance taskInstance, List<TaskInstance> parentList, List<TaskInstance> childrenList) {
        if (parentList.size() != 0)
            this.hasParent = true;
        else
            this.hasParent = false;

        if (childrenList.size() != 0)
            this.hasChildren = true;
        else
            this.hasChildren = false;
        this.childrenList = new ArrayList<>();
        this.parentList = new ArrayList<>();
        for (TaskInstance t1 : parentList) {
            this.parentList.add(t1.getId());
        }

        for (TaskInstance t2 : childrenList) {
            this.childrenList.add(t2.getId());
        }
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());

    }

    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsParentNode(TaskInstance taskInstance, boolean hasChildren, boolean hasParent, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.childrenList = new ArrayList();
        this.childrenList.add(centerId);
        this.parentList = new ArrayList<>();
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());
    }


    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsChildNode(TaskInstance taskInstance, boolean hasChildren, boolean hasParent, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.childrenList = new ArrayList();
        this.parentList = new ArrayList<>();
        this.parentList.add(centerId);
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());
    }
}
