package jm.task.core.jdbc.dao.impl;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = new StringBuilder()
                    .append("CREATE TABLE IF NOT EXISTS USERS ")
                    .append("(id INTEGER PRIMARY KEY AUTO_INCREMENT, ")
                    .append("name VARCHAR(50), ")
                    .append("lastName VARCHAR (50), ")
                    .append("age SMALLINT)").toString();
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "DROP TABLE IF EXISTS USERS";
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = new StringBuilder()
                    .append("INSERT INTO USERS(name, lastName, age) VALUES ('").append(name)
                    .append("', '").append(lastName)
                    .append("', ").append(age)
                    .append(")").toString();
            statement.executeUpdate(SQL);
            System.out.println(String.format("User c именем - %s добавлен в базу данных", name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "DELETE FROM USERS WHERE id = " + id;
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        User user = new User();

        try (Connection connection = Util.getMySQLConnection(); Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM USERS";
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
            String SQL = "DELETE FROM USERS";
            statement.executeUpdate(SQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
