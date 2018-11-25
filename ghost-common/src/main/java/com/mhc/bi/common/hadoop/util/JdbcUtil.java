package com.mhc.bi.common.hadoop.util;

import com.google.common.collect.Lists;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoshuai on 2016/11/1.
 */
public class JdbcUtil {

    private Connection connection;

    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    private static Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

    private JdbcUtil(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
        try {
            System.out.println("---------- 加载驱动 ----------");
            Class.forName(this.driver);
            System.out.println("---------- 注册驱动成功！ ----------");
            connect();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public void connect() throws SQLException {
        connect(url, username, password);
    }

    public boolean isNullConnection() {
        return null == connection;
    }

    /**
     * 获取对象连接
     * @param url 连接地址
     * @param username 用户名
     * @param password 密码
     * @throws SQLException
     */
    public void connect(String url, String username, String password) throws SQLException {
        logger.info("---------- 连接数据库 ----------");
        connection = DriverManager.getConnection(url, username, password);
        logger.info("---------- 数据库连接成功 ----------");
    }

    /**
     * 查询列表数据
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> select(String sql, Class<T> clazz, Object... params) throws Exception {
        logger.info("---------- 查询开始 ----------");
        if (StringUtil.isEmpty(sql) || sql.length() < 6 || clazz == null) {
            logger.error("---------- 验证sql或者clazz为空 ----------");
            return null;
        }
        sql = sql.trim();
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return null;
        }
        logger.info("---------- SQL : " + sql + " ----------");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int i = 0, len = params.length; i < len; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
//            if (null == rs) {
//                System.err.println("---------- 获取结果集错误 ----------");
//                return null;
//            }
            List<T> list = new ArrayList<>();
            T t;
            while (rs.next()) {
                t = ReflectionUtil.createObj(rs, clazz);
                if (null != t) {
                    list.add(t);
                }
            }
            ps.close();
            ps = null;
            rs.close();
            rs = null;
            logger.info("---------- 查询成功 ----------");
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != ps) {
                ps.close();
            }
            if (null != rs) {
                rs.close();
            }
        }
    }

    public List<String> selectColumnData(String sql, Object... params) throws Exception {
        logger.info("---------- 查询开始 ----------");
        if (StringUtil.isEmpty(sql) || sql.length() < 6) {
            logger.error("---------- 验证sql ----------");
            return null;
        }
        sql = sql.trim();
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return null;
        }
        System.out.println("---------- SQL : " + sql + " ----------");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int i = 0, len = params.length; i < len; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
//            if (null == rs) {
//                System.err.println("---------- 获取结果集错误 ----------");
//                return null;
//            }
            List<String> list = new ArrayList<>();
            String value;
            while (rs.next()) {
                value = rs.getString(1);
                if (null != value) {
                    list.add(value);
                }
            }
            ps.close();
            ps = null;
            rs.close();
            rs = null;
            logger.info("---------- 查询成功 ----------");
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != ps) {
                ps.close();
            }
            if (null != rs) {
                rs.close();
            }
        }
    }

    /**
     * 按sql查询内容,返回List<Map>类型
     * author:展天
     * @param sql
     * @param params
     */
    public List<Map<String, Object>> selectMapList(String sql, Object... params) throws Exception {
        logger.info("---------- 查询开始 ----------");
        if (StringUtil.isEmpty(sql) || sql.length() < 6) {
            logger.error("---------- 验证sql为空 ----------");
            return null;
        }
        sql = sql.trim();
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return null;
        }
        logger.info("---------- SQL : " + sql + " ----------");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int i = 0, len = params.length; i < len; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
//            if (null == rs) {
//                System.err.println("---------- 获取结果集错误 ----------");
//                return null;
//            }
            List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> row;
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                row = new LinkedHashMap();
                for(int i = 1;i <= metaData.getColumnCount();i++){
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
            ps.close();
            ps = null;
            rs.close();
            rs = null;
            logger.info("---------- 查询成功 ----------");
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != ps) {
                ps.close();
            }
            if (null != rs) {
                rs.close();
            }
        }
    }

    /**
     * 查询单个数据
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public Object select(String sql, Object... params) throws Exception {
        logger.info("---------- 查询开始 ----------");
        if (StringUtil.isEmpty(sql) || sql.length() < 6) {
            logger.error("---------- 验证sql为空 ----------");
            return null;
        }
        sql = sql.trim();
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return null;
        }
        logger.info("---------- SQL : " + sql + " ----------");
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int i = 0, len = params.length; i < len; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            rs = ps.executeQuery();
//            if (null == rs) {
//                System.err.println("---------- 获取结果集错误 ----------");
//                return null;
//            }
            Object result = rs.next() ? rs.getObject(1) : null;
            ps.close();
            ps = null;
            rs.close();
            rs = null;
            logger.info("---------- 查询成功 ----------");
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != ps) {
                ps.close();
            }
            if (null != rs) {
                rs.close();
            }
        }
    }

    /**
     * 删改查
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public int edit(String sql, Object... params) throws Exception {
        logger.info("---------- 执行修改SQL开始 ----------");
        if (StringUtil.isEmpty(sql)) {
            logger.error("---------- 验证sql或者clazz为空 ----------");
            return -1;
        }
        sql = sql.trim();
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return -1;
        }
        System.out.println("---------- SQL : " + sql + " ----------");
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            if (null != params && params.length > 0) {
                for (int i = 0, len = params.length; i < len; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            int hasEffect = ps.executeUpdate();
            ps.close();
            ps = null;
            logger.info("---------- 执行修改SQL完成 ----------");
            return hasEffect;
        } catch (Exception e) {
            throw e;
        } finally {
            if (null != ps) {
                ps.close();
            }
        }
    }

    /**
     * 验证表是否存在
     * @param tableName
     * @return
     * @throws SQLException
     */
    public boolean validateTableExist(String tableName) throws SQLException {
        boolean flag;
        if (null == connection) {
            logger.error("---------- connection为空，不能查询 ----------");
            return false;
        }
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            String[] type = {"TABLE"};
            ResultSet rs = metaData.getTables(null, null, tableName, type);
            flag = rs.next();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        return flag;
    }

    public List<String> getTableColumns(String tableName) throws SQLException {
        ResultSet rs = null;
        try {
            if (!validateTableExist(tableName)) {
                logger.error("表：" + tableName + " 不存在");
                return null;
            }
            DatabaseMetaData metaData = connection.getMetaData();
            rs = metaData.getColumns(null, null, tableName.toUpperCase(), "%");
            List<String> columns = Lists.newArrayList();
            while (rs.next()) {
                columns.add(rs.getString("COLUMN_NAME").toLowerCase());
            }
            return columns;
        } catch(SQLException e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            if (null != rs) {
                rs.close();
            }
        }
    }

    /**
     * 关闭连接
     * @throws SQLException
     */
    public void close() throws SQLException {
        if (null != connection) {
            connection.close();
        }
    }

    public void commit() throws SQLException {
        if (null != connection) {
            connection.commit();
        }
    }

    public static Builder custom() {
        return new Builder();
    }

    public static class Builder {

        private String driver;
        private String url;
        private String username;
        private String password;

        public Builder() {
            this.driver = null;
            this.url = null;
            this.username = null;
            this.password = null;
        }

        public Builder driver(String driver) {
            this.driver = driver;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public JdbcUtil build() {
            return new JdbcUtil(this.driver, this.url, this.username, this.password);
        }
    }

}
