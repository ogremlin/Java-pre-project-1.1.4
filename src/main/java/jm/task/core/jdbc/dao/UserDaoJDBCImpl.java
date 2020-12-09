package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "CREATE TABLE IF NOT EXISTS USER "
                    + "(id INTEGER PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(50), "
                    + "lastName VARCHAR (50), "
                    + "age SMALLINT)";
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE IF EXISTS USER";
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = String.format("INSERT INTO USER(name, lastName, age) VALUES ('%s', '%s', %d)", name, lastName, age);
            statement.executeUpdate(SQL);
            System.out.println(String.format("User c именем - %s добавлен в базу данных", name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM USER WHERE id = " + id;
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();

        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM USER";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                userList.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            userList.stream().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM USER";
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
