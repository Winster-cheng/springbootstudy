package com.mhc.bi.vo.taskinstance;

import com.mhc.bi.domain.theadvisor.TaskInstance;

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

    public void initByTaskInstance(TaskInstance taskInstance, List<Integer> parentList, List<Integer> childrenList) {
        this.id = taskInstance.getId();
        this.name = taskInstance.getName();
        if (parentList.size() != 0) {
            this.hasParent = true;
        }
        if (childrenList.size() != 0) {
            this.hasChildren = true;
        }
        this.status = Status.getStatus(taskInstance.getStatus());
    }
}
