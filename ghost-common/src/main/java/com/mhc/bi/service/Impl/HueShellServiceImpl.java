package com.mhc.bi.service.Impl;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.Utils.StringHandle;
import com.mhc.bi.domain.theadvisor.HueShell;
import com.mhc.bi.domain.theadvisor.JobPlan;
import com.mhc.bi.domain.theadvisor.ShellContent;
import com.mhc.bi.mapper.hue.DesktopDocument2Mapper;
import com.mhc.bi.mapper.theadvisor.HueShellMapper;
import com.mhc.bi.service.HueShellService;
import com.mhc.bi.service.JobPlanService;
import com.mhc.bi.service.ShellContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    JobPlan jobPlan;
    ShellContent shellContent;

    @Override
    public boolean submit(String name) {
        HueShell hueShell;
        hueShell = hueShellService.selectByName(StringHandle.checkEnding(name,"bi"));
        insertOrAddNewVersion(hueShell);//做一个备份到hueshell表
        String msg = check(hueShell);
        if (msg != "OK") {
            return false;
        } else {
            jobPlan = new JobPlan(hueShell.getName(), hueShell.getInput(), hueShell.getShellName().replaceAll(".bi", ""), hueShell.getOutput(), hueShell.getExecuteRate(), hueShell.getExecuteTime(), hueShell.getParaments(), GetTime.getTimeStamp("yyyyMMdd"),hueShell.getOwner(),hueShell.getType());
            jobPlanService.insert(jobPlan);
            shellContent = new ShellContent(hueShell.getShellName(), hueShell.getShellContent(), hueShell.getType(), GetTime.getTimeStamp("yyyyMMdd"));
            shellContentService.insertShellContent(shellContent);
            return true;
        }
    }

    @Override
    public HueShell selectAliveByName(String name) {
        return hueShellMapper.selectAliveByName(name);
    }

    @Override
    public int insertOrAddNewVersion(HueShell hueShell) {
        //如果有旧版本，那么把旧版本is_history设置为1
        HueShell h = hueShellService.selectAliveByName(hueShell.getName());
        if (h != null) {
            hueShellMapper.updateHistory(h);
        }
        hueShellService.insert(hueShell);
        return 0;
    }

    /**
     * @param output
     * @描述 根据output和is_history=1查询hueshell对象
     * @参数 output
     * @返回值 HueShell
     * @创建人 baiyan
     * @创建时间 2018/9/27
     * @修改人和其它信息
     */
    @Override
    public HueShell selectByOutput(String output) {
        return hueShellMapper.selectByOutput(output);
    }

    @Override
    public List<String> getDirectory() {
        return null;
    }

    @Override
    public List<HueShell> selectAll() {
        return hueShellMapper.getAll();
    }

    @Override
    public int insert(HueShell hueShell) {
        return hueShellMapper.insert(hueShell, GetTime.getTimeWithMysqlFormat());
    }

    /**
     * @描述 从hue数据库中的desktop_document2表的search内容转化为hueshell, 其中
     * @参数 执行脚本名称，不以.bi结尾
     * @返回值 hueshell对象
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    @Override
    public HueShell selectByName(String name) {
      HueShell  hueShell=new HueShell();
      String realName=StringHandle.checkEnding(name, ".bi");
        String executorContent = desktopDocument2Mapper.selectByName(realName).getSearch();
        //TODO owner接入实际用户
        String owner="测试用户";
        String type="hiveshell";
        //name type executetime shellname shellcontent input output execute_rate paraments
        hueShell.setName(realName);
        hueShell.setType(type);
        Map<String, String> fileMap = new HashMap<String, String>(); //负责存储从数据库中查询出来的键值对
        String[] ec = executorContent.split("\\n");
        for (String x : ec) {
            if (x.contains("command=")) {//处理hue界面的command=xxxx.sql x=20133113 y=${yyyyMMdd}
                String[] g = x.replaceAll("command=", "").trim().split("//s+");
                hueShell.setShellName(g[0]);
                String command = desktopDocument2Mapper.selectByName(g[0]).getSearch();
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
        if (fileMap.get("executetime") == null || fileMap.get("type") == null || fileMap.get("output") == null) {
            try {
                throw new Exception("缺少executetime或者缺少type或者缺少output");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hueShell.setExecuteTime(fileMap.get("executetime"));
        hueShell.setType(fileMap.get("type"));

        if (fileMap.keySet().contains("input")) {
            hueShell.setInput(fileMap.get("input"));
        }

        if (fileMap.keySet().contains("output"))
            hueShell.setOutput(fileMap.get("output"));
        if (fileMap.keySet().contains("executerate"))
            hueShell.setExecuteRate(fileMap.get("executerate"));
        hueShell.setIsHistory(1);
        hueShell.setOwner(owner);
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
        for (String x : hueShell.getInput().split(",")) {//检查每天父节点的最早执行时间
            HueShell parentHueShell;
            parentHueShell = hueShellService.selectByOutput(x);
            if (startHour < getEarliesTime(parentHueShell)) {
                return hueShell.getName() + "的执行时间早于" + parentHueShell.getName();
            }
        }
        hueShell.getName();

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
        System.out.println("hueShell.getExecuteTime():"+hueShell.getExecuteTime());
        List<String> timeList = Arrays.asList(hueShell.getExecuteTime().split(","));
        return Integer.parseInt(Collections.min(timeList));
    }


}
