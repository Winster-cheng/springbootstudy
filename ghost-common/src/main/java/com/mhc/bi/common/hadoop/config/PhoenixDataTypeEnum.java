package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/9 上午11:42
 *
 * @author weifeng
 * @version 1.0.0
 */
public enum PhoenixDataTypeEnum {
    
    INTEGER(
            "INTEGER",
            "Possible values: -2147483648 to 2147483647",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER
    ),
    UNSIGNED_INT(
            "UNSIGNED_INT",
            "Possible values: 0 to 2147483647",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER
    ),
    BIGINT(
            "BIGINT",
            "Possible values: -9223372036854775808 to 9223372036854775807",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT
    ),
    UNSIGNED_LONG(
            "UNSIGNED_LONG",
            "Possible values: 0 to 9223372036854775807",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT
    ),
    TINYINT(
            "TINYINT",
            "Possible values: -128 to 127",
            SparkDataTypeEnum.BYTE,
            OdpsDateTypeEnum.TINYINT,
            HiveDataTypeEnum.TINYINT,
            MysqlDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT
    ),
    UNSIGNED_TINYINT(
            "UNSIGNED_TINYINT",
            "Possible values: 0 to 127",
            SparkDataTypeEnum.BYTE,
            OdpsDateTypeEnum.TINYINT,
            HiveDataTypeEnum.TINYINT,
            MysqlDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT
    ),
    SMALLINT(
            "SMALLINT",
            "Possible values: -32768 to 32767",
            SparkDataTypeEnum.SHORT,
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT
    ),
    UNSIGNED_SMALLINT(
            "UNSIGNED_SMALLINT",
            "Possible values: 0 to 32767",
            SparkDataTypeEnum.SHORT,
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT
    ),
    FLOAT(
            "FLOAT",
            "Possible values: -3.402823466 E + 38 to 3.402823466 E + 38",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT
    ),
    UNSIGNED_FLOAT(
            "UNSIGNED_FLOAT",
            "Possible values: 0 to 3.402823466 E + 38",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT
    ),
    DOUBLE(
            "DOUBLE",
            "Possible values: -1.7976931348623158 E + 308 to 1.7976931348623158 E + 308",
            SparkDataTypeEnum.DOUBLE,
            OdpsDateTypeEnum.DOUBLE,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PostgreSqlDataTypeEnum.DOUBLE
    ),
    UNSIGNED_DOUBLE(
            "UNSIGNED_DOUBLE",
            "Possible values: 0 to 1.7976931348623158 E + 308",
            SparkDataTypeEnum.DOUBLE,
            OdpsDateTypeEnum.DOUBLE,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PostgreSqlDataTypeEnum.DOUBLE
    ),
    DECIMAL(
            "DECIMAL",
            "Data type with fixed precision and scale",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            HiveDataTypeEnum.DECIMAL,
            MysqlDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL
    ),
    BOOLEAN(
            "BOOLEAN",
            "Possible values: TRUE and FALSE",
            SparkDataTypeEnum.BOOLEAN,
            OdpsDateTypeEnum.BOOLEAN,
            HiveDataTypeEnum.BOOLEAN,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.BOOLEAN
    ),
    TIME(
            "TIME",
            "The time data type",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIME,
            PostgreSqlDataTypeEnum.TIME
    ),
    DATE(
            "DATE",
            "The date data type",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE
    ),
    TIMESTAMP(
            "TIMESTAMP",
            "The timestamp data type",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP
    ),
    UNSIGNED_TIME(
            "UNSIGNED_TIME",
            "The time data type",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIME,
            PostgreSqlDataTypeEnum.TIME
    ),
    UNSIGNED_DATE(
            "UNSIGNED_DATE",
            "The date data type",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE
    ),
    UNSIGNED_TIMESTAMP(
            "UNSIGNED_TIMESTAMP",
            "The timestamp data type",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP
    ),
    VARCHAR(
            "VARCHAR",
            "A variable length String with an optional max byte length",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.VARCHAR
    ),
    CHAR(
            "CHAR",
            "A fixed length String with single-byte characters",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.CHAR,
            HiveDataTypeEnum.CHAR,
            MysqlDataTypeEnum.CHAR,
            PostgreSqlDataTypeEnum.CHAR
    ),
    BINARY(
            "BINARY",
            "Raw fixed length byte array",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY
    ),
    VARBINARY(
            "VARBINARY",
            "Raw variable length byte array",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY
    ),
    ARRAY(
            "ARRAY",
            "Raw variable length byte array",
            SparkDataTypeEnum.ARRAY,
            OdpsDateTypeEnum.ARRAY,
            HiveDataTypeEnum.ARRAY,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT
    ),
    ;
    
    private String type;
    
    private String desc;
    
    private SparkDataTypeEnum sparkDataType;
    
    private OdpsDateTypeEnum odpsDateType;
    
    private HiveDataTypeEnum hiveDataType;
    
    private MysqlDataTypeEnum mysqlDataType;
    
    private PostgreSqlDataTypeEnum postgreSqlDataType;

    PhoenixDataTypeEnum(String type, String desc, 
                        SparkDataTypeEnum sparkDataType, 
                        OdpsDateTypeEnum odpsDateType, 
                        HiveDataTypeEnum hiveDataType, 
                        MysqlDataTypeEnum mysqlDataType, 
                        PostgreSqlDataTypeEnum postgreSqlDataType) {
        this.type = type;
        this.desc = desc;
        this.sparkDataType = sparkDataType;
        this.odpsDateType = odpsDateType;
        this.hiveDataType = hiveDataType;
        this.mysqlDataType = mysqlDataType;
        this.postgreSqlDataType = postgreSqlDataType;
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

    public OdpsDateTypeEnum getOdpsDateType() {
        return odpsDateType;
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

    public static PhoenixDataTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (PhoenixDataTypeEnum item : PhoenixDataTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
