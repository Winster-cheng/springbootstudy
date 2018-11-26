package com.mhc.bi.exec.execapp;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.common.Status;
import com.mhc.bi.domain.theadvisor.ExecuteInstance;
import com.mhc.bi.domain.theadvisor.ShellContent;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.exec.FlowControl;
import com.mhc.bi.service.ExecuteInstanceService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.service.alert.DingDingAlert;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author baiyan
 * @date 2018/09/21
 * @description 所有执行器的父类
 */
public class Runner implements Runnable {
    public String name;
    public String realName;  //去除了时间后缀之后的名称
    public String readShellName;
    public String output;
    public Status status;
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
    public DingDingAlert dingDingAlert;
    public org.slf4j.Logger logger;


    public Runner init(TaskInstance taskInstance, TaskInstanceService taskInstanceService, ShellContentService shellContentService, ExecuteInstanceService executeInstanceService, DingDingAlert dingDingAlert) {
        //TODO 4个service类无法通过注入的方式实现，需要深入学习下这个知识点
        this.dingDingAlert = dingDingAlert;
        this.executeInstanceService = executeInstanceService;
        this.taskInstanceService = taskInstanceService;
        this.shellContentService = shellContentService;
        executeInstance = new ExecuteInstance();
        this.taskInstance = taskInstance;
        //进行参数替换
        this.realName = taskInstance.getRealName();
        this.readShellName = taskInstance.getShellName();
        this.command = getCommand(taskInstance);
        this.day = taskInstance.getExecuteDay();
        this.executeTime = taskInstance.getExecuteTime();
        this.name = taskInstance.getName();
        this.output = taskInstance.getOutput();
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.setInputListAndOutputList(taskInstance);
        return this;
    }

    @Override
    public void run() {
        dingDingAlert.sendMsg("开始执行" + name);
        taskInstance.setStatus(3);
        this.taskInstance.setStartTime(GetTime.getTimeWithMysqlFormat());
        taskInstanceService.updateStatus(this.taskInstance);
        if (!checkParentsStatus()) return;
        int time = timeCheck();
        if (time != 0) {
            logger.info("时间未到，等待" + time + "小时");
            dingDingAlert.sendMsg(taskInstance.getName() + "时间未到，等待" + time + "小时");
            try {
                Thread.sleep(time * 3600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            int execStatus = this.execute(this.command);
            this.taskInstance.setEndTme(GetTime.getTimeWithMysqlFormat());
            if (execStatus != 0) {
                this.taskInstance.setStatus(5);
                dingDingAlert.sendMsg(this.realName + "在" + this.executeTime + "点的任务执行失败");
                taskInstanceService.updateStatus(this.taskInstance);
                return;
            }
            this.taskInstance.setStatus(4);
            dingDingAlert.sendMsg(this.realName + "在" + this.executeTime + "点的任务执行成功");
            taskInstanceService.updateStatus(this.taskInstance);
            this.initExecuteInstance(taskInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.outputNodeList.size() != 0) {
            for (TaskInstance taskInstance : outputNodeList) {
                if (taskInstance.getType().equals("sqoop"))
                    FlowControl.threadPoolExecutor.submit(new SqoopRunner().init(taskInstance, taskInstanceService, shellContentService, executeInstanceService, dingDingAlert));
                else
                    FlowControl.threadPoolExecutor.submit(new ShellRunner().init(taskInstance, taskInstanceService, shellContentService, executeInstanceService, dingDingAlert));

            }
        }
    }

    /**
     * @描述 输入TaskInstance获取替换过参数的命令
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/27
     * @修改人和其它信息
     */
    public String getCommand(TaskInstance taskInstance) {
        ShellContent shellContent;
        //获取去除了时间后缀的task任务名
        shellContent = shellContentService.selectByName(readShellName);
        if (taskInstance.getParaments() != null) {
            this.command = this.paramentsReplace(shellContent.getShellContent(), taskInstance.getParaments());
        } else this.command = shellContent.getShellContent();
        return this.command;
    }

    /**
     * @描述 将command需要替换的参数进行替换
     * @参数 比如command=sqoop_test.sql biz_date=${yyyyMMdd+1}，那么会把sqoop_test.sql中的${biz_date}替换成明天
     * @返回值 可以直接用来运行的命令
     * @创建人 baiyan
     * @创建时间 2018/9/25
     * @修改人和其它信息
     */
    public String paramentsReplace(String command, String paraments) {
        try {
            List<String> paramentsList;
            String pattern = "\\{(.*?)\\}";//${}
            if (paraments == null) return command;
            paramentsList = Arrays.asList(paraments.split(","));
            for (String parament : paramentsList) {
                String key = parament.split("=")[0];
                String value = parament.split("=")[1];
                if (Pattern.matches(pattern, value)) { //如果参数是{yyyyMMdd+1}格式，那么把他替换成时间
                    value = GetTime.getTimeForCommand(value.replaceAll("\\{|\\}", ""));
                }
                command = command.replaceAll("\\$\\{" + key + "\\}", value);
            }
            //TODO   DingDingAlert.sendMsg(command);会报错
            return command;
        } catch (Exception e) {
            DingDingAlert.sendMsg(e.getMessage());
            DingDingAlert.sendMsg(e.getLocalizedMessage());
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setInputListAndOutputList(TaskInstance taskInstance) {
        inputList = new LinkedList<>();
        //生成父节点列表
        if (taskInstance.getInput() != null && taskInstance.getInput().contains(",")) {
            for (String task : taskInstance.getInput().split(",")) {
                inputList.add(taskInstanceService.selectByTimeAndOutput(taskInstance.getExecuteDay(), task));
            }
        } else if (taskInstance.getInput() != null) {
            inputList.add(taskInstanceService.selectByTimeAndOutput(taskInstance.getExecuteDay(), taskInstance.getInput()));
        }
        //生成子节点列表
        outputNodeList = taskInstanceService.selectOutNode(taskInstance.getOutput(), taskInstance.getExecuteDay());
    }

    public int execute(String cmd) {
        return 0;
    }

    public boolean checkParentsStatus() {
        System.out.println(this.inputList);
        for (TaskInstance t : this.inputList) {
            if (t.getStatus() != 4) {
                System.out.println(this.name + "的父节点" + t.getRealName() + "在 " + t.getExecuteDay() + "日" + this.executeTime + "点" + "状态是" + Status.getStatus(t.getStatus()) + ",结束本次执行");
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
        try {
            this.executeInstance.setParentId(taskInstance.getId());
            this.executeInstance.setEndTime(taskInstance.getEndTime());
            this.executeInstance.setStartTime(taskInstance.getStartTime());
            this.executeInstance.setStatus(taskInstance.getStatus());
            this.executeInstance.setLogLink("No LOG");
            this.executeInstanceService.insert(this.executeInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
