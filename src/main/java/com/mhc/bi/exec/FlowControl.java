package com.mhc.bi.exec;

import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.exec.execapp.Runner;
import com.mhc.bi.exec.execapp.ShellRunner;
import com.mhc.bi.service.ExecuteInstanceService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.service.alter.DingDingAlert;
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
    ShellRunner shellRunner ;

    /**
     * @描述 每天0点开始，运行任务
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/23
     * @修改人和其它信息
     */
    public Object start() {
        startNode = taskInstanceService.selectStartNode(GetTime.getTimeStamp("yyyyMMdd"));
        //从头节点开始启动
        for (TaskInstance taskInstance : startNode) {
            shellRunner=new ShellRunner(taskInstance, taskInstanceService, shellContentService,executeInstanceService,dingDingAlert);//这里请解决构造方法中无法引用注解的问题，目前先把注解对象传入
            threadPoolExecutor.submit(shellRunner);
        }
        return "所有任务都已经启动";
    }
}
