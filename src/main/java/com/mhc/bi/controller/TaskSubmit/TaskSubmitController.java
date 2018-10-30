package com.mhc.bi.controller.TaskSubmit;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.service.DesktopDocument2Service;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.vo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyan
 * @date 2018/10/22
 * @description 任务提交界面相关接口
 */
@RestController
@RequestMapping("/submit")
public class TaskSubmitController {
    @Autowired
    HueShellService hueShellService;

    @Autowired
    DesktopDocument2Service desktopDocument2Service;


    ActionResult actionResult;

    @PostMapping("/getDirectory")
    public ActionResult getDirectory() {
        actionResult = new ActionResult();
        try {
            actionResult.success();
            actionResult.setList(desktopDocument2Service.getDirectory());
        } catch (Exception e) {
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getContent")
    public ActionResult getContent(int fileId) {
        actionResult = new ActionResult();
        File file = desktopDocument2Service.getContent(fileId);
        try {
            actionResult.success();
            actionResult.setDataValue(file);
        } catch (Exception e) {
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/save")
    public ActionResult save(int fileId, String content) {
        actionResult = new ActionResult();
        try {
            boolean flag = desktopDocument2Service.save(fileId, content);
            if (flag) actionResult.success();
            else actionResult.fail();
        } catch (Exception e) {
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/submit")
    public ActionResult submit(int fileId) {
        actionResult = new ActionResult();
        try {
            boolean flag = hueShellService.submit(fileId);
            if (flag) actionResult.success();
            else
                actionResult.fail();
        } catch (Exception e) {
            actionResult.fail();
        }
        return actionResult;
    }
}
