package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.impl.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.impl.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.dropUsersTable();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Вася", "Пупкин", (byte) 50);
        userDaoHibernate.saveUser("Петя", "Иванов", (byte) 40);
        userDaoHibernate.saveUser("Миша", "Сидоров", (byte) 30);
        userDaoHibernate.saveUser("Рома", "Абрамович", (byte) 20);
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();

/*
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Вася", "Пупкин", (byte) 50);
        userDaoJDBC.saveUser("Петя", "Иванов", (byte) 40);
        userDaoJDBC.saveUser("Миша", "Сидоров", (byte) 30);
        userDaoJDBC.saveUser("Рома", "Абрамович", (byte) 20);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
 */
    }
}
