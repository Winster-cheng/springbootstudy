package com.mhc.bi.task;

import com.mhc.bi.domain.TaskInstance;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.TaskInstanceService;
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

    TaskInstance taskInstance;

    @Scheduled(cron = "0 30 23 ? * *")
//@Scheduled(cron="0*/1 * * * * *") //每秒执行
    public void createTaskInstance(){
        taskInstanceService.createTaskInstance();
    }

}
