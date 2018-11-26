package com.mhc.bi.exec.execapp;

import com.mhc.bi.common.hadoop.HiveTableClient;
import com.mhc.bi.common.hadoop.util.SpringContextUtil;

/**
 * @author baiyan
 * @date 2018/11/23
 * @description
 */
public class SqoopRunner extends Runner {

    HiveTableClient hiveTableClient = SpringContextUtil.getBean(HiveTableClient.class);
    public int execute(String cmd) {
        int exitValue = 1;
        String tableName = cmd.split("\\s+")[0];
        String ds = cmd.split("\\s+")[1];
        try {
            exitValue = hiveTableClient.moveDataFromMysqlToHive(tableName, ds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return exitValue;
    }

}
