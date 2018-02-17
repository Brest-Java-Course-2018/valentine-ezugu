package com.epam.brest.course;

import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        System.out.println( "Hello World!");
        DbUtils dbUtils = new DbUtils();

        Connection connection = dbUtils.getConnection();
        dbUtils.createUserTable(connection);
        dbUtils.addUser(connection,"admin","admin","admin User");
        dbUtils.addUser(connection,"admin1","admin1","admin User");

        dbUtils.addUser(connection,"admin2","admin2","admin User");

        dbUtils.getUser(connection);

        dbUtils.deleteUser(connection,1);
        dbUtils.getAllUsers(connection);

    }
}
