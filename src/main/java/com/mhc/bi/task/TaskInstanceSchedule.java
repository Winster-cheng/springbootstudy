package com.mhc.bi.task;

import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.exec.FlowControl;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.service.alert.DingDingAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description 作业计划每天11：30生成任务实例
 */
@Component
public class TaskInstanceSchedule {
    @Autowired
    private JobPlanService jobPlanService;

    @Autowired
    private TaskInstanceService taskInstanceService;

    @Autowired
    private FlowControl flowControl;

    @Autowired
    DingDingAlert dingDingAlert;
    TaskInstance taskInstance;

    //@Scheduled(cron="0*/1 * * * * *") //每秒执行
    @Scheduled(cron = "0 30 23 ? * *")
    public void createTaskInstance() {
        dingDingAlert.sendMsg("开始生成任务实例");
        taskInstanceService.createTaskInstance();
    }

    @Scheduled(cron = "0 01 00 ? * *")
    public void start() {
        dingDingAlert.sendMsg("开始执行任务");
        flowControl.start();
    }


}
