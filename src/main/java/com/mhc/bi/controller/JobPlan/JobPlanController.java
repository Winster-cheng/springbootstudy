package com.mhc.bi.controller.JobPlan;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.common.AddParm;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskplan.JobPlanView;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/29
 * @description
 */
@RestController
@RequestMapping("/taskPlan")
public class JobPlanController {
    @Autowired
    JobPlanService jobPlanService;

    ActionResult actionResult;


    @PostMapping("/select")
    public ActionResult select(@RequestBody TaskPlanSelect taskPlanSelect) {
        actionResult = new ActionResult();
        try {
            int totalCount;
            if (taskPlanSelect.getFileName() == "") {
                totalCount = jobPlanService.getNumbers();
            } else {
                totalCount = jobPlanService.getNumbersByName(taskPlanSelect.getFileName());
            }
            PageMessage pageMessage = new PageMessage();
            JobPlanView jobPlanView;
            List<JobPlanView> jobPlanViewList = new ArrayList<JobPlanView>();
            List<JobPlan> jobPlanList = jobPlanService.selectJobPlanListByPage(taskPlanSelect.getPageSize(), taskPlanSelect.getPageNo(), taskPlanSelect.getFileName(), taskPlanSelect.getTimeSortType());
            for (JobPlan jobPlan : jobPlanList) {
                jobPlanView = new JobPlanView();
                jobPlanView.initByJobPlan(jobPlan);
                jobPlanViewList.add(jobPlanView);
            }

            pageMessage.setList(jobPlanViewList);
            pageMessage.setPageNo(taskPlanSelect.getPageNo());
            pageMessage.setPageSize(taskPlanSelect.getPageSize());
            pageMessage.setTotalPage(totalCount % taskPlanSelect.getPageSize() == 0 ? totalCount / taskPlanSelect.getPageSize() : totalCount / taskPlanSelect.getPageSize() + 1);
            pageMessage.setTotalCount(totalCount);

            actionResult.setDataValue(pageMessage);
            actionResult.success();
        } catch (Exception e) {
            actionResult.fail();
            e.printStackTrace();
        }
        return actionResult;
    }

    @PostMapping("/getDependencies")
    public ActionResult getDependencies(int jobPlanId) {
        actionResult = new ActionResult();

        return actionResult;
    }

}