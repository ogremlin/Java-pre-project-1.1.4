package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.*;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.transaction.UserTransaction;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/JAVAMENTOR?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String USER = "user";
    private static final String PASS = "1";
    private static SessionFactory sessionFactory;

    public static Session getSessionFactory() {
            try {
                Properties prop= new Properties();
                prop.setProperty("hibernate.connection.url", DB_URL);
                prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                prop.setProperty("hibernate.connection.username", USER);
                prop.setProperty("hibernate.connection.password", PASS);
                prop.setProperty("hibernate.connection.driver_class", JDBC_DRIVER);
                prop.setProperty("hibernate.connection.pool_size", "1");
                prop.setProperty("hibernate.show_sql", "true");

                Configuration configuration = new Configuration().addProperties(prop).addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        return sessionFactory.openSession();
    }

    public static void sessionFactoryClose() {
        sessionFactory.close();
    }


    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
        return connection;
    }

}
