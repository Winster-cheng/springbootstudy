package com.mhc.bi.exec;

import com.mhc.bi.domain.TaskInstance;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author baiyan
 * @date 2018/09/21
 * @description 启动一个调度线程和一个线程池
 * 调度线程:读取数据库，创建线程池(FlowRunner)
 * 线程池：存放执行线程，目前先默认执行shell线程
 */
@Service
public class ExecServer {
    ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);//创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
    @Autowired
    TaskInstanceService taskInstanceService;
    @Autowired
    ShellContentService shellContentService;

    List<TaskInstance> taskInstances;
    ExecutorService pool = Executors.newFixedThreadPool(100);

    /**
     * @描述 启动整个DAG
     * @参数
     * @返回值
     * @创建人 baiyan
     * @创建时间 2018/9/21
     * @修改人和其它信息
     */
    public Object start() {
       return shellContentService.selectByName("task1");
    }
}
