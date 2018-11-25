package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/9 上午11:45
 *
 * @author weifeng
 * @version 1.0.0
 */
public enum MysqlDataTypeEnum {
    
    BIT(
            "BIT",
            "A bit-value type. M indicates the number of bits per value, from 1 to 64.",
            SparkDataTypeEnum.BYTE,
            OdpsDateTypeEnum.TINYINT,
            HiveDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.TINYINT
    ),
    TINYINT(
            "TINYINT",
            "A very small integer. The signed range is -128 to 127. The unsigned range is 0 to 255",
            SparkDataTypeEnum.BYTE,
            OdpsDateTypeEnum.TINYINT,
            HiveDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.TINYINT
    ),
    SMALLINT(
            "SMALLINT",
            "A small integer. The signed range is -32768 to 32767. The unsigned range is 0 to 65535",
            SparkDataTypeEnum.SHORT,
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    MEDIUMINT(
            "MEDIUMINT",
            "A medium-sized integer. The signed range is -8388608 to 8388607. The unsigned range is 0 to 16777215.",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    INT(
            "INT",
            "A normal-size integer. The signed range is -2147483648 to 2147483647. The unsigned range is 0 to 4294967295.",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    INTEGER(
            "INTEGER",
            "A normal-size integer. The signed range is -2147483648 to 2147483647. The unsigned range is 0 to 4294967295.",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    BIGINT(
            "BIGINT",
            "A large integer. The signed range is -9223372036854775808 to 9223372036854775807. The unsigned range is 0 to 18446744073709551615.",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    DECIMAL(
            "DECIMAL",
            "",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            HiveDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    DEC(
            "DEC",
            "",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            HiveDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    
    FLOAT(
            "FLOAT",
            "",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    DOUBLE(
            "DOUBLE",
            "",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    
    DATE(
            "DATE",
            "",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),
    TIME(
            "TIME",
            "",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIME,
            PhoenixDataTypeEnum.TIME
    ),
    YEAR(
            "YEAR",
            "",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),
    DATETIME(
            "DATETIME",
            "",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    TIMESTAMP(
            "TIMESTAMP",
            "",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    
    CHAR(
            "CHAR",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.CHAR,
            HiveDataTypeEnum.CHAR,
            PostgreSqlDataTypeEnum.CHAR,
            PhoenixDataTypeEnum.CHAR
    ),
    VARCHAR(
            "VARCHAR",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            PostgreSqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    BINARY(
            "BINARY",
            "",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.BINARY
    ),
    VARBINARY(
            "VARBINARY",
            "",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.VARBINARY
    ),
    TINYTEXT(
            "TINYTEXT",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    TEXT(
            "TEXT",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    MEDIUMTEXT(
            "MEDIUMTEXT",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    LONGTEXT(
            "LONGTEXT",
            "",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    ;
    
    private String type;
    
    private String desc;
    
    private SparkDataTypeEnum sparkDataType;
    
    private OdpsDateTypeEnum odpsType;
    
    private HiveDataTypeEnum hiveDataType;
    
    private PostgreSqlDataTypeEnum postgreSqlDataType;
    
    private PhoenixDataTypeEnum phoenixDataType;

    MysqlDataTypeEnum(String type, String desc, 
                      SparkDataTypeEnum sparkDataType, 
                      OdpsDateTypeEnum odpsType, 
                      HiveDataTypeEnum hiveDataType, 
                      PostgreSqlDataTypeEnum postgreSqlDataType, 
                      PhoenixDataTypeEnum phoenixDataType) {
        this.type = type;
        this.desc = desc;
        this.sparkDataType = sparkDataType;
        this.odpsType = odpsType;
        this.hiveDataType = hiveDataType;
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

    public HiveDataTypeEnum getHiveDataType() {
        return hiveDataType;
    }

    public PostgreSqlDataTypeEnum getPostgreSqlDataType() {
        return postgreSqlDataType;
    }

    public PhoenixDataTypeEnum getPhoenixDataType() {
        return phoenixDataType;
    }
    
    public static MysqlDataTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (MysqlDataTypeEnum item : MysqlDataTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
