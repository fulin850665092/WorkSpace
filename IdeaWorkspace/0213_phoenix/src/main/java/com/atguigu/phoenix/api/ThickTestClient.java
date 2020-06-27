package com.atguigu.phoenix.api;

import java.sql.*;
import java.util.Properties;

/**
 * '盘客户端'
 */
public class ThickTestClient {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:phoenix:hadoop202,hadoop203,hadoop204:2181";
        System.out.println(url);
        Properties properties = new Properties();
        properties.put("phoenix.schema.isNamespaceMappingEnabled","true");
        Connection connection = DriverManager.getConnection(url,properties);
        PreparedStatement pt = connection.prepareStatement("select * from \"popu\" ");
        ResultSet resultSet = pt.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) +"\t"+ resultSet.getString(2));
        }
        connection.close();
    }
}
