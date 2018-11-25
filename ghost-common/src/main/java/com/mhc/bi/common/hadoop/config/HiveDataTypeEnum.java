package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/9 上午10:43
 *
 * @author weifeng
 * @version 1.0.0
 */
public enum HiveDataTypeEnum {

    TINYINT(
            "TINYINT", 
            "1-byte signed integer, from -128 to 127", 
            SparkDataTypeEnum.INTEGER, 
            OdpsDateTypeEnum.TINYINT,
            MysqlDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.TINYINT
    ),
    SMALLINT(
            "SMALLINT", 
            "2-byte signed integer, from -32,768 to 32,767",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    INT(
            "INT", 
            "4-byte signed integer, from -2,147,483,648 to 2,147,483,647",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    INTEGER(
            "INTEGER",
            "4-byte signed integer, from -2,147,483,648 to 2,147,483,647",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    BIGINT(
            "BIGINT",
            "8-byte signed integer, from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    FLOAT(
            "FLOAT",
            "4-byte single precision floating point number",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    DOUBLE(
            "DOUBLE",
            "8-byte double precision floating point number",
            SparkDataTypeEnum.DOUBLE,
            OdpsDateTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PostgreSqlDataTypeEnum.DOUBLE,
            PhoenixDataTypeEnum.DOUBLE
    ),
    DECIMAL(
            "DECIMAL",
            "introduced user-definable precision and scale",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            MysqlDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    NUMERIC(
            "NUMERIC",
            "introduced user-definable precision and scale",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            MysqlDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    
    TIMESTAMP(
            "TIMESTAMP",
            "",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    DATE(
            "DATE",
            "",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),
    INTERVAL(
            "INTERVAL",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.INTERVAL,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    STRING(
            "STRING",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    VARCHAR(
            "VARCHAR",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    CHAR(
            "CHAR",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.CHAR,
            MysqlDataTypeEnum.CHAR,
            PostgreSqlDataTypeEnum.CHAR,
            PhoenixDataTypeEnum.CHAR
    ),
    
    BOOLEAN(
            "BOOLEAN",
            "",
            SparkDataTypeEnum.BOOLEAN,
            OdpsDateTypeEnum.BOOLEAN,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.BOOLEAN,
            PhoenixDataTypeEnum.BOOLEAN
    ),
    BINARY(
            "BINARY",
            "",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.BINARY
    ),
    
    ARRAY(
            "ARRAY",
            "",
            SparkDataTypeEnum.ARRAY,
            OdpsDateTypeEnum.ARRAY,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.ARRAY
    ),
    MAP(
            "MAP",
            "",
            SparkDataTypeEnum.MAP,
            OdpsDateTypeEnum.MAP,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    STRUCT(
            "STRUCT",
            "",
            SparkDataTypeEnum.STRUCT,
            OdpsDateTypeEnum.STRUCT,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    UNIONTYPE(
            "UNIONTYPE",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    ;
    
    private String type;
    
    private String desc;
    
    private SparkDataTypeEnum sparkDataType;
    
    private OdpsDateTypeEnum odpsType;

    private MysqlDataTypeEnum mysqlDataType;
    
    private PostgreSqlDataTypeEnum postgreSqlDataType;
    
    private PhoenixDataTypeEnum phoenixDataType;

    HiveDataTypeEnum(String type, String desc, 
                     SparkDataTypeEnum sparkDataType, 
                     OdpsDateTypeEnum odpsType, 
                     MysqlDataTypeEnum mysqlDataType, 
                     PostgreSqlDataTypeEnum postgreSqlDataType, 
                     PhoenixDataTypeEnum phoenixDataType) {
        this.type = type;
        this.desc = desc;
        this.sparkDataType = sparkDataType;
        this.odpsType = odpsType;
        this.mysqlDataType = mysqlDataType;
        this.postgreSqlDataType = postgreSqlDataType;
        this.phoenixDataType = phoenixDataType;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public SparkDataTypeEnum getSparkDataType() {
        return sparkDataType;
    }

    public OdpsDateTypeEnum getOdpsType() {
        return odpsType;
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
    
    public static HiveDataTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (HiveDataTypeEnum item : HiveDataTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
