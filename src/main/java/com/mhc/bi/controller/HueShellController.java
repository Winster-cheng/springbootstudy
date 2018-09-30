package com.mhc.bi.controller;

import com.mhc.bi.domain.theadvisor.HueShell;
import com.mhc.bi.mapper.theadvisor.HueShellMapper;
import com.mhc.bi.service.HueShellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description
 */
@RestController
@RequestMapping("/hueshell")

public class HueShellController {
    @Autowired
    private HueShellService hueShellService;

    @Autowired
    private HueShellMapper hueShellMapper;


    @GetMapping("select")
    public Object select() {
        return hueShellService.selectAll();
    }

    @GetMapping("insert")
    public int insertIntoHueShell() {

        HueShell hueShell1 = new HueShell();
        hueShell1.setName("task1");
        hueShell1.setShellContent("echo hueshell1");
        hueShell1.setType("shell");
        hueShell1.setExecuteTime("0");


        HueShell hueShell2 = new HueShell();
        hueShell2.setName("task2");
        hueShell2.setShellContent("echo hueshell2");
        hueShell2.setType("shell");
        hueShell2.setInput("task1");
        hueShell2.setExecuteTime("0");

        HueShell hueShell3 = new HueShell();
        hueShell3.setName("task3");
        hueShell3.setShellContent("echo hueshell3");
        hueShell3.setType("shell");
        hueShell3.setInput("task2");
        hueShell3.setExecuteTime("0");

        HueShell hueShell4 = new HueShell();
        hueShell4.setName("task4");
        hueShell4.setShellContent("echo hueshell4");
        hueShell4.setType("shell");
        hueShell4.setInput("task2");
        hueShell4.setExecuteTime("0");

        hueShellService.insert(hueShell2);
        hueShellService.insert(hueShell1);
        hueShellService.insert(hueShell3);
        hueShellService.insert(hueShell4);

        return 0;
    }

    //将HueShell的内容导入到JobPlan和ShellContent,模拟提交操作 http://localhost:8080/hueshell/export?name=task1
    @GetMapping("submit")
    public String export(String name) {
        return hueShellService.submit(name);
    }

    //将HueShell的内容导入到JobPlan和ShellContent,模拟提交操作 http://localhost:8080/hueshell/export?name=task1
    @GetMapping("selecttest")
    public Object selectAllTest(String name) {
        return hueShellMapper.getAllTest();
    }
}