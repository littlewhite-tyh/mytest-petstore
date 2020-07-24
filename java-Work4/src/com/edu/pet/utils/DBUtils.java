package com.edu.pet.utils;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.*;

public class DBUtils {
    private static BasicDataSource basicDataSource = null;
    static {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("123456");
    }
//    //获取连接对象
//    public static Connection getConnection() {
//        String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=UTC";
//        String user = "root";
//        String password = "123456";
//        Connection connection = null;
//        try {
//            //注册驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //获取连接
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

    public static Connection getConnection() throws SQLException{
        Connection connection = basicDataSource.getConnection();
        return connection;
    }
    //集中关闭对象
    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //对dml语句的处理
    public static boolean executeSQL(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null) {
                //循环把参数值赋给？占位符
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            //如果返回受影响的行数大于0，则返回true，否则返回false
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeAll(null,preparedStatement,connection);
        }
        //如果有异常，返回false
        return false;
    }
}
