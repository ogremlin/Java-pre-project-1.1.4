package jm.task.core.jdbc.dao.impl;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.NoArgsConstructor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserDaoHibernateImpl implements UserDao {

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();

        String SQL = new StringBuilder()
                .append("CREATE TABLE IF NOT EXISTS USERS ")
                .append("(id INTEGER PRIMARY KEY AUTO_INCREMENT, ")
                .append("name VARCHAR(50), ")
                .append("lastName VARCHAR (50), ")
                .append("age SMALLINT)").toString();

       Query query = session.createSQLQuery(SQL).addEntity(User.class);
       session.getTransaction().commit();
       query.executeUpdate();
       session.close();
       Util.sessionFactoryClose();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        String SQL = "DROP TABLE IF EXISTS USERS";
        Query query = session.createSQLQuery(SQL).addEntity(User.class);
        session.getTransaction().commit();
        query.executeUpdate();
        session.close();
        Util.sessionFactoryClose();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.printf("User c именем - %s добавлен в базу данных%n", name);
        session.close();
        Util.sessionFactoryClose();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        Util.sessionFactoryClose();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory();
        final List<User> userList = session.createCriteria(User.class).list();
        userList.forEach(System.out::println);
        session.close();
        Util.sessionFactoryClose();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory();
        session.beginTransaction();
        final List<User> instances = session.createCriteria(User.class).list();
        for (User user : instances) {
            session.delete(user);
        }
        session.getTransaction().commit();
        session.close();
        Util.sessionFactoryClose();
    }
}
