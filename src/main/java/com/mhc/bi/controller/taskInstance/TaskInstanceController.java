package com.mhc.bi.controller.taskInstance;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskinstance.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ActionResult select(int pageSize, int pageNo, String date, String fileName, int[] status, String sortName, String sortType) {
        actionResult = new ActionResult();
        List<TaskInstance> taskInstanceList = taskInstanceService.selectAll();
        return actionResult;
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
}
