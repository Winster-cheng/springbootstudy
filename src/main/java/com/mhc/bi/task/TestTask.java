package com.mhc.bi.task;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author baiyan
 * @date 2018/09/16
 * @description
 */
@Component
public class TestTask {
//    @Scheduled(fixedRate = 2000)
//    @Scheduled(cron = "0*/1 * * * * *")
//    @Scheduled(fixedDelay = 2000)
    public void helloWorld() throws InterruptedException {
        System.out.println("当前的时间是"+new Date());
        Thread.sleep(1000);
    }

}
