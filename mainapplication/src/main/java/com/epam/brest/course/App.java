package com.epam.brest.course;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * this is the main class of our app.
 */
public final class App {

    /**
     *
     */
    private App() { }

    /**
     *
     * @param args main
     * @throws SQLException when we have sql mistake
     * @throws ClassNotFoundException when class not detected
     */
    public static void main(final String[]  args)
            throws SQLException,
                    ClassNotFoundException {

        System.out.println("Hello World!");
        DbUtils dbUtils = new DbUtils();

        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        dbUtils.addUser(connection, "admin", "admin", "admin User");
        dbUtils.addUser(connection, "admin1", "admin1", "admin User");
        dbUtils.addUser(connection, "admin2", "admin2", "admin User");
        dbUtils.getUser(connection);
        dbUtils.deleteUser(connection, 1);
        dbUtils.getAllUsers(connection);
        dbUtils.updateUser(connection, 2, "valentine", "java-man",
                                                     "java is good");
    }
}
