package com.epam.brest.course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;

/**
 * DbUtils with is a class that handles db interactions.
 */
public class DbUtils {
    /**
     * variables to be used for statements.
     */
    private static final int ONE = 1;
    /**
     * two.
     */
    private static final int TWO = 2;
    /**
     * three.
     */
    private static final int THREE = 3;
    /**
     * four.
     */
    private static final int FOUR = 4;

    /**
     * method for getting connection.
     *
     * @return connection.
     * @throws ClassNotFoundException is thrown when driver not found.
     * @throws SQLException           can be thrown for execution errors.
     */
    public final Connection getConnection() throws ClassNotFoundException,
            SQLException {

        String databaseUrl = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        Class.forName("org.h2.Driver");

        return DriverManager.getConnection(databaseUrl, "sa", "");
    }

    /**
     * @param connection is required to do sql manipulations.
     * @throws SQLException when data is incorrect.
     */
    public final void createUserTable(final Connection connection) throws
            SQLException {
        System.out.println("app user table created ");
        System.out.println();

        String createTable = "CREATE TABLE app_user("
                + "user_id INT NOT NULL AUTO_INCREMENT,"
                + "login VARCHAR (225) NOT NULL,"
                + "password VARCHAR(225) NOT NULL,"
                + "description VARCHAR (225) NULL,"
                + "PRIMARY KEY (user_id))";

        try (Statement statement = connection.createStatement()) {
            //create a sql table.
            statement.executeUpdate(createTable);
        }
    }

    /**
     * @param connection is required to do sql manipulations.
     * @param id         we delete by id.
     * @throws SQLException is thrown when we have query mistake.
     *                      connection we get after we can make query delete.
     */
    public final void deleteUser(final Connection connection, final int id)
            throws SQLException {

        System.out.println();
        System.out.println("delete user with id -> " + id);

        String deleteById = "DELETE FROM app_user \n"
                + "WHERE user_id = ?; ";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(deleteById)) {

            preparedStatement.setInt(ONE, id);

            preparedStatement.executeUpdate();

        }

    }

    /**
     * @param connection  is required to do sql manipulations.
     * @param login       required param for login.
     * @param password    required for add user password.
     * @param description param to describe user.
     * @throws SQLException thrown when wrong data is entered.
     * @return statement execution
     */
    public final int addUser(final Connection connection, final String login,
                              final String password, final String description)
            throws SQLException {

        System.out.println(String.format("Add user : %s",
                                                          login));
        if (connection == null
                || login == null || login.length() == 0
                    || password == null || password.length() == 0
                             || description == null) {
            return -1;
        }


        String newUSer = "INSERT INTO app_user(login," + "password,"
                                   + " description) " + "VALUES (?,?,?)";

        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(newUSer)) {

            preparedStatement.setString(ONE, login);
            preparedStatement.setString(TWO, password);
            preparedStatement.setString(THREE, description);
            // returns number of changed column.
           return preparedStatement.executeUpdate();
        }
    }

    /**
     * @param connection is required to do sql manipulations.
     * @throws SQLException thrown when sql mistake
     */
    public final void getUser(final Connection connection)
            throws SQLException {

        System.out.println();
        System.out.println("-----Initial list of users-----");

        String getUserRecord = "SELECT user_id, login, description"
                + " FROM app_user ORDER BY user_id";

        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(getUserRecord);

            while (resultSet.next()) {
                System.out.println(String.format("User: %s, %s, %s,",
                        resultSet.getInt("user_id"),
                        resultSet.getString("login"),
                        resultSet.getString("description")));
            }
        }

    }

    /**
     * @param connection required.
     * @throws SQLException sql query mistake.
     */
    public final void getAllUsers(final Connection connection)

            throws SQLException {
        System.out.println("--------users remaining after delete --------");
        System.out.println();

        String allUsers = "SELECT login, password FROM app_user";

        try (PreparedStatement preparedStatement1
                     = connection.prepareStatement(allUsers)) {
            ResultSet rs = preparedStatement1.executeQuery();
            while (rs.next()) {

                String name = rs.getString("login");
                String password2 = rs.getString("password");
                System.out.println(String.format("users : %s, %s, ",
                                                            name, password2));

            }
        }
    }

    /**
     * @param connection  is required.
     * @param id          param .
     * @param login       param to be updated.
     * @param password    password for user.
     * @param description new description.
     * @throws SQLException query mistake.
     */
    public final void updateUser(final Connection connection,
                                 final int id, final String login,
                                 final String password,
                                 final String description) throws SQLException {

        System.out.println("---------after update of table--------");
        System.out.println();

        String updateQuery = "UPDATE app_user SET password = ?,"
                + "login = ?, description = ?"
                + " WHERE user_id = ?";

        try (PreparedStatement preparedStatement
                     = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(ONE, login);
            preparedStatement.setString(TWO, password);
            preparedStatement.setString(THREE, description);
            preparedStatement.setInt(FOUR, id);

            preparedStatement.executeUpdate();

            String allUsers = "SELECT login,password FROM app_user";

            try (PreparedStatement preparedStatement1
                         = connection.prepareStatement(allUsers)) {
                ResultSet resultSet = preparedStatement1.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("login");
                    String password1 = resultSet.getString("password");

                    System.out.println(String.format("User: %s, %s,",
                                                           name, password1));

                }

            }
        }

    }
}
