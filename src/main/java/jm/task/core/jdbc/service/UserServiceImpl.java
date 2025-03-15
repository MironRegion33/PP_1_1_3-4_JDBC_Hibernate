package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getMyConnection;

public class UserServiceImpl implements UserService {

    private static final String CREATE = """
                        CREATE TABLE IF NOT EXISTS user (
                            id INTEGER AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255),
                            last_name VARCHAR(255),
                            age TINYINT
                        )
            """;
    private static final String GET_USERS = """
                        SELECT *
                        FROM user
            """;
    private static final String DELETE = """
            DELETE FROM user
            WHERE id = ?
            """;


    public void createUsersTable() {

        try (Connection connection = Util.getMyConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE)) {
            statement.executeUpdate();
        } catch (SQLException ignored) {
        }
        System.out.println("Table created");
    }

    public void dropUsersTable() {

        try (Connection connection = getMyConnection();
             PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS user")) {
            statement.executeUpdate();
            System.out.println("Table dropped");
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = getMyConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO user (name, last_name, age) VALUES (?, ?, ?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = getMyConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            System.out.println("User с id - " + id + " удален из базы данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        try (Connection connection = getMyConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {

        try (Connection connection = getMyConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "TRUNCATE TABLE user")) {
            preparedStatement.executeUpdate();
            System.out.println("Table cleaned");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
