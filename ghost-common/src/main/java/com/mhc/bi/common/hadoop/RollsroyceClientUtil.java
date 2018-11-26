package com.mhc.bi.common.hadoop;

import java.util.Properties;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/24 下午2:24
 *
 * @author weifeng
 * @version 1.0.0
 */
public class RollsroyceClientUtil {

    /**
     * 构建BI数据库properties
     *
     * @return
     */

    public static Properties buildBIDBProperties(String user,String password,String driver) {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password",password);
        properties.put("driver", driver);
        return properties;
    }

    public static Properties buildBIDBPropertiesTest() {
        Properties properties = new Properties();
        properties.put("user", "dtdba_backup57");
        properties.put("password", "r8G8EMqNC8p");
        properties.put("driver", "com.mysql.jdbc.Driver");
        return properties;
    }

    public static Properties buildBIDBProperties1(String user,String password,String driver) {
        Properties properties = new Properties();
        properties.put("user", "bi");
        properties.put("password", "aRwr84pKtqXAZm");
        properties.put("driver", "com.mysql.jdbc.Driver");
        return properties;
    }
}
