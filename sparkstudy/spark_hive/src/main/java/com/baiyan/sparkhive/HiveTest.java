package com.baiyan.sparkhive;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;

/**
 * @Auther: peilongcheng
 * @Date: 2019/3/20 17:52
 * @Description:
 */
public class HiveTest {
    public static void main(String args[]) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("hive sql test")
                .master("local[*]")
                .config("executor-memory", "4G")
                .config("total-executor-cores", "2")//这个参数是所有的executor使用的总CPU核数
                .config("spark.cores.max", "2")
                .config("spark.sql.warehouse.dir", "hdfs://spark-test1:8020/apps/hive/warehouse")
                .enableHiveSupport()
                .getOrCreate();
        sparkSession.sql("CREATE DATABASE IF NOT EXISTS db_gtr LOCATION 'hdfs://spark-test1:8020/apps/hive/warehouse/db_gtr.db'");
        sparkSession.sql("CREATE TABLE IF NOT EXISTS db_gtr.s_fp_order_f (fp_order_no STRING,gmt_create STRING,gmt_modified STRING,fp_order_detail STRING,status INTEGER,risk_control INTEGER,biz_type INTEGER,dealer_id BIGINT,partner_name STRING,provider_id BIGINT,loan_start_time BIGINT,loan_expire_time BIGINT,manager_id BIGINT,region_id BIGINT,process_instance_id STRING,product_inst_id STRING,user_id BIGINT,capital_id BIGINT,is_pay_off INTEGER,has_extension BOOLEAN,is_confirmed_transport BOOLEAN,is_provider_car BOOLEAN,provider_user_id BIGINT,provider_manager_id BIGINT,apply_loan_amount BIGINT,biz_id STRING,step_flag INTEGER,thawing_subType INTEGER,dealer_contract_id BIGINT,is_guarantee BOOLEAN,dealer_name STRING,is_dealer_can_see BOOLEAN,account_deadline BIGINT,dealer_contract_phone STRING,choose_stocked_cars BOOLEAN,is_speed_return_money BOOLEAN,is_three_transport BOOLEAN,choose_loaning_cars BOOLEAN,source_list_type INTEGER) PARTITIONED by (ds STRING) ");

        //下面是通过Spark将mysql数据插入到hive，实现mysql->临时表->hive
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "root@123");
        properties.put("driver", "com.mysql.jdbc.Driver");
        //读取mysql数据
        Dataset<Row> bizdateDS = sparkSession.read().jdbc(
                "jdbc:mysql://localhost:3306/mytest?useUnicode=true&verifyServerCertificate=false&useSSL=false&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull",
                "user",
                properties
        );
        //读取mysql数据到内存的临时表
        bizdateDS.createOrReplaceTempView("temporary_table");
        //插入临时表数据到hive
        sparkSession.sql("insert into db_test.user select id,name from temporary_table");

    }
}
