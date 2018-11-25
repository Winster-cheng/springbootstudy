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

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author baiyan
 * @date 2018/09/18
 * @description ShellRunner负责属性shell类型的任务
 */
public class ShellRunner extends Runner {
    public ShellRunner(TaskInstance taskInstance, TaskInstanceService taskInstanceService, ShellContentService shellContentService, ExecuteInstanceService executeInstanceService, DingDingAlert dingDingAlert) {
        super(taskInstance, taskInstanceService, shellContentService, executeInstanceService, dingDingAlert);
    }

    public int execute(String cmd) {
        String result = "";
        int exitValue = 1;
        try {
            String[] commands = {"/bin/sh", "-c", cmd};
            ProcessBuilder builder = new ProcessBuilder(commands);
            builder.redirectErrorStream(true);
            Process process = builder.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while (in.read(re) != -1) {
                String msg = new String(re);
                logger.info(msg);
                re = new byte[1024];
                result = result + new String(msg);
            }
            exitValue = process.waitFor();
            dingDingAlert.sendMsg(result);
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exitValue;
    }
}