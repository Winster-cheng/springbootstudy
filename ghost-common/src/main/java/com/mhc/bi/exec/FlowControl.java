package com.mhc.bi.exec;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.exec.execapp.Runner;
import com.mhc.bi.exec.execapp.ShellRunner;
import com.mhc.bi.service.ExecuteInstanceService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.service.alert.DingDingAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author baiyan
 * @date 2018/09/21
 * @description 调度线程
 */
@Service
public class FlowControl {

    @Autowired
    TaskInstanceService taskInstanceService;

    @Autowired
    ShellContentService shellContentService;

    @Autowired
    DingDingAlert dingDingAlert;

    @Autowired
    ExecuteInstanceService executeInstanceService;
    private List<Runner> shellRunnerList;
    private List<TaskInstance> taskInstancesList;
    private List<TaskInstance> startNode;

    public static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);//创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
    ShellRunner shellRunner;

    /**
     * @描述 每天0点开始，运行任务
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/23
     * @修改人和其它信息
     */
    public String start() {
        TaskInstance taskInstance=taskInstanceService.selectStartNode(GetTime.getTimeStamp("yyyyMMdd"));//根据execute_day执行，注意这是0点以后执行的
//        TaskInstance taskInstance = taskInstanceService.selectStartNode(GetTime.getTimeStamp("20181126"));
        shellRunner = new ShellRunner(taskInstance, taskInstanceService, shellContentService, executeInstanceService, dingDingAlert);
        FlowControl.threadPoolExecutor.submit(shellRunner);
        dingDingAlert.sendMsg("头节点已经启动");
        return "头节点已经启动";
    }
}
