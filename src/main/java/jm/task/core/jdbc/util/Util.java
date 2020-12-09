package jm.task.core.jdbc.util;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class Util {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/JAVAMENTOR?serverTimezone=Europe/Moscow&useSSL=false";
    static final String USER = "user";
    static final String PASS = "1";

    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }
}
