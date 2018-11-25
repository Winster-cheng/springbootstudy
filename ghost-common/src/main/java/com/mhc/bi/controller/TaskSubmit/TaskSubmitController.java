package com.mhc.bi.controller.TaskSubmit;

import com.mhc.bi.Utils.StringHandle;
import com.mhc.bi.common.ActionResult;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitGetContent;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitSave;
import com.mhc.bi.controller.TaskSubmit.BeamForm.TaskSubmitSubmit;
import com.mhc.bi.domain.hue.DesktopDocument2;
import com.mhc.bi.domain.theadvisor.HueShell;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.domain.theadvisor.ShellContent;
import com.mhc.bi.service.DesktopDocument2Service;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.vo.tasksubmit.File;
import com.mhc.bi.vo.tasksubmit.FileContent;
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
    ShellContentService shellContentService;

    @Autowired
    DesktopDocument2Service desktopDocument2Service;

    @Autowired
    JobPlanService jobPlanService;

    ActionResult actionResult;

    //输入一个无序的file列表，返回1.2.文件/文件夹显示需要的结构（输入的File中children是空的）
    public List<File> FileConstructDeal(List<File> fileList) {
        List<File> parentFileList = new ArrayList<>();//根目录
        for (File file : fileList) {
            if (file.getParent() == 1) {
                parentFileList.add(file);
            }
        }
        fileList.removeAll(parentFileList);
        for (File f : parentFileList) { //从根目录开始设置
            this.fileSetChildren(f, fileList);
        }
        return parentFileList;
    }

    //输入parentFile和FileList,根据parentFile每个file的childrenList中的id,获取fileList中对应的file填入parentFile.children
    public File fileSetChildren(File parentFile, List<File> fileList) {
        parentFile.setChildren(new ArrayList<>());
        if (parentFile.getChildrenId().isEmpty()) return parentFile;
        for (int childId : parentFile.getChildrenId()) {
            for (File file : fileList) { //找到childId对应的File
                if (file.getId() == childId) {//找到childId对应的File
                    if (file.getChildrenId().isEmpty())
                        parentFile.getChildren().add(file);
                    else fileSetChildren(file, fileList);
                }
            }
        }
        return parentFile;
    }

    @PostMapping("/getDirectory")
    public ActionResult getDirectory() {
        actionResult = new ActionResult();
        try {
            List<File> fileList = new ArrayList<>();
            File file;
            List<DesktopDocument2> aliveDesktopDocument2 = desktopDocument2Service.getAllAlive();
            for (DesktopDocument2 desktopDocument2 : aliveDesktopDocument2) {
                file = new File();
                file.initByDesktopDocument2(desktopDocument2, desktopDocument2Service);
                fileList.add(file);
            }

            actionResult.setList(this.FileConstructDeal(fileList));
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
        String content = desktopDocument2Service.getContent(taskSubmitGetContent.getFileId());
        fileContent.setFileContent(content);
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

    //提交的条件 1.父节点的最早执行时间不比子节点的最早执行时间晚 2.只能提交.bi文件 3.
    @PostMapping("/submit")
    public ActionResult submit(@RequestBody TaskSubmitSubmit taskSubmitSubmit) {
        String msg;
        String name;
        actionResult = new ActionResult();
        try {
            String beforeMessage = desktopDocument2Service.getContent(taskSubmitSubmit.getFileId());
            String content = taskSubmitSubmit.getContent();
            if (!(content.equals(beforeMessage))) {
                actionResult.fail("提交错误，请先保存");
                return actionResult;
            }
            name = desktopDocument2Service.getNameById(taskSubmitSubmit.getFileId());
            if (!name.endsWith(".bi")) {
                actionResult.fail("提交错误，只能提交.bi文件");
                return actionResult;
            }
            //TODO 不应该在selectByName里面进行筛选
            HueShell hueShell = hueShellService.selectByName(StringHandle.checkEnding(name, "bi"));
            hueShellService.insertOrAddNewVersion(hueShell);//备份到hueshell表;
            msg = hueShellService.check(hueShell);
            if (!msg.equals("OK")) { //检查子节点的时间有没有比父节点早
                actionResult.fail(msg);
            }
            JobPlan jobPlan = jobPlanService.getJobPlanFromHueShell(hueShell);
            ShellContent shellContent = shellContentService.getShellContentFromHueShell(hueShell);
            //如果JobPlan已经存在，那么更新
            if (jobPlanService.isExit(name)) {
                jobPlanService.update(jobPlan);
                shellContentService.updateShellContent(shellContent);
            } else {
                jobPlanService.insert(jobPlan);
                shellContentService.insertShellContent(shellContent);
            }
            actionResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail("未知错误，请查看后台日志");
        }
        return actionResult;
    }
}
