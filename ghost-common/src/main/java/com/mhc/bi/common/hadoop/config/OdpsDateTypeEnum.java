package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * Created by zhaoshuai on 2017/1/4.
 */
public enum OdpsDateTypeEnum {

    BIGINT(
            "BIGINT", 
            "8字节有符号整型",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    DOUBLE(
            "DECIMAL", 
            "8字节有符号浮点型",
            SparkDataTypeEnum.DOUBLE,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PostgreSqlDataTypeEnum.DOUBLE,
            PhoenixDataTypeEnum.DOUBLE
    ),
    STRING(
            "VARCHAR", 
            "字符串类型",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    DECIMAL(
            "DECIMAL", 
            "精确小数类型",
            SparkDataTypeEnum.DECIMAL,
            HiveDataTypeEnum.DECIMAL,
            MysqlDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    BOOLEAN(
            "BOOLEAN", 
            "布尔型",
            SparkDataTypeEnum.BOOLEAN,
            HiveDataTypeEnum.BOOLEAN,
            MysqlDataTypeEnum.BIT,
            PostgreSqlDataTypeEnum.BOOLEAN,
            PhoenixDataTypeEnum.BOOLEAN
    ),
    DATETIME(
            "DATETIME", 
            "日期类型",
            SparkDataTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.DATETIME,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    MAP(
            "MAP", 
            "MAP类型",
            SparkDataTypeEnum.MAP,
            HiveDataTypeEnum.MAP,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    ARRAY(
            "ARRAY", 
            "ARRAY类型",
            SparkDataTypeEnum.ARRAY,
            HiveDataTypeEnum.ARRAY,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.ARRAY
    ),
    VOID(
            "VOID", 
            "VOID类型",
            null,
            null,
            null,
            null,
            null
    ),
    TINYINT(
            "TINYINT", 
            "1字节有符号整型",
            SparkDataTypeEnum.INTEGER,
            HiveDataTypeEnum.TINYINT,
            MysqlDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.TINYINT
    ),
    SMALLINT(
            "SMALLINT", 
            "2字节有符号整型",
            SparkDataTypeEnum.INTEGER,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    INT(
            "INT", 
            "4字节有符号整型",
            SparkDataTypeEnum.INTEGER,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    FLOAT(
            "FLOAT", 
            "单精度浮点",
            SparkDataTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    CHAR(
            "CHAR", 
            "固定长度字符串",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.CHAR,
            MysqlDataTypeEnum.CHAR,
            PostgreSqlDataTypeEnum.CHAR,
            PhoenixDataTypeEnum.CHAR
    ),
    VARCHAR(
            "VARCHAR", 
            "可变长度字符串",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    DATE(
            "DATE", 
            "时间类型",
            SparkDataTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),
    TIMESTAMP(
            "TIMESTAMP", 
            "时间戳",
            SparkDataTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    BINARY(
            "BINARY", 
            "字节数组",
            SparkDataTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.BINARY
    ),
    INTERVAL_DAY_TIME(
            "INTERVAL_DAY_TIME", 
            "日期间隔",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.INTERVAL,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.INTERVAL,
            PhoenixDataTypeEnum.VARCHAR
    ),
    INTERVAL_YEAR_MONTH(
            "INTERVAL_YEAR_MONTH", 
            "年份间隔",
            SparkDataTypeEnum.STRING,
            HiveDataTypeEnum.INTERVAL,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.INTERVAL,
            PhoenixDataTypeEnum.VARCHAR
    ),
    STRUCT(
            "STRUCT", 
            "结构体",
            SparkDataTypeEnum.STRUCT,
            HiveDataTypeEnum.STRUCT,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    ;

    private String type;
    
    private String desc;
    
    private SparkDataTypeEnum sparkDataType;
    
    private HiveDataTypeEnum hiveDataType;
    
    private MysqlDataTypeEnum mysqlDataType;
    
    private PostgreSqlDataTypeEnum postgreSqlDataType;
    
    private PhoenixDataTypeEnum phoenixDataType;

    OdpsDateTypeEnum(String type, String desc, 
                     SparkDataTypeEnum sparkDataType, 
                     HiveDataTypeEnum hiveDataType, 
                     MysqlDataTypeEnum mysqlDataType, 
                     PostgreSqlDataTypeEnum postgreSqlDataType, 
                     PhoenixDataTypeEnum phoenixDataType) {
        this.type = type;
        this.desc = desc;
        this.sparkDataType = sparkDataType;
        this.hiveDataType = hiveDataType;
        this.mysqlDataType = mysqlDataType;
        this.postgreSqlDataType = postgreSqlDataType;
        this.phoenixDataType = phoenixDataType;
    }

    public String getDesc() {
        return desc;
    }

    public String getType() {
        return type;
    }

    public SparkDataTypeEnum getSparkDataType() {
        return sparkDataType;
    }

    public HiveDataTypeEnum getHiveDataType() {
        return hiveDataType;
    }

    public MysqlDataTypeEnum getMysqlDataType() {
        return mysqlDataType;
    }

    public PostgreSqlDataTypeEnum getPostgreSqlDataType() {
        return postgreSqlDataType;
    }

    public PhoenixDataTypeEnum getPhoenixDataType() {
        return phoenixDataType;
    }

    public static OdpsDateTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (OdpsDateTypeEnum item : OdpsDateTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
