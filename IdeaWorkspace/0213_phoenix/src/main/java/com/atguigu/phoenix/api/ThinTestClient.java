package com.atguigu.phoenix.api;

import org.apache.phoenix.queryserver.client.ThinClientUtil;

import java.sql.*;

/**
 * '瘦'客户端
 */
public class ThinTestClient {
    public static void main(String[] args) throws SQLException {
        String url = ThinClientUtil.getConnectionUrl("hadoop202", 8765);
        System.out.println(url);
        Connection connection = DriverManager.getConnection(url);
        PreparedStatement pt = connection.prepareStatement("select * from \"popu\" ");
        ResultSet resultSet = pt.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1) +"\t"+ resultSet.getString(2));
        }
        connection.close();
    }
}
