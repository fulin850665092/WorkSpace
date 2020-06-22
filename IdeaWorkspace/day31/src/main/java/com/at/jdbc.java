package com.at;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class jdbc
{
    public static void main(String[] args) throws Exception {

        InputStream rs = jdbc.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties properties= new Properties();
        properties.load(rs);

        String url = properties.getProperty("url");
        String password = properties.getProperty("password");
        String username = properties.getProperty("username");
        Class.forName(properties.getProperty("drive"));
        Connection connection = DriverManager.getConnection(url, username,password);
        System.out.println(connection);

        connection.close();

    }
}
