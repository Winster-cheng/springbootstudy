package com.mhc.bi.controller;

import com.mhc.bi.Utils.JsonData;
import com.mhc.bi.domain.theadvisor.ShellContent;
import com.mhc.bi.service.ShellContentService;
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
@RequestMapping("/shellcontent")
public class ShellContentController {
    @Autowired
    private ShellContentService shellContentService;

    @GetMapping("insert")
    public Object insertShellContent() {
        ShellContent shellContent = new ShellContent();
        shellContent.setShellContent("show databases");
        shellContent.setShellName("show_databases");
        shellContent.setShellType("hive");
        int id = shellContentService.insertShellContent(shellContent);
        return JsonData.buildSuccess(id);
    }

    @GetMapping("selectall")
    public Object selectAllFromShellContent() {
        return shellContentService.selectShellContent();
    }

    @GetMapping("update")
    public Object updateShellContent() {
        ShellContent shellContent = new ShellContent();
        shellContent.setShellContent("update_shell_content");
        shellContent.setShellName("show_databases");
        shellContent.setShellType("mysql");

        return "更新了" + shellContentService.updateShellContent(shellContent) + "条数据";
    }

    @GetMapping("delete")
    public Object delete() {
        ShellContent shellContent = new ShellContent();
        shellContent.setShellName("show_databases");
        return shellContentService.deleteShellContent(shellContent);
    }

    @GetMapping("selectbyname")
    public Object selectstartnode(String name) {
        return shellContentService.selectByName(name);
    }
}
