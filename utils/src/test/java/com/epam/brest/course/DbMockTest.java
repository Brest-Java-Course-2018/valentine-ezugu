package com.epam.brest.course;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbMockTest {

    @Mock
    private DataSource ds;

    @Mock
    private Connection conn;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @Before
    public void setUp() throws Exception {

        assertNotNull(ds);
        when(ds.getConnection()).thenReturn(conn);
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);

        String createTable = "CREATE TABLE app_user(" +
                "user_id INT NOT NULL AUTO_INCREMENT," +
                "login VARCHAR (225) NOT NULL," +
                "password VARCHAR(225) NOT NULL," +
                "description VARCHAR (225) NULL," +
                "PRIMARY KEY (user_id))";

        stmt.executeUpdate(createTable);
    }


    @Test
    public void addUser( ) throws Exception {
       DbUtils dbUtils = new DbUtils();
          assertNotNull(dbUtils);
       // dbUtils.addUser(conn, "login","password", "my info");
        //dbUtils.getAllUsers(conn);
    }

}
