package com.mhc.bi.controller.TaskSubmit;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitGetContent;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitSave;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitSubmit;
import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.service.DesktopDocument2Service;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.vo.File;
import com.mhc.bi.vo.FileContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/22
 * @description 任务提交界面相关接口
 */
@RestController
@RequestMapping("/taskSubmit")
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
            List<File> fileList = new ArrayList<>();
            File file;
            for (DesktopDocument2 desktopDocument2 : desktopDocument2Service.getAllAlive()) {
                file = new File();
                file.initByDesktopDocument2(desktopDocument2,desktopDocument2Service);
                fileList.add(file);
            }
            actionResult.setList(fileList);
            actionResult.success();

        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getContent")
    public ActionResult getContent(@RequestBody TaskSubmitGetContent taskSubmitGetContent) {
        actionResult = new ActionResult();
        FileContent fileContent = new FileContent();
        fileContent.setFileContent(desktopDocument2Service.getContent(taskSubmitGetContent.getFileId()));
        try {
            actionResult.success();
            actionResult.setDataValue(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/save")
    public ActionResult save(@RequestBody TaskSubmitSave taskSubmitSave) {
        actionResult = new ActionResult();
        try {
            boolean flag = desktopDocument2Service.save(taskSubmitSave.getFileId(), taskSubmitSave.getContent());
            if (flag) actionResult.success();
            else actionResult.fail();
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/submit")
    public ActionResult submit(@RequestBody TaskSubmitSubmit taskSubmitSubmit) {
        actionResult = new ActionResult();
        try {
            boolean flag = hueShellService.submit(taskSubmitSubmit.getFileId());
            if (flag) actionResult.success();
            else
                actionResult.fail();
        } catch (Exception e) {
            actionResult.fail();
        }
        return actionResult;
    }
}
