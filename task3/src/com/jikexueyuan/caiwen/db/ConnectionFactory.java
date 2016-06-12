package com.jikexueyuan.caiwen.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by caiwen on 2016/6/11.
 */
public class ConnectionFactory {

    private static String driver;
    private static String dbUrl;
    private static String user;
    private static String password;
    private Connection connection;
    private static ConnectionFactory connectionFactory;

    static {
        try {
            InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            dbUrl = properties.getProperty("dbUrl");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
