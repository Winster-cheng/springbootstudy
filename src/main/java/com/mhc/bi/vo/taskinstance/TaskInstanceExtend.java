package com.mhc.bi.vo.taskinstance;

import com.mhc.bi.domain.theadvisor.TaskInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class TaskInstanceExtend {
    private int id;
    private String name;
    private boolean hasParent;
    private boolean hasChildren;
    private Status status;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void initAsParent(TaskInstance taskInstance, List<Integer> parentList, List<Integer> childrenList) {
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        if (parentList.size() != 0) {
            this.hasParent = true;
        }
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        }
        this.output = childrenList;
        this.input = new ArrayList<>();
        this.status=Status.getStatus(taskInstance.getStatus());
    }

    public void initAsChild(TaskInstance taskInstance, List<Integer> parentList, List<Integer> childrenList) {
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        if (parentList.size() != 0) {
            this.hasParent = true;
        }
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        }
        this.input = parentList;
        this.output = new ArrayList<>();
        this.status=Status.getStatus(taskInstance.getStatus());
    }
}
