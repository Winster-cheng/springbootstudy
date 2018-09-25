package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.HueShell;
import com.mhc.bi.domain.JobPlan;
import com.mhc.bi.domain.ShellContent;
import com.mhc.bi.mapper.HueShellMapper;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.ShellContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/09/17
 * @description
 */
@Service
public class HueShellServiceImpl implements HueShellService {
    @Autowired
    HueShellMapper hueShellMapper;

    @Autowired
    private HueShellService hueShellService;

    @Autowired
    private JobPlanService jobPlanService;

    @Autowired
    private ShellContentService shellContentService;

    HueShell hueShell;
    JobPlan jobPlan;
    ShellContent shellContent;

    @Override
    public String submit(String name) {
        hueShell = hueShellService.selectByName(name);
        String msg=check(hueShell);
        if (msg!="OK") {
            return "提交" + name + "失败！！"+msg;
        } else {
            jobPlan = new JobPlan(name, hueShell.getInput(),hueShell.getShellName(),hueShell.getOutput(), hueShell.getExecuteRate(), hueShell.getExecuteTime(), hueShell.getParaments(), GetTime.getTimeStamp("yyyyMMdd"));
            shellContent = new ShellContent(name, hueShell.getShellContent(), hueShell.getType(), GetTime.getTimeStamp("yyyyMMdd"));
            return "jobPlan插入结果" + jobPlanService.insert(jobPlan) + "\nshellContent插入结果:" + shellContentService.insertShellContent(shellContent);
        }
    }

    @Override
    public List<HueShell> selectAll() {
        return hueShellMapper.getAll();
    }

    @Override
    public int insert(HueShell hueShell) {
        return hueShellMapper.insert(hueShell, new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
    }

    @Override
    public HueShell selectByName(String name) {
        return hueShellMapper.getHueShell(name);
    }


    /**
     * @描述 确认输入的任务，他的最早执行时间，有没有比他的父节点早，如果早，就返回false
     * @参数 HueShell hueShell
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/23
     * @修改人和其它信息
     */
    public String check(HueShell hueShell) {
        if (hueShell.getInput() == null) return "OK";//这个节点没有父依赖
        int startHour = getEarliesTime(hueShell);
        HueShell parentHueShell;
        for (String x : hueShell.getInput().split(",")) {//检查每天父节点的最早执行时间
            parentHueShell = hueShellService.selectByName(x);
            if (startHour < getEarliesTime(parentHueShell)) {
                return hueShell.getName() + "的执行时间早于" + parentHueShell.getName();
            }
        }
        return "OK";
    }

    /**
     * @描述 输入一个HueShell对象，返回这个HueShell的最早执行时间
     * @参数 Hueshell hueShell
     * @返回值 int
     * @创建人 baiyan
     * @创建时间 2018/9/23
     * @修改人和其它信息
     */

    public int getEarliesTime(HueShell hueShell) {
        List<String> timeList = Arrays.asList(hueShell.getExecuteTime().split(","));
        return Integer.parseInt(Collections.min(timeList));
    }
}
