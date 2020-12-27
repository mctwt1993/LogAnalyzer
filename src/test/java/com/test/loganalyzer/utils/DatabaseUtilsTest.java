package com.test.loganalyzer.utils;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class DatabaseUtilsTest {

    @Test
    public void getConnection() {
        Connection con = DatabaseUtils.getConnection();
        Assert.assertNotNull(con);
    }

}