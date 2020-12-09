package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Вася", "Пупкин", (byte) 50);
        userDaoJDBC.saveUser("Петя", "Иванов", (byte) 40);
        userDaoJDBC.saveUser("Миша", "Сидоров", (byte) 30);
        userDaoJDBC.saveUser("Рома", "Абрамович", (byte) 20);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
