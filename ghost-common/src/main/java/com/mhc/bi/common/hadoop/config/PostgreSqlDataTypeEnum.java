package com.mhc.bi.common.hadoop.config;


import com.mhc.bi.common.hadoop.util.StringUtil;

/**
 * DESCRIPTION :
 * DATETIME : 2018/1/9 下午2:46
 *
 * @author weifeng
 * @version 1.0.0
 */
public enum PostgreSqlDataTypeEnum {

    SMALLINT(
            "int2",
            "signed two-byte integer",
            SparkDataTypeEnum.SHORT,
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    INTEGER(
            "int4",
            "signed four-byte integer",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    BIGINT(
            "int8",
            "signed eight-byte integer",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    DECIMAL(
            "decimal",
            "exact numeric of selectable precision",
            SparkDataTypeEnum.DECIMAL,
            OdpsDateTypeEnum.DECIMAL,
            HiveDataTypeEnum.DECIMAL,
            MysqlDataTypeEnum.DECIMAL,
            PhoenixDataTypeEnum.DECIMAL
    ),
    REAL(
            "float4",
            "single precision floating-point number (4 bytes)",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    FLOAT(
            "float4",
            "single precision floating-point number (4 bytes)",
            SparkDataTypeEnum.FLOAT,
            OdpsDateTypeEnum.FLOAT,
            HiveDataTypeEnum.FLOAT,
            MysqlDataTypeEnum.FLOAT,
            PhoenixDataTypeEnum.FLOAT
    ),
    DOUBLE(
            "float8",
            "double precision floating-point number (8 bytes)",
            SparkDataTypeEnum.DOUBLE,
            OdpsDateTypeEnum.DOUBLE,
            HiveDataTypeEnum.DOUBLE,
            MysqlDataTypeEnum.DOUBLE,
            PhoenixDataTypeEnum.DOUBLE
    ),
    SMALLSERIAL(
            "serial2",
            "auto incrementing two-byte integer",
            SparkDataTypeEnum.SHORT,
            OdpsDateTypeEnum.SMALLINT,
            HiveDataTypeEnum.SMALLINT,
            MysqlDataTypeEnum.SMALLINT,
            PhoenixDataTypeEnum.SMALLINT
    ),
    SERIAL(
            "serial4",
            "auto incrementing four-byte integer",
            SparkDataTypeEnum.INTEGER,
            OdpsDateTypeEnum.INT,
            HiveDataTypeEnum.INTEGER,
            MysqlDataTypeEnum.INTEGER,
            PhoenixDataTypeEnum.INTEGER
    ),
    BIGSERIAL(
            "serial8",
            "auto incrementing eight-byte integer",
            SparkDataTypeEnum.LONG,
            OdpsDateTypeEnum.BIGINT,
            HiveDataTypeEnum.BIGINT,
            MysqlDataTypeEnum.BIGINT,
            PhoenixDataTypeEnum.BIGINT
    ),
    
    MONEY(
            "money",
            "currency amount",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),

    CHAR(
            "char",
            "fixed-length character string",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.CHAR,
            HiveDataTypeEnum.CHAR,
            MysqlDataTypeEnum.CHAR,
            PhoenixDataTypeEnum.CHAR
    ),
    VARCHAR(
            "varchar",
            "variable-length character string",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    TEXT(
            "text",
            "variable-length character string",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    BINARY(
            "bytea",
            "binary data (byte array)",
            SparkDataTypeEnum.BINARY,
            OdpsDateTypeEnum.BINARY,
            HiveDataTypeEnum.BINARY,
            MysqlDataTypeEnum.BINARY,
            PhoenixDataTypeEnum.BINARY
    ),

    DATE(
            "date",
            "calendar date (year, month, day)",
            SparkDataTypeEnum.DATE,
            OdpsDateTypeEnum.DATE,
            HiveDataTypeEnum.DATE,
            MysqlDataTypeEnum.DATE,
            PhoenixDataTypeEnum.DATE
    ),
    TIME(
            "time",
            "time of day (no time zone)",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.DATETIME,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIME,
            PhoenixDataTypeEnum.TIME
    ),
    TIMEZ(
            "timez",
            "time of day, including time zone",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIME,
            PhoenixDataTypeEnum.TIME
    ),
    TIMESTAMP(
            "timestamp",
            "date and time (no time zone)",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    TIMESTAMPZ(
            "timestampz",
            "date and time, including time zone",
            SparkDataTypeEnum.TIMESTAMP,
            OdpsDateTypeEnum.TIMESTAMP,
            HiveDataTypeEnum.TIMESTAMP,
            MysqlDataTypeEnum.TIMESTAMP,
            PhoenixDataTypeEnum.TIMESTAMP
    ),
    INTERVAL(
            "interval",
            "time span",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.STRING,
            HiveDataTypeEnum.STRING,
            MysqlDataTypeEnum.TEXT,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    BOOLEAN(
            "bool",
            "logical Boolean (true/false)",
            SparkDataTypeEnum.BOOLEAN,
            OdpsDateTypeEnum.BOOLEAN,
            HiveDataTypeEnum.BOOLEAN,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.BOOLEAN
    ),
    
    POINT(
            "point",
            "geometric point on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    LINE(
            "line",
            "infinite line on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    LSEG(
            "lseg",
            "line segment on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    BOX(
            "box",
            "rectangular box on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    PATH(
            "path",
            "geometric path on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    POLYGON(
            "polygon",
            "closed geometric path on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    CIRCLE(
            "circle",
            "circle on a plane",
            null,
            null,
            null,
            null,
            null
    ),
    
    CIDR(
            "cidr",
            "IPv4 or IPv6 network address",
            null,
            null,
            null,
            null,
            null
    ),
    INET(
            "inet",
            "IPv4 or IPv6 host address",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    MACADDR(
            "macaddr",
            "MAC (Media Access Control) address",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    BITSTRING(
            "bit",
            "fixed-length bit string",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    VARBITSTRING(
            "varbit",
            "variable-length bit string",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    TSQUERY(
            "tsquery",
            "text search query",
            null,
            null,
            null,
            null,
            null
    ),
    TSVECTOR(
            "tsvector",
            "text search document",
            null,
            null,
            null,
            null,
            null
    ),
    
    UUID(
            "uuid",
            "universally unique identifier",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    XML(
            "xml",
            "XML data",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    
    JSON(
            "json",
            "textual JSON data",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    JSONB(
            "jsonb",
            "binary JSON data, decomposed",
            SparkDataTypeEnum.STRING,
            OdpsDateTypeEnum.VARCHAR,
            HiveDataTypeEnum.VARCHAR,
            MysqlDataTypeEnum.VARCHAR,
            PhoenixDataTypeEnum.VARCHAR
    ),
    ;
    
    private String type;
    
    private String desc;
    
    private SparkDataTypeEnum sparkDataType;
    
    private OdpsDateTypeEnum odpsDateType;
    
    private HiveDataTypeEnum hiveDataType;
    
    private MysqlDataTypeEnum mysqlDataType;
    
    private PhoenixDataTypeEnum phoenixDataType;

    PostgreSqlDataTypeEnum(String type, String desc, 
                           SparkDataTypeEnum sparkDataType, 
                           OdpsDateTypeEnum odpsDateType, 
                           HiveDataTypeEnum hiveDataType, 
                           MysqlDataTypeEnum mysqlDataType, 
                           PhoenixDataTypeEnum phoenixDataType) {
        this.type = type;
        this.desc = desc;
        this.sparkDataType = sparkDataType;
        this.odpsDateType = odpsDateType;
        this.hiveDataType = hiveDataType;
        this.mysqlDataType = mysqlDataType;
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

    public OdpsDateTypeEnum getOdpsDateType() {
        return odpsDateType;
    }

    public HiveDataTypeEnum getHiveDataType() {
        return hiveDataType;
    }

    public MysqlDataTypeEnum getMysqlDataType() {
        return mysqlDataType;
    }

    public PhoenixDataTypeEnum getPhoenixDataType() {
        return phoenixDataType;
    }

    public static PostgreSqlDataTypeEnum getItemByType(String type) {
        if (StringUtil.isEmpty(type)) {
            return null;
        }
        for (PostgreSqlDataTypeEnum item : PostgreSqlDataTypeEnum.values()) {
            if (item.type.toLowerCase().equals(type.toLowerCase())) {
                return item;
            }
        }
        return null;
    }
}
