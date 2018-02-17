package com.epam.brest.course;


import java.sql.*;

public class DbUtils {

    private Statement statement;

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
        try {

            statement = connection.createStatement();
            //can create a sql table
           statement.executeUpdate(createTable);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
    }

    public void deleteUser(Connection connection, int id) throws SQLException{
        System.out.println();
        System.out.println("delete user with id -> " +id);

        PreparedStatement preparedStatement;

      String deleteById = "DELETE FROM app_user \n" +
              "WHERE user_id = ?; ";

      try {
          preparedStatement = connection.prepareStatement(deleteById);
          preparedStatement.setInt(1,id);

           preparedStatement.executeUpdate();
      }catch (SQLException e){
          System.out.println(e);
      }

    }


    public void addUser(Connection connection,
                        String login, String password,
                        String description) throws SQLException {

        PreparedStatement preparedStatement;

        System.out.println(String.format("Add user : %s", login));

        String newUSer = "INSERT INTO app_user(login, password, description) VALUES (?,?,?)";

        try {

            preparedStatement = connection.prepareStatement(newUSer);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, description);

            // returns number of changed column
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void getUser(Connection connection) throws SQLException {
        System.out.println();
        System.out.println("-----Initial list of users-----");

        String getUserRecord = "SELECT user_id, login, description" +
                " From app_user ORDER BY user_id";

        try {

            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(getUserRecord);

            while (resultSet.next()) {
                System.out.println(String.format("User : %s, %s, %s,",
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void getAllUsers(Connection connection) throws SQLException {
        System.out.println();
        System.out.println("--------users remaining after delete --------");

            PreparedStatement preparedStatement;
        String allUsers = "SELECT login FROM app_user";

        try {
            preparedStatement = connection.prepareStatement(allUsers);
            ResultSet resultSet = preparedStatement.executeQuery();

            while ((resultSet.next())){
                String name  = resultSet.getString("login");

                System.out.println(name);
            }

        }catch (SQLException e){
            System.out.println(e);
        }
    }

}
