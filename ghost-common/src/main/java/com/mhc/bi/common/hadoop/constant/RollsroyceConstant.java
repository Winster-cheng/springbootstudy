package com.mhc.bi.common.hadoop.constant;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * DESCRIPTION :
 * DATETIME : 2017/8/11 下午5:17
 *
 * @author weifeng
 * @version 1.0.0
 */
public class RollsroyceConstant implements Serializable{

    private static final long serialVersionUID = 8500890599233474130L;

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    public static final String TOPIC_ID = "maxwell_mysql_binlog";
    public static final String APP_NAME = "MHC_BI_SPARK_STREAMING";
    public static final String APP_NAME_RISK = "BI_SPARK_STREAMING_RISK";
    public static final String SPARK_DEFAULT_LOCAL_MASTER = "local[*]";

    public static final long DEFAULT_DURATION_SECONDS = 2L;

    public static final String GROUP_ID_FINANCE_ORDER_DETAIL = "FINANCE_ORDER_DETAIL";
    public static final String NEW_GROUP_ID_FINANCE_ORDER_DETAIL = "NEW_FINANCE_ORDER_DETAIL";
    public static final String GROUP_ID_STREAMING_RISK = "RISK_STREAMING";
    public static final String GROUP_ID_FINANCE_CREDIT ="FINANCE_CREDIT";
    public static final String GROUP_ID_FINANCE_CREDIT_TEST ="FINANCE_CREDIT_TEST";

    public static final String TAG_GTR_FP_ORDER = "db_gtr.fp_order";
    public static final String TAG_B2B_WMS_CAR = "db_b2b.wms_car";
    public static final String TAG_B2B_WMS_WAREHOUSE = "db_b2b.wms_warehouse";
    public static final String TAG_B2B_TMS_TRANSPORT_CAR = "db_b2b.tms_transport_car";
    public static final String TAG_B2B_SUPERVISION_COMMITMENT = "db_b2b.b2b_supervision_commitment";
    public static final String TAG_B2B_CAR_SUPERVISION = "db_b2b.b2b_car_supervision";
    public static final String TAG_B2B_CC_CAR = "db_b2b.cc_car";
    public static final String TAG_B2B_TMS_TRANSPORT_ORDER = "db_b2b.tms_transport_order";
    public static final String TAG_B2B_TMS_IN_TRANSIT_RECORD = "db_b2b.tms_in_transit_record";
    public static final String TAG_GTR_FP_PAYMENT_BATCH = "db_gtr.fp_payment_batch";
    public static final String TAG_GTR_FP_PAYMENT_DETAIL = "db_gtr.fp_payment_detail";
    public static final String TAG_GTR_FP_ORDER_LABEL = "db_gtr.fp_order_label";
    public static final String TAG_B2B_WMS_CAR_STOCKTAKE_DETAIL = "db_b2b.wms_car_stocktake_detail";
    public static final String TAG_GTR_FP_EXTENSION_BATCH = "db_gtr.fp_extension_batch";
    public static final String TAG_GTR_FP_EXTENSION_DETAIL = "db_gtr.fp_extension_detail";

    public static final String MQ_SERVER_ADDRESS_KEY = "mq.server.addr";
    public static final String STREAMING_MASTER_KEY = "streaming.master";
    public static final String SQL_MASTER_KEY = "sql.master";
    public static final String WAREHOUSE_LOCATION_KEY = "warehouse.location";
    public static final String BI_DB_URL_KEY = "bi.db.url";
    public static final String BI_DB_PORT_KEY = "bi.db.port";
    public static final String BI_DB_NAME_KEY = "bi.db.name";
    public static final String BI_DB_USERNAME_KEY = "bi.db.username";
    public static final String BI_DB_PASSWORD_KEY = "bi.db.password";
    public static final String PHOENIX_ZK_URL_KEY = "phoenix.zk.url";
    public static final String HIVE_JDBC_URL = "hive.jdbc.url";

    public static final String HBASE_DAILY_HOSTS = "172.21.10.53,172.21.10.140,172.21.10.243";
    public static final String HBASE_ONLINE_HOSTS = "10.0.1.85,10.0.1.88,10.0.1.89";

    public static final String mqServerAddress;
    public static final String streamingMaster;

    public static final String sqlMaster;
    public static final String warehouseLocation;
    public static final String biDBUrl;
    public static final String biDBPort;
    public static final String biDBName;
    public static final String biDBUser;
    public static final String biDBPassword;

    public static final String biDBFullUrl;

    public static final String phoenixZkUrl;
    public static final String phoenixJdbcUrl;
    public static final String hiveJdbcUrl;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("config/rollsroyce");
        mqServerAddress = resource.getString(MQ_SERVER_ADDRESS_KEY);
        streamingMaster = resource.getString(STREAMING_MASTER_KEY);
        sqlMaster = resource.getString(SQL_MASTER_KEY);
        warehouseLocation = resource.getString(WAREHOUSE_LOCATION_KEY);
        biDBUrl = resource.getString(BI_DB_URL_KEY);
        biDBPort = resource.getString(BI_DB_PORT_KEY);
        biDBName = resource.getString(BI_DB_NAME_KEY);
        biDBUser = resource.getString(BI_DB_USERNAME_KEY);
        biDBPassword = resource.getString(BI_DB_PASSWORD_KEY);
        biDBFullUrl = "jdbc:mysql://" + biDBUrl + ":" + biDBPort + "/" + biDBName + "?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&useSSL=false";
        phoenixZkUrl = resource.getString(PHOENIX_ZK_URL_KEY);
        phoenixJdbcUrl = "jdbc:phoenix:" + phoenixZkUrl;
        hiveJdbcUrl = resource.getString(HIVE_JDBC_URL);
//        String fileName = System.getProperty("user.home") + File.separatorChar + "antx-rollsroyce.properties";
//        Properties resource = new Properties();
//        try {
//            resource.load(new FileReader(fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mqServerAddress = resource.getProperty(MQ_SERVER_ADDRESS_KEY);
//        streamingMaster = resource.getProperty(STREAMING_MASTER_KEY);
//        sqlMaster = resource.getProperty(SQL_MASTER_KEY);
//        warehouseLocation = resource.getProperty(WAREHOUSE_LOCATION_KEY);
//        biDBUrl = resource.getProperty(BI_DB_URL_KEY);
//        biDBPort = resource.getProperty(BI_DB_PORT_KEY);
//        biDBName = resource.getProperty(BI_DB_NAME_KEY);
//        biDBUser = resource.getProperty(BI_DB_USERNAME_KEY);
//        biDBPassword = resource.getProperty(BI_DB_PASSWORD_KEY);
//        biDBFullUrl = "jdbc:mysql://" + biDBUrl + ":" + biDBPort + "/" + biDBName + "?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&useSSL=false";
//        phoenixZkUrl = resource.getProperty(PHOENIX_ZK_URL_KEY);
//        phoenixJdbcUrl = "jdbc:phoenix:" + phoenixZkUrl;
//        hiveJdbcUrl = resource.getProperty(HIVE_JDBC_URL);
    }

}
