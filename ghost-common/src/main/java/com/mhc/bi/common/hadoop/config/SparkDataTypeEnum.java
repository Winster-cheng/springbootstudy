package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/9 上午10:57
 *
 * @author weifeng
 * @version 1.0.0
 */
public enum SparkDataTypeEnum {

    BYTE(
            "byte",
            "1字节有符号整数. 数值范围： -128 到 127",
            OdpsDateTypeEnum.TINYINT,
            HiveDataTypeEnum.TINYINT,
            MysqlDataTypeEnum.TINYINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.TINYINT
    ),
    SHORT(
            "short",
            "2字节有符号整数. 数值范围： -32768 到 32767",
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PostgreSqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    INTEGER(
            "integer",
            "4字节有符号整数. 数值范围： -2147483648 到 2147483647",
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PostgreSqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    LONG(
            "long",
            "8字节有符号整数. 数值范围： -9223372036854775808 到 9223372036854775807",
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PostgreSqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    FLOAT(
            "float",
            "4字节单精度浮点数",
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PostgreSqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    DOUBLE(
            "double",
            "8字节双精度浮点数",
            OdpsDateTypeEnum.DOUBLE,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PostgreSqlDataTypeEnum.DOUBLE,
            PhoenixDataTypeEnum.DOUBLE
    ),
    DECIMAL(
            "decimal",
            "任意精度的有符号十进制数",
            OdpsDateTypeEnum.DECIMAL,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DECIMAL,
            PostgreSqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DOUBLE
    ),

    STRING(
            "string",
            "字符串值",
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),

    BINARY(
            "binary",
            "字节序列值",
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PostgreSqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.BINARY
    ),

    BOOLEAN(
            "boolean",
            "字节序列值",
            OdpsDateTypeEnum.BOOLEAN,
            HiveDataTypeEnum.BOOLEAN,
            MysqlDataTypeEnum.VARCHAR,
            PostgreSqlDataTypeEnum.BOOLEAN,
            PhoenixDataTypeEnum.BOOLEAN
    ),

    TIMESTAMP(
            "timestamp",
            "包含的年、月、日、时、分和秒的时间值",
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.DATETIME,
            PostgreSqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    DATE(
            "date",
            "包含的年、月、日的日期值",
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PostgreSqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),

    ARRAY(
            "array",
            "包含一系列类型为elementType的元素。如果在一个将ArrayType值的元素可以为空值，containsNull指示是否允许为空",
            OdpsDateTypeEnum.ARRAY,
            HiveDataTypeEnum.ARRAY,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.ARRAY
    ),
    MAP(
            "map",
            "一系列键值对的集合。key不允许为空，valueContainsNull指示value是否允许为空",
            OdpsDateTypeEnum.MAP,
            HiveDataTypeEnum.MAP,
            MysqlDataTypeEnum.TEXT,
            PostgreSqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),

    STRUCT(
            "struct",
            "结构体",
            OdpsDateTypeEnum.STRUCT,
            HiveDataTypeEnum.STRUCT,
            null,
            null,
            null
    ),

    ;

    private String type;

    private String desc;

    private OdpsDateTypeEnum odpsDateType;

    private HiveDataTypeEnum hiveDataType;

    private MysqlDataTypeEnum mysqlDataType;

    private PostgreSqlDataTypeEnum postgreSqlDataType;

    private PhoenixDataTypeEnum phoenixDataType;

    SparkDataTypeEnum(String type, String desc,
                      OdpsDateTypeEnum odpsDateType,
                      HiveDataTypeEnum hiveDataType,
                      MysqlDataTypeEnum mysqlDataType,
                      PostgreSqlDataTypeEnum postgreSqlDataType,
                      PhoenixDataTypeEnum phoenixDataType) {
        this.type = type;
        this.desc = desc;
        this.odpsDateType = odpsDateType;
        this.hiveDataType = hiveDataType;
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

    public PhoenixDataTypeEnum getPhoenixDataType() {
        return phoenixDataType;
    }

    public static SparkDataTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (SparkDataTypeEnum item : SparkDataTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
