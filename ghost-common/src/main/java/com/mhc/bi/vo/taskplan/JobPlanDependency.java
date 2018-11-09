package com.mhc.bi.vo.taskplan;

import com.mhc.bi.domain.theadvisor.JobPlan;

import java.util.ArrayList;
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
    List<Integer> input;
    List<Integer> output;

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

    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsParentNode(JobPlan jobPlan, boolean hasChildren, boolean hasParent, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.output = new ArrayList();
        this.output.add(centerId);
        this.input = new ArrayList<>();
        this.id = jobPlan.getId();
        this.name = jobPlan.getName();

    }


    //如果这个节点是父节点，那么我们就把他的父节点列表设置为空，子节点列表写死
    public void initAsChildNode(JobPlan jobPlan, boolean hasParent, boolean hasChildren, int centerId) {
        this.hasParent = hasParent;
        this.hasChildren = hasChildren;
        this.output = new ArrayList();
        this.input = new ArrayList<>();
        input.add(centerId);
        this.id = jobPlan.getId();
        this.name = jobPlan.getName();

    }

    public void init(JobPlan jobPlan, List<Integer> parentList, List<Integer> childrenList) {
        if (parentList.size() != 0)
            this.hasParent = true;
        else
            this.hasParent = false;

        if (childrenList.size() != 0)
            this.hasChildren = true;
        else
            this.hasChildren = false;

        this.output = childrenList;
        this.input = parentList;
        this.id = jobPlan.getId();
        this.name = jobPlan.getName();

    }
}
