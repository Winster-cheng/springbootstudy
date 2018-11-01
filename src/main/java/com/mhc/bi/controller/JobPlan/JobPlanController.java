package com.mhc.bi.controller.JobPlan;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskplan.JobPlanView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ActionResult select(Integer pageSize, Integer pageNo, String fileName, Integer timeSortType) {
        actionResult = new ActionResult();
        try {
            int totalCount;
            if (fileName == "") {
                totalCount = jobPlanService.getNumbers();
            } else {
                totalCount = jobPlanService.getNumbersByName(fileName);
            }
            PageMessage pageMessage = new PageMessage();
            JobPlanView jobPlanView;
            List<JobPlanView> jobPlanViewList = new ArrayList<JobPlanView>();
            List<JobPlan> jobPlanList = jobPlanService.selectJobPlanListByPage(pageSize, pageNo, fileName, timeSortType);
            for (JobPlan jobPlan : jobPlanList) {
                jobPlanView = new JobPlanView();
                jobPlanView.initByJobPlan(jobPlan);
                jobPlanViewList.add(jobPlanView);
            }

            pageMessage.setList(jobPlanViewList);
            pageMessage.setPageNo(pageNo);
            pageMessage.setPageSize(pageSize);
            pageMessage.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
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