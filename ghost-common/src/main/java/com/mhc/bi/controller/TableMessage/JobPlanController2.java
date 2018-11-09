package com.mhc.bi.controller.TableMessage;

import com.mhc.bi.Utils.JsonData;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.service.JobPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyan
 * @date 2018/09/14
 * @description
 */
@RestController
@RequestMapping("/jobplan")
public class JobPlanController2 {
    @Autowired
    private JobPlanService jobPlanService;

    @GetMapping("insert")
    public Object insertShellContent() {
        JobPlan jobPlan = new JobPlan();
        jobPlan.setName("show databases");
        jobPlan.setExecuteTime("0,1,2,3,4,5,6");
        return JsonData.buildSuccess(jobPlanService.insert(jobPlan));
    }

    @GetMapping("selectall")
    public Object selectAllFromJobPlan() {
        return jobPlanService.selectAll();
    }

    @GetMapping("update")
    public Object updateShellContent() {
        JobPlan jobPlan = new JobPlan();
        jobPlan.setName("show databases");
        jobPlan.setExecuteTime("这里是更新时间的测试");
        return "更新了" + jobPlanService.update(jobPlan)+ "条数据";
    }

    @GetMapping("delete")
    public Object delete() {
        JobPlan jobPlan=new JobPlan();
        jobPlan.setName("show databases");
        return jobPlanService.delete(jobPlan);
    }


}
