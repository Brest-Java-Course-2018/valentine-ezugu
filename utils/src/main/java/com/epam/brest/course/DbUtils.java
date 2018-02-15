package com.epam.brest.course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {



    public Connection getConnection() throws ClassNotFoundException, SQLException {
       Connection connection;

        String databaseUrl = "Jdbc:h2mem:test_db;MODE=MYSQL;DB_CLOSE_DELAY=-10";

        Class.forName("org.h2.Driver");

        connection = DriverManager.getConnection(databaseUrl,"sa","");

        return connection;
    }

}
