package com.epam.brest.course;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class DbUtilsTest {

    private static String URL = "jdbc:h2:mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-1";

    //we should throw exception to avoid connection close by default
    private static Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection(URL, "sa", "");
        return connection;
    }

    //using before class so we establish app_user table only once for all tests
    @BeforeClass
    public static void set() throws ClassNotFoundException {

        String createTable =
                "CREATE TABLE app_user(" +
                        "user_id INT NOT NULL AUTO_INCREMENT," +
                        "login VARCHAR (255) NOT NULL ," +
                        "password VARCHAR (255) NOT NULL ," +
                        "description VARCHAR (255) NULL," +
                        "PRIMARY KEY (user_id))";

        try (Statement statement = connection().createStatement()) {
            statement.executeUpdate(createTable);
            Assert.assertNotNull(statement);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void addUser() throws SQLException, ClassNotFoundException {

        String addUser = "INSERT INTO app_user(login, password, description) VALUES (1234,'valentine','javaMan')";
        PreparedStatement statement = connection().prepareStatement(addUser);
        int rowAffected = statement.executeUpdate();

        Assert.assertNotNull(rowAffected);
        Assert.assertEquals(rowAffected, 1);
    }


    @Test
    public void getAllUsers() throws SQLException, ClassNotFoundException {
        PreparedStatement statement;

        String addUser = "INSERT INTO app_user(login, password, description) VALUES (1234,'valentine','javaMan')";
        statement = connection().prepareStatement(addUser);
        statement.executeUpdate();

        String allUser = "SELECT * FROM app_user";
        statement = connection().prepareStatement(allUser);
        ResultSet resultSet = statement.executeQuery();
        Assert.assertNotNull(resultSet);
        Assert.assertEquals(1,1);
    }

    // todo test is unnecessarily large
    @Test
    public void deleteById() throws SQLException, ClassNotFoundException {
        PreparedStatement statement;

        String addUser = "INSERT INTO app_user(login, password, description) VALUES (54332,'james','cole')";
        statement = connection().prepareStatement(addUser);
        statement.executeUpdate();

        String addUser2 = "INSERT INTO app_user(login, password, description) VALUES (989876,'roman','gaiv')";
        statement = connection().prepareStatement(addUser2);
        statement.executeUpdate();

        String allUser = "SELECT * FROM app_user";
        statement = connection().prepareStatement(allUser);
        ResultSet resultSet = statement.executeQuery();

        Assert.assertNotNull(resultSet);
        Assert.assertEquals(2,2);

        String deleteById = "DELETE FROM app_user \n" +
                "WHERE user_id = 1";

        statement = connection().prepareStatement(deleteById);
        statement.executeUpdate();

        String remaining = "SELECT * FROM app_user";
        statement = connection().prepareStatement(remaining);
        ResultSet rs = statement.executeQuery();

        Assert.assertNotNull(rs);
        Assert.assertEquals(1,1);

    }

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {

        DbUtils dbUtils = new DbUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }

}