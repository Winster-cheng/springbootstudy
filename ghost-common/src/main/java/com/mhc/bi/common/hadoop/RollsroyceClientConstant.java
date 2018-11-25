package com.mhc.bi.common.hadoop;

import java.io.File;

/**
 * DESCRIPTION :
 * DATETIME : 2017/10/12 下午8:31
 *
 * @author weifeng
 * @version 1.0.0
 */
public class RollsroyceClientConstant {

    public static final String DEFAULT_LOG_PATH = System.getProperty("user.home") + File.separatorChar
            + "logs" + File.separatorChar + "rollsroyce-client";

    public static final String MYSQL_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String PHOENIX_JDBC_DRIVER = "org.apache.phoenix.jdbc.PhoenixDriver";

    public static final int FAIL_CODE = 1;
    public static final int SUCCESS_CODE = 0;

}
