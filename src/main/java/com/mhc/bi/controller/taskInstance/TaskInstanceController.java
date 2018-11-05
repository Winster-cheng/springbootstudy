package com.mhc.bi.controller.taskInstance;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.controller.JobPlan.TaskPlanGetDependencies;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceGetDependency;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceSelect;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskinstance.Status;
import com.mhc.bi.vo.taskinstance.TaskInstanceDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/30
 * @description
 */
@RestController
@RequestMapping("/taskInstance")
public class TaskInstanceController {
    @Autowired
    TaskInstanceService taskInstanceService;

    ActionResult actionResult;

    @PostMapping("/select")
    public ActionResult select(@RequestBody TaskInstanceSelect taskInstanceSelect) {
        int pageSize = taskInstanceSelect.getPageSize();
        int pageNo = taskInstanceSelect.getPageNo();
        String date = taskInstanceSelect.getDate();
        String fileName = taskInstanceSelect.getFileName();
        int[] status = taskInstanceSelect.getStatus();
        String sortName = taskInstanceSelect.getSortName();
        Integer sortType = taskInstanceSelect.getSortType();
        int totalCount;

        if (date != null) {

        } else if (fileName != null) {

        } else if (status.length != 0) {

        }

        actionResult = new ActionResult();
        List<TaskInstance> taskInstanceList = taskInstanceService.selectAll();
        return actionResult;
    }

    //select方法的orderBy条件
    public String selectSortNameSQL(String sortName, int sortType) {
        if (sortType == 0)
            return "";
        else if (sortType == 1)
            return "order by " + sortName + " asc";
        else return "order by " + sortName + " desc";
    }

    //目前的Status先不接入comman包中的status
    @PostMapping("/getStatus")
    public ActionResult getStatus() {
        try {
            List<Status> list = new ArrayList<Status>();
            for (int i = 0; i < 6; i++) {
                list.add(Status.getStatus(i));
            }
            actionResult = new ActionResult();
            actionResult.success();
            actionResult.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getDependencies")
    public ActionResult getDependencies(@RequestBody TaskInstanceGetDependency taskInstanceGetDependency) {
        ActionResult actionResult = new ActionResult();
        try {
            int centerId = taskInstanceGetDependency.getTaskInstanceId();
            TaskInstanceDependency taskInstanceDependency;
            List<TaskInstanceDependency> taskInstanceDependencies = new ArrayList<TaskInstanceDependency>();
            List<TaskInstance> taskInstanceChildrenList = taskInstanceService.getChildrenListById(centerId);
            List<TaskInstance> taskInstanceParentList = taskInstanceService.getParentListById(centerId);

            //给父节点生成VO类
            for (TaskInstance taskInstance : taskInstanceParentList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                taskInstanceDependency = new TaskInstanceDependency();
                if (taskInstanceService.getChildrenListById(taskInstance.getId()).size() != 0) hasParent = true;
                if (taskInstanceService.getParentListById(taskInstance.getId()).size() != 0) hasChildren = true;
                taskInstanceDependency.initAsParentNode(taskInstance, hasParent, hasChildren, centerId);
                taskInstanceDependencies.add(taskInstanceDependency);
            }
            //给子节点生成VO类
            for (TaskInstance taskInstance : taskInstanceChildrenList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                taskInstanceDependency = new TaskInstanceDependency();
                if (taskInstanceService.getParentListById(taskInstance.getId()).size() != 0) hasParent = true;
                if (taskInstanceService.getChildrenListById(taskInstance.getId()).size() != 0) hasChildren = true;
                taskInstanceDependency.initAsChildNode(taskInstance, hasParent, hasChildren, centerId);
                taskInstanceDependencies.add(taskInstanceDependency);
            }

            //给中心节点生产VO类
            taskInstanceDependency = new TaskInstanceDependency();
            taskInstanceDependency.init(taskInstanceService.selectTaskInstanceById(centerId), taskInstanceService.getParentListById(centerId), taskInstanceService.getChildrenListById(centerId));
            taskInstanceDependencies.add(taskInstanceDependency);
            actionResult.success();
            actionResult.setList(taskInstanceDependencies);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }
}
