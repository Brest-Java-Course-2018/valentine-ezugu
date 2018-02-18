package com.epam.brest.course;

import java.sql.*;

public class DbUtils {

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        String databaseUrl = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(databaseUrl, "sa", "");
    }

    public void createUserTable(Connection connection) throws SQLException {
        System.out.println("app user table created ");
        System.out.println();

        String createTable = "CREATE TABLE app_user(" +
                "user_id INT NOT NULL AUTO_INCREMENT," +
                "login VARCHAR (225) NOT NULL," +
                "password VARCHAR(225) NOT NULL," +
                "description VARCHAR (225) NULL," +
                "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()) {
            //create a sql table
            statement.executeUpdate(createTable);
        }
    }

    public void deleteUser(Connection connection, int id) throws SQLException {
        System.out.println();
        System.out.println("delete user with id -> " + id);


        String deleteById = "DELETE FROM app_user \n" +
                "WHERE user_id = ?; ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        }

    }


    public void addUser(Connection connection,
                        String login, String password,
                        String description) throws SQLException {

        System.out.println(String.format("Add user : %s", login));

        String newUSer = "INSERT INTO app_user(login, password, description) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(newUSer)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, description);

            // returns number of changed column
            preparedStatement.executeUpdate();
        }
    }


    public void getUser(Connection connection) throws SQLException {
        System.out.println();
        System.out.println("-----Initial list of users-----");

        String getUserRecord = "SELECT user_id, login, description" +
                " From app_user ORDER BY user_id";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserRecord);

            while (resultSet.next()) {
                System.out.println(String.format("User : %s, %s, %s,",
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("description")));
            }
        }

    }

    public void getAllUsers(Connection connection) throws SQLException {
        System.out.println("--------users remaining after delete --------");
        System.out.println();

        String allUsers = "SELECT login, password FROM app_user";

        try (PreparedStatement preparedStatement1 = connection.prepareStatement(allUsers)) {
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {

                String name = rs.getString("login");
                String password2 = rs.getString("password");
                System.out.println(String.format("users : %s, %s,",name ,password2));

            }
        }
    }

    public void updateUser(Connection connection, int id, String login, String password, String description) throws SQLException {

        System.out.println("---------after update of table--------");
        System.out.println();

        String updateQuery = "UPDATE app_user SET password = ?,login = ?, description = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

            String allUsers = "SELECT login,password FROM app_user";

            try (PreparedStatement preparedStatement1 = connection.prepareStatement(allUsers)) {
                ResultSet resultSet = preparedStatement1.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("login");
                    String password1 = resultSet.getString("password");

                    System.out.println(String.format("User : %s, %s,",name ,password1));

                }

            }
        }

    }
}
