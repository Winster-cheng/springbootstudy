package com.mhc.bi.vo.taskinstance;

import com.mhc.bi.domain.theadvisor.TaskInstance;

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
    private List<Integer> input;
    private List<Integer> output;
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

    public void init(TaskInstance taskInstance, List<TaskInstance> parentList, List<TaskInstance> childrenList) {
        if (parentList.size() != 0)
            this.hasParent = true;
        else
            this.hasParent = false;

        if (childrenList.size() != 0)
            this.hasChildren = true;
        else
            this.hasChildren = false;
        this.output = new ArrayList<>();
        this.input = new ArrayList<>();
        for (TaskInstance t1 : parentList) {
            this.input.add(t1.getId());
        }

        for (TaskInstance t2 : childrenList) {
            this.output.add(t2.getId());
        }
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());

    }

    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsParentNode(TaskInstance taskInstance, boolean hasParent, boolean hasChildren, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.output = new ArrayList();
        this.output.add(centerId);
        this.input = new ArrayList<>();
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());
    }


    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsChildNode(TaskInstance taskInstance, boolean hasParent, boolean hasChildren, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.output = new ArrayList();
        this.input = new ArrayList<>();
        this.input.add(centerId);
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        this.status = Status.getStatus(taskInstance.getStatus());
    }
}
