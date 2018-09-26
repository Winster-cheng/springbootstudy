package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.Utils.StringHandle;
import com.mhc.bi.domain.HueShell;
import com.mhc.bi.domain.JobPlan;
import com.mhc.bi.domain.ShellContent;
import com.mhc.bi.mapper.hue.DesktopDocument2Mapper;
import com.mhc.bi.mapper.theadvisor.HueShellMapper;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.ShellContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

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
    DesktopDocument2Mapper desktopDocument2Mapper;

    @Autowired
    private HueShellService hueShellService;

    @Autowired
    private JobPlanService jobPlanService;

    @Autowired
    private ShellContentService shellContentService;

    @Autowired
    HueShell hueShell;

    JobPlan jobPlan;
    ShellContent shellContent;

    @Override
    public String submit(String name) {
        hueShell = hueShellService.selectByName(name);
        String msg = check(hueShell);
        if (msg != "OK") {
            return "提交" + name + "失败！！" + msg;
        } else {
            jobPlan = new JobPlan(name, hueShell.getInput(), hueShell.getShellName(), hueShell.getOutput(), hueShell.getExecuteRate(), hueShell.getExecuteTime(), hueShell.getParaments(), GetTime.getTimeStamp("yyyyMMdd"));
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

    /**
     * @描述 从hue数据库中的desktop_document2表的search内容转化为hueshell,其中
     * @参数 执行脚本名称，不以.bi结尾
     * @返回值 hueshell对象
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    @Override
    public HueShell selectByName(String name) {
        String executorContent = desktopDocument2Mapper.getSearch(StringHandle.checkEnding(name,".bi"));
        System.out.println("从hue2中取出的"+StringHandle.checkEnding(name,"bi")+"内容是"+executorContent);
        //name type executetime shellname shellcontent input output execute_rate paraments
        hueShell.setName(name);
        Map<String, String> fileMap = new HashMap<String, String>(); //负责存储从数据库中查询出来的键值对
        String[] ec = executorContent.split("\\n");
        for (String x : ec) {
            if (x.contains("command=")) {//处理hue界面的command=xxxx.sql x=20133113 y=${yyyyMMdd}
                String[] g = x.replaceAll("command=", "").trim().split("//s+");
                hueShell.setShellName(g[0]);
                String command = desktopDocument2Mapper.getSearch(g[0]);
                hueShell.setShellContent(command);
                if (g.length > 1) {
                    StringBuffer p = new StringBuffer();
                    for (int j = 1; j < g.length; j++) {
                        p.append(g[j] + ",");
                    }
                    hueShell.setParaments(p.toString());
                }
            } else if (x.split("=").length > 1)
                fileMap.put(x.split("=")[0].toLowerCase(), x.split("=")[1]);
        }
        if (!fileMap.keySet().contains("executetime") &&!fileMap.keySet().contains("type")) {
            try {
                throw new Exception("缺少executetime或者缺少type");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hueShell.setExecuteTime(fileMap.get("executetime"));
        hueShell.setType(fileMap.get("type"));

        if (fileMap.keySet().contains("input"))
            hueShell.setInput(fileMap.get("input"));

        if (fileMap.keySet().contains("output"))
            hueShell.setOutput(fileMap.get("output"));
        if (fileMap.keySet().contains("executerate"))
            hueShell.setExecuteRate(fileMap.get("executerate"));

        return hueShell;
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
