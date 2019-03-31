package com.zth.db;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 操作数据库的工具类，封装DBUtils
 */
public class Db {

    private static QueryRunner run  = new QueryRunner();
    private static DruidDataSource ds = null;
    // 只放进行事务的 Connection
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    // 初始化连接池
    static {
        ResourceBundle res = ResourceBundle.getBundle("jdbc");

        ds = new DruidDataSource();
        ds.setUrl(res.getString("url"));
        ds.setDriverClassName(res.getString("driverClassName"));
        ds.setUsername(res.getString("username"));
        ds.setPassword(res.getString("password"));
        try {
            ds.setFilters(res.getString("filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ds.setMaxActive(Integer.parseInt(res.getString("maxActive")));
        ds.setInitialSize(Integer.parseInt(res.getString("initialSize")));
        ds.setMaxWait(Long.parseLong(res.getString("maxWait")));
        ds.setMinIdle(Integer.parseInt(res.getString("minIdle")));
    }

    /**
     * 通过 DataSource 得到 Connection
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 得到 ThreadLocal 中的 connection
        Connection con = connectionThreadLocal.get();
        // 如果开启了事务，则 con 不为空，应该直接返回
       /* if (null != con){
            return con;
        }*/

       if (null == con){
           con = ds.getConnection();
           connectionThreadLocal.set(con);
       }
       return con;
    }

    //------------事务操作------------------

    public static void beginTransation() throws Exception {
        // 得到 TreeadLocal 中的 connection
        Connection con = connectionThreadLocal.get();
        // 判断是否为空，如果不为空，则说明事务已经开启
        if (con != null){
            throw new Exception("事务已经开启，不能重复开启事务");
        }
        // 如果不为空，则开启事务
        con = getConnection();
        // 设置事务提交为手动
        con.setAutoCommit(false);
        connectionThreadLocal.set(con);
    }



}
