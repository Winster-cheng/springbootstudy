package com.mhc.bi.common.hadoop;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mhc.bi.Utils.GetTime;
import com.mhc.bi.common.hadoop.config.HiveDataTypeEnum;
import com.mhc.bi.common.hadoop.config.SparkDataTypeEnum;
import com.mhc.bi.common.hadoop.util.JdbcUtil;
import com.mhc.bi.service.alert.DingDingAlert;
import jodd.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * DESCRIPTION :
 * BAIYAN's DESCRIPTION:
 * DATETIME : 2018/11/21
 *
 * @author baiyan
 * @version 2.0.0
 */
public class HiveTableClient {
    DingDingAlert dingDingAlert;
    private static Logger logger = LoggerFactory.getLogger(HiveTableClient.class);

//    public static void main(String[] args) {
//        new HiveTableClient().moveDataFromMysqlToHive("dws_b2b_car_com","20181123");
//    }

    public int moveDataFromMysqlToHive(String tableName, String ds) {
        dingDingAlert = new DingDingAlert();
        logger.info("hive 表执行任务开始--------");
        if (StringUtil.isEmpty(tableName)) {
            logger.info("请输入tableName ----------");
            return RollsroyceClientConstant.FAIL_CODE;
        }
        if (StringUtil.isEmpty(ds)) {
            logger.info("请输入ds ----------");
            return RollsroyceClientConstant.FAIL_CODE;
        }
        logger.info("参数验证完成，tableName = " + tableName + ", ds = " + ds);
        String biDBName = "db_bi";
        String partitionColumn = "ds";
        String realHiveTableName = biDBName + "." + tableName;
        //TODO 通过配置文件来读取wareHouseLocation
        //       String warehouseLocation = "hdfs://spark-test1:8020/apps/hive/warehouse"; //hdfs://spark-test1:8020/apps/hive/warehouse
        String warehouseLocation = "hdfs://mycluster/apps/hive/warehouse"; //hdfs://spark-test1:8020/apps/hive/warehouse
        String dbPath = warehouseLocation + "/" + biDBName + ".db";//
        String timestamp = GetTime.getTimeStamp("yyyyMMdd");
        String taskAppName = "hive_table_data_sync_" + tableName + "_" + ds + "_" + timestamp;
        int capacity = 1000;
        SparkSession sparkSession = SparkSession.builder()
                .appName(taskAppName)
                .master("local[4]")
                .config("executor-memory", "4G")
                .config("total-executor-cores", "4")
                .config("spark.cores.max", "4")
                .config("spark.sql.warehouse.dir", warehouseLocation)
                .enableHiveSupport()
                .getOrCreate();
        try {
            String dbTableName = "hive_" + tableName;
            //从mysql中生成DataFram
//            Dataset<Row> bizdateDS = sparkSession.read().jdbc(
//                    "jdbc:mysql://rds7fzjym7fzjymo.mysql.rds.aliyuncs.com:3306/db_bi?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&useSSL=false",
//                    dbTableName,
//                    RollsroyceClientUtil.buildBIDBProperties());
            Dataset<Row> bizdateDS = sparkSession.read().jdbc(
                    "jdbc:mysql://back-risk.mysql.rds.aliyuncs.com:3306/db_pasaat?useUnicode=true&characterEncoding=UTF-8&verifyServerCertificate=false&useSSL=false",
                    dbTableName,
                    RollsroyceClientUtil.buildBIDBProperties());

            StructType structType = bizdateDS.schema();
            StructField[] structFields = structType.fields();
            /*
            structField是StructType中的字段。
            param：name此字段的名称。
            param：dataType此字段的数据类型。
            param：nullable指示此字段的值是否为空值。
            param：metadata此字段的元数据。 如果未修改列的内容（例如，在选择中），则应在转换期间保留元数据。
             */
            String sourceType; //Name of the type used in JSON serialization.
            String columnName;
            String targetType;
            StructField structField;
            SparkDataTypeEnum sparkDataType;
            StringBuilder createBuilder = new StringBuilder(capacity);
            createBuilder.append("CREATE TABLE IF NOT EXISTS ").append(realHiveTableName).append(" (");
            List<String> dbTableColumns = Lists.newArrayList();
            Map<String, String> dbTableColumnTypeMap = Maps.newHashMap();
            //把Mysql中的每个字段都提取出来
            for (int i = 0, len = structFields.length; i < len; i++) {
                structField = structFields[i];
                sourceType = structField.dataType().typeName();
                columnName = structField.name();
                if (sourceType.contains("(")) {
                    sourceType = sourceType.substring(0, sourceType.indexOf("("));
                }
                sparkDataType = SparkDataTypeEnum.getItemByType(sourceType);
                if (null != sparkDataType) {
                    targetType = sparkDataType.getHiveDataType().getType();
                } else {
                    targetType = HiveDataTypeEnum.STRING.getType();
                }
                dbTableColumns.add(columnName);
                dbTableColumnTypeMap.put(columnName, targetType);
                if (i != 0) {
                    createBuilder.append(",");
                }
                createBuilder.append(columnName).append(" ").append(targetType);
            }
            createBuilder.append(") PARTITIONED by (").append(partitionColumn)
                    .append(" STRING) ").append("STORED AS PARQUET");
            String createDBSQL = "CREATE DATABASE IF NOT EXISTS db_bi LOCATION '" + dbPath + "'";
            String createTableSQL = createBuilder.toString();
            logger.info("建库脚本：" + createDBSQL);
            sparkSession.sql(createDBSQL);
            logger.info("建表脚本：" + createTableSQL);
            sparkSession.sql(createTableSQL);

            //JdbcUtil只是一个记录属性的类，不是Spark的类
            JdbcUtil jdbcUtil = JdbcUtil.custom()
                    .driver("org.apache.hive.jdbc.HiveDriver")
                    //.url("jdbc:hive2://spark-test2:10000/db_bi")
                    .url("jdbc:hive2://hadoop-server2:10000/db_bi")
                    .username("root")
                    .password("Apyb290ICAg")
                    .build();

            //获取建表脚本的hive表中的字段
            List<String> hiveTableColumns =
                    jdbcUtil.getTableColumns(tableName);
            if (null == hiveTableColumns) {
                logger.error("表" + tableName + "不存在，程序关闭");
                dingDingAlert.sendMsg("表" + tableName + "不存在，程序关闭");
                return RollsroyceClientConstant.FAIL_CODE;
            }
            List<String> alterColumns = Lists.newArrayList();

            //对比hive的表和mysql中的表的字段，如果hive中少了就加回去
            for (String dbTableColumn : dbTableColumns) {
                if (!hiveTableColumns.contains(dbTableColumn)) {
                    alterColumns.add(dbTableColumn);
                }
            }
            if (CollectionUtils.isNotEmpty(alterColumns)) {
                StringBuilder alterColumnsBuilder = new StringBuilder(capacity);
                alterColumnsBuilder.append("ALTER TABLE ").append(realHiveTableName)
                        .append(" ADD COLUMNS (");
                for (int i = 0, len = alterColumns.size(); i < len; i++) {
                    if (i != 0) {
                        alterColumnsBuilder.append(",");
                    }
                    columnName = alterColumns.get(i);
                    targetType = dbTableColumnTypeMap.get(columnName);
                    alterColumnsBuilder.append(columnName).append(" ").append(targetType);
                }
                alterColumnsBuilder.append(")");
                String alterColumnsSQL = alterColumnsBuilder.toString();
                logger.info("添加字段脚本：" + alterColumnsSQL);
//                sparkSession.sql(alterColumnsSQL);
                jdbcUtil.edit(alterColumnsSQL);
            }
            //导数
            String tmpTableName = tableName + "_" + ds;
            bizdateDS.createOrReplaceTempView(tmpTableName); //注意这里不是直接从mysql抽到hive，而是先从Mysql抽到内存中
            StringBuilder insertBuilder = new StringBuilder(capacity);
            insertBuilder.append("INSERT OVERWRITE TABLE ").append(realHiveTableName)
                    .append(" PARTITION (").append(partitionColumn).append(" = '")
                    .append(ds).append("') SELECT ");
//            descDS = sparkSession.sql("DESC " + realHiveTableName);
//            hiveTableColumns = RollsroyceSdkUtils.getSparkTableColumnNames(descDS);
            hiveTableColumns = jdbcUtil.getTableColumns(tableName);
            for (int i = 0, len = hiveTableColumns.size(); i < len; i++) {
                columnName = hiveTableColumns.get(i);
                if (!partitionColumn.equals(columnName)) {
                    if (i != 0) {
                        insertBuilder.append(",");
                    }
                    if (dbTableColumns.contains(columnName)) {
                        insertBuilder.append(columnName);
                    } else {
                        insertBuilder.append("NULL");
                    }
                }
            }
            insertBuilder.append(" FROM ").append(tmpTableName);
            //执行insert脚本
            String insertSql = insertBuilder.toString();
            logger.info("插入数据脚本：" + insertSql);
            sparkSession.sql(insertBuilder.toString());
            logger.info("HIVE导数完成");
            jdbcUtil.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
            dingDingAlert.sendMsg(e.getMessage());
            return RollsroyceClientConstant.FAIL_CODE;
        } finally {
            sparkSession.close();
        }
        return RollsroyceClientConstant.SUCCESS_CODE;
    }
}
