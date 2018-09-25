package com.mhc.bi.exec.execapp;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.common.Status;
import com.mhc.bi.domain.ExecuteInstance;
import com.mhc.bi.domain.ShellContent;
import com.mhc.bi.domain.TaskInstance;
import com.mhc.bi.exec.FlowControl;
import com.mhc.bi.service.ExecuteInstanceService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author baiyan
 * @date 2018/09/18
 * @description ShellRunner负责属性shell类型的任务
 */
public class ShellRunner extends Runner implements Runnable {
    public String name;
    public Status status;
    public String paraments;
    public TaskInstance taskInstance;
    public List<TaskInstance> inputList; //父节点
    public List<TaskInstance> outputNodeList; //该节点的子节点
    public String executeTime;
    private String command;
    public String day;
    public ShellContentService shellContentService;
    public ExecuteInstance executeInstance;
    public TaskInstanceService taskInstanceService;
    public ExecuteInstanceService executeInstanceService;

    public ShellRunner(TaskInstance taskInstance, TaskInstanceService taskInstanceService, ShellContentService shellContentService, ExecuteInstanceService executeInstanceService) {
        //TODO 3个service类无法通过注入的方式实现，需要深入学习下这个知识点
        this.executeInstanceService = executeInstanceService;
        this.taskInstanceService = taskInstanceService;
        this.shellContentService = shellContentService;
        executeInstance = new ExecuteInstance();
        ShellContent shellContent = shellContentService.selectByName(taskInstance.getName().split("_")[0]);
        this.taskInstance = taskInstance;
        this.command = shellContent.getShellContent();
        //TODO 这里没进行参数替换，以后加上去
        this.paraments = taskInstance.getParaments();
        this.day = taskInstance.getExecuteDay();
        this.executeTime = taskInstance.getExecuteTime();
        this.name = taskInstance.getName();
        this.setInputListAndOutputList(taskInstance);
    }

    @Override
    public void run() {
        if (checkParentsStatus()) {
            int time = timeCheck();
            if (time != 0) {
                System.out.println("时间未到，等待" + time + "小时");
                try {
                    Thread.sleep(time * 3600000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("开始执行" + name);
            this.taskInstance.setStartTime(GetTime.getTimeStamp("yyyyMMddHHmmss"));
            int execStatus = this.execute(this.command);
            this.taskInstance.setEndTme(GetTime.getTimeStamp("yyyyMMddHHmmss"));
            if (execStatus != 0) {
                this.taskInstance.setStatus(5);
                System.out.println(this.name + "执行失败");
                return;
            } else {
                this.taskInstance.setStatus(4);
                System.out.println(this.name + "执行成功");
            }
            taskInstanceService.updateStatus(this.taskInstance);
            this.initExecuteInstance(taskInstance);
            if (this.outputNodeList != null) {
                for (TaskInstance taskInstance : outputNodeList) {
                    FlowControl.threadPoolExecutor.submit(new ShellRunner(taskInstance, taskInstanceService, shellContentService, executeInstanceService));
                }
            }
        }
    }

    /**
     * @描述 将command需要替换的参数进行替换
     * @参数 command: ${x} 类似于这种格式的x需要替换 paraments: x=20190331,y=${yyyyMMdd} 以","分割
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    public String paramentsReplace(String command, String paraments) {
        List<String> paramentsList;
        String pattern = "(.*?)\\$\\{(.*?)\\}(.*?)";//x=${yyyymmdd}
        if (paraments == null) return command;
        paramentsList = Arrays.asList(paraments.split(","));
        for (String parament : paramentsList) {
            String key = parament.split("=")[0];
            String value = parament.split("=")[1];
            if (Pattern.matches(pattern, value)) { //如果参数是${}格式，那么把他替换成时间
                value = GetTime.getTimeStamp(parament.split("=")[1], -1);
            }
            command = command.replaceAll("\\$\\{" + key + "\\}", value);
        }
        return command;
    }

    public void setInputListAndOutputList(TaskInstance taskInstance) {
        inputList = new LinkedList<>();
        //生成父节点列表
        if (taskInstance.getInput() != null && taskInstance.getInput().contains(",")) {
            for (String task : taskInstance.getInput().split(",")) {
                inputList.add(taskInstanceService.selectByTimeAndName(taskInstance.getExecuteDay(), task));
            }
        } else if (taskInstance.getInput() != null) {
            inputList.add(taskInstanceService.selectByTimeAndName(taskInstance.getExecuteDay(), taskInstance.getInput()));
        }

        //生成子节点列表
        if (taskInstance.getOutput() == null)
            outputNodeList = taskInstanceService.selectOutNode(taskInstance.getName(), taskInstance.getExecuteDay());
        else outputNodeList = taskInstanceService.selectOutNode(taskInstance.getOutput(), taskInstance.getExecuteDay());
    }

    public int execute(String command) {
        StringBuffer results = new StringBuffer();
        String[] commands = {"/bin/sh", "-c", command};
        int exitValue = 1;
        try {
            ProcessBuilder hiveProcessBuilder = new ProcessBuilder(commands);
            Process process = hiveProcessBuilder.start();
            try {
                exitValue = process.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            String data = null;
            while ((data = br.readLine()) != null) {
                results.append(data + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(results.toString());
        return exitValue;
    }

    public boolean checkParentsStatus() {
        for (TaskInstance t : this.inputList) {
            if (taskInstanceService.selectByTimeAndName(t.getExecuteDay(), t.getName()).getStatus() != 4) {
                System.out.println(this.name + "的父节点" + t.getName().split("_")[0] + "在 " + t.getExecuteDay() + "日" + t.getName().split("_")[1] + "点" + "状态是" + Status.getStatus(t.getStatus()) + ",结束本次执行");
                return false;
            }
        }
        return true;
    }

    /**
     * @描述 比较当前任务的执行时间和当前时间的大小
     * @参数
     * @返回值 如果执行时间>当前时间，返回 执行时间-当前时间，其他情况返回0
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    public int timeCheck() {
        int time = Integer.parseInt(this.executeTime) - Integer.parseInt(GetTime.getCurrentHour());
        if (time > 0) {
            return time;
        }
        return 0;
    }

    /**
     * @描述 初始化executeInstance对象并且存入数据库的executeInstance表中
     * @参数 taskInstance对象
     * @返回值 无
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    public void initExecuteInstance(TaskInstance taskInstance) {
        this.executeInstance.setParentId(taskInstance.getId());
        this.executeInstance.setEndTime(taskInstance.getEndTime());
        this.executeInstance.setStartTime(taskInstance.getStartTime());
        this.executeInstance.setStatus(taskInstance.getStatus());
        this.executeInstance.setLogLink("暂无");
        this.executeInstanceService.insert(this.executeInstance);
    }
}