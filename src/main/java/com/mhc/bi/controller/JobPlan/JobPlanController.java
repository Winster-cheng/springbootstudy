package com.mhc.bi.controller.JobPlan;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.common.ActionResult2;
import com.mhc.bi.controller.JobPlan.FormBean.TaskPlanGetDependencies;
import com.mhc.bi.controller.JobPlan.FormBean.TaskPlanGetMoreDependencies;
import com.mhc.bi.controller.JobPlan.FormBean.TaskPlanSelect;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskplan.JobPlanDependency;
import com.mhc.bi.vo.taskplan.JobPlanExtend;
import com.mhc.bi.vo.taskplan.JobPlanView;
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
            System.out.println(taskPlanSelect.getTimeSortType());
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
    public ActionResult getDependencies(@RequestBody TaskPlanGetDependencies taskPlanGetDependencies) {
        actionResult = new ActionResult();
        try {
            int centerId = taskPlanGetDependencies.getJobPlanId();
            JobPlanDependency jobPlanDependency;
            List<JobPlanDependency> jobPlanDependencyList = new ArrayList<JobPlanDependency>();
            List<JobPlan> jobPlanParentList = jobPlanService.getParentList(centerId);
            //给父节点生成VO类
            for (JobPlan jobPlanParent : jobPlanParentList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                jobPlanDependency = new JobPlanDependency();
                if (jobPlanService.getChildrenIdByJobPlan(jobPlanParent).size() != 0) hasParent = true;
                if (jobPlanService.getParentIdByJobPlan(jobPlanParent).size() != 0) hasChildren = true;
                jobPlanDependency.initAsParentNode(jobPlanParent, hasParent, hasChildren, centerId);
                jobPlanDependencyList.add(jobPlanDependency);
            }
            //给子节点生成VO类
            List<JobPlan> jobPlanChildrenList = jobPlanService.getChildrenList(centerId);
            for (JobPlan jobPlanchild : jobPlanChildrenList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                jobPlanDependency = new JobPlanDependency();
                if (jobPlanService.getParentIdByJobPlan(jobPlanchild).size() != 0) hasParent = true;
                if (jobPlanService.getChildrenIdByJobPlan(jobPlanchild).size() != 0) hasChildren = true;
                jobPlanDependency.initAsChildNode(jobPlanchild, hasParent, hasChildren, centerId);
                jobPlanDependencyList.add(jobPlanDependency);
            }

            //给中心节点生产VO类
            jobPlanDependency = new JobPlanDependency();
            JobPlan centerJobPlan = jobPlanService.getJobPlanById(centerId);
            List<Integer> parentList = jobPlanService.getParentIdById(centerId);
            List<Integer> childrenList = jobPlanService.getChildrenIdById(centerId);
            jobPlanDependency.init(centerJobPlan, parentList, childrenList);
            jobPlanDependencyList.add(jobPlanDependency);

            actionResult.success();
            actionResult.setList(jobPlanDependencyList);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getMoreDependencies")
    public ActionResult2 getMoreDependencies(@RequestBody TaskPlanGetMoreDependencies taskPlanGetMoreDependencies) {
        JobPlanExtend jobPlanExtend;

        ActionResult2 actionResult = new ActionResult2();
        try {
            int jobPlanId = taskPlanGetMoreDependencies.getJobPlanId();
            List<JobPlan> jobPlanList;
            boolean isTop = taskPlanGetMoreDependencies.getIsTop();
            JobPlan jobPlan = jobPlanService.getJobPlanById(jobPlanId);

            List<JobPlanExtend> jobPlanExtendList = new ArrayList<>();
            if (isTop) {//获取中心节点父节点列表->遍历列表生成jobPlanExtend对象并且插入jobPlanExtend列表
                jobPlanList = jobPlanService.getParentList(jobPlanId);
                for (JobPlan jobPlan1 : jobPlanList) {
                    jobPlanExtend = new JobPlanExtend();
                    List<Integer> jobPlanParentsList = jobPlanService.getParentIdByJobPlan(jobPlan1);
                    List<Integer> jobPlanChildrenList = jobPlanService.getChildrenIdByJobPlan(jobPlan1);
                    jobPlanExtend.initAsParent(jobPlan1, jobPlanParentsList, jobPlanChildrenList);
                    jobPlanExtendList.add(jobPlanExtend);
                }
            } else {//获取中心节点子节点列表->遍历列表生成jobPlanExtend对象并且插入jobPlanExtend列表
                jobPlanList = jobPlanService.getChildrenList(jobPlanId);
                for (JobPlan jobPlan1 : jobPlanList) {
                    jobPlanExtend = new JobPlanExtend();
                    List<Integer> jobPlanParentsList = jobPlanService.getParentIdByJobPlan(jobPlan1);
                    List<Integer> jobPlanChildrenList = jobPlanService.getChildrenIdByJobPlan(jobPlan1);
                    jobPlanExtend.initAsChildren(jobPlan1, jobPlanParentsList, jobPlanChildrenList);
                    jobPlanExtendList.add(jobPlanExtend);
                }
            }

            actionResult.setList(jobPlanExtendList);
            actionResult.setIsTop(isTop);
            actionResult.setDataValue(jobPlanId);
            actionResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }
}