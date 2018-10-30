package com.mhc.bi.controller.taskInstance;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.service.TaskInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;

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
    public ActionResult select(int pageSize, int pageNo, String date, String fileName, int status, int timingSortType, int bussinessDateSortType, int startTimeSortType) {
        actionResult = new ActionResult();

        return  actionResult;
    }
}
