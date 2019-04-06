package com.zth.db;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * 操作数据库的工具类，封装DBUtils
 */
public class Db {

    private static QueryRunner queryRunner  = new QueryRunner();
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

    /**
     * 开启事务
     * @throws Exception
     */
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

    /**
     * 提交事务
     * @throws Exception
     */
    public static void commitTransaction() throws Exception {
        // 得到 TreadLocal 中的 connection
        Connection con = connectionThreadLocal.get();
        // 如果为空，说明没有开启事务
        if (con == null){
            throw  new Exception("没有开启事务，不能提交事务");
        }
        con.commit();
        con.close();
        // 将连接移除 ThreadLocal
        connectionThreadLocal.remove();
    }

    /**
     * 回滚事务
     * @throws Exception
     */
    public static void rollbackTransaction() throws Exception {
        // 得到 TreadLocal 中的 connection
        Connection con = connectionThreadLocal.get();
        // 如果为空，说明没有开启事务
        if (con == null){
            throw  new Exception("没有开启事务，不能回滚事务");
        }
        con.rollback();
        connectionThreadLocal.remove();

    }

    /**
     * 关闭事务
     * @param connection
     * @throws SQLException
     */
    public static void  releaseConnection(Connection connection) throws SQLException {
        // 得到 TreadLocal 中的 connection
        Connection con = connectionThreadLocal.get();
        //如果参数连接与当前事务连接不相等，则说明参数连接不是事务连接，可以关闭，否则交由事务关闭
        if (connection != null && con != connection){
            // 如果连接没有被关闭，关闭之
            if (!connection.isClosed()){
                connection.close();
            }
        }
    }


    public static void closeDataSoource(){
        if (null != ds){
            ds.close();
        }
    }

    //-----------------对数据库的操作-----------------

    //--------------重写 QueryRunner 中的方法----------

    public static int[] batch(String sql,Object[][] params) throws SQLException {
        Connection connection = getConnection();
        int[] result = queryRunner.batch(connection,sql,params);
        releaseConnection(connection);
        return result;
    }


    public static <T> T query(String sql, ResultSetHandler<T> resultSetHandler, Object... params) throws SQLException {

        Connection connection = getConnection();
        T result = queryRunner.query(connection,sql,resultSetHandler,params);
        releaseConnection(connection);
        return result;
    }

    public static <T> T query(String sql,ResultSetHandler<T> resultSetHandler) throws SQLException {
        Connection connection = getConnection();
        T result = queryRunner.query(connection,sql,resultSetHandler);
        releaseConnection(connection);
        return result;
    }

    public static int update (String sql,Object param) throws SQLException {
        Connection connection = getConnection();
        int result = queryRunner.update(connection,sql,param);
        releaseConnection(connection);
        return result;
    }

    public static int update(String sql,Object... params) throws SQLException {
        Connection connection = getConnection();
        int result = queryRunner.update(connection,sql,params);
        releaseConnection(connection);
        return result;
    }

    public static int update(String sql) throws SQLException {
        Connection connection = getConnection();
        int result = queryRunner.update(connection,sql);
        releaseConnection(connection);
        return result;
    }


}
