package com.mhc.bi.exec.execapp;

import com.mhc.bi.common.hadoop.HiveTableClient;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.service.ExecuteInstanceService;
import com.mhc.bi.service.ShellContentService;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.service.alert.DingDingAlert;

/**
 * @author baiyan
 * @date 2018/11/23
 * @description
 */
public class SqoopRunner extends Runner {
    public SqoopRunner(TaskInstance taskInstance, TaskInstanceService taskInstanceService, ShellContentService shellContentService, ExecuteInstanceService executeInstanceService, DingDingAlert dingDingAlert) {
        super(taskInstance, taskInstanceService, shellContentService, executeInstanceService, dingDingAlert);
    }

    public int execute(String cmd) {
        int exitValue = 1;
        String tableName = cmd.split("\\s+")[0];
        String ds = cmd.split("\\s+")[1];
        try {
            exitValue = new HiveTableClient().moveDataFromMysqlToHive(tableName, ds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exitValue;
    }
}
