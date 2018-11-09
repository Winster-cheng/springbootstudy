package com.mhc.bi.vo.taskinstance;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.TaskInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/31
 * @description
 */
public class TaskInstanceView {
    private String name;
    private Status status;
    private String owner;
    private String executeTime;
    private String bussinessTime;
    private String startTime;
    private Integer id;
    private String type;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getBussinessTime() {
        return bussinessTime;
    }

    public void setBussinessTime(String bussinessTime) {
        this.bussinessTime = bussinessTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TaskInstanceView> getTaskInstanceViewListByTaskInstanceList(List<TaskInstance> taskInstanceList) {
        List<TaskInstanceView> taskInstanceViewList = new ArrayList<TaskInstanceView>();
        TaskInstanceView taskInstanceView;
        for (TaskInstance taskInstance : taskInstanceList) {
            taskInstanceView = new TaskInstanceView();
            taskInstanceView.initByTaskInstance(taskInstance);
            taskInstanceViewList.add(taskInstanceView);
        }
        return taskInstanceViewList;
    }

    //转换器，把taskInstance转化成taskInstanceView
    public TaskInstanceView initByTaskInstance(TaskInstance taskInstance) {

        this.setStatus(Status.getStatus(taskInstance.getStatus()));
        this.setName(taskInstance.getName());
        this.setOwner(taskInstance.getOwner());
        String executeTime = taskInstance.getExecuteTime();
        if (executeTime.length() == 1) {
            this.setExecuteTime(taskInstance.getExecuteDay() + ":0" + taskInstance.getExecuteTime()+":00:00");
        } else this.setExecuteTime(taskInstance.getExecuteDay() + ":" + taskInstance.getExecuteTime()+":00:00");

//TODO 业务时间目前默认executeTime-1
        this.setBussinessTime(GetTime.getTimeWithCount(taskInstance.getExecuteDay(), "yyyyMMdd", -1));
        this.setStartTime(taskInstance.getStartTime());
        this.setId(taskInstance.getId());
        this.setType(taskInstance.getType());
        return this;
    }
}

