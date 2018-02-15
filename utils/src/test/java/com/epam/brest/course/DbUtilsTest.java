package com.epam.brest.course;

import org.junit.Assert;
import org.junit.Test;
import java.sql.SQLException;


public class DbUtilsTest {

    @Test
    public void getConnection() throws SQLException, ClassNotFoundException {

        DbUtils dbUtils = new DbUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }

}
