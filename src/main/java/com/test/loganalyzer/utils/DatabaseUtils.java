package com.test.loganalyzer.utils;

import com.test.loganalyzer.model.LogEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Map;

public class DatabaseUtils {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtils.class);

    private static final String JDBC_URL = "jdbc:hsqldb:file:database/testDB";
    private static final String JDBC_USERNAME = "SA";
    private static final String JDBC_PASSWORD = "";

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            printSQLException(e);
        }
        return connection;
    }

    public static void initDatabase() {
        try (Connection conn = getConnection()) {
            run(conn, "DROP TABLE LogEvent IF EXISTS");
            run(conn, "CREATE TABLE LogEvent ("
                    + "eventId VARCHAR(255) PRIMARY KEY, "
                    + "eventDuration INTEGER, "
                    + "type VARCHAR(255), "
                    + "host VARCHAR(255), "
                    + "alert BOOLEAN DEFAULT FALSE)");
            logger.debug("Database is initialized, LogEvent is created.");

        } catch (SQLException exception) {
            printSQLException(exception);
        }

    }

    public static void populateDatabase(Map<String, LogEvent> insertRecords) {
        try (Connection conn = getConnection()) {
            insertRecords.forEach((k, v) -> run(conn, printLogInsertQuery(v)));
        } catch (SQLException exception) {
            printSQLException(exception);
        }
    }

    public static void printLogEventSummary() {
        logger.info("LogEvent has: ");
        try (Connection conn = getConnection()) {
            int totalRows = getCount(conn, "SELECT count(*) FROM LogEvent");
            int totalAlertRows = getCount(conn, "SELECT count(*) FROM LogEvent where alert ='true'");
            logger.info("- total number of records: " + totalRows);
            logger.info("- total number of alert records: " + totalAlertRows);
        } catch (SQLException exception) {
            printSQLException(exception);
        }
    }

    public static Integer getCount(Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }
    }

    private static String printLogInsertQuery(LogEvent event) {
        String host = event.getHost() == null ? null : "'" + event.getHost() + "'";
        String type = event.getType() == null ? null : "'" + event.getType() + "'";

        return "INSERT INTO LogEvent VALUES ('" +
                event.getEventId() + "'," +
                event.getEventDuration() + "," +
                type + "," +
                host + "," +
                event.getAlertFlag() + ")";
    }

    private static void run(Connection conn, String sql) {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException exception) {
            printSQLException(exception);
        }
    }

    private static void printSQLException(SQLException exception) {
        logger.error("SQLState: " + exception.getSQLState());
        logger.error("Error Code: " + exception.getErrorCode());
        logger.error("Message: " + exception.getMessage());
        logger.error("Cause: " + exception.getCause());
        exception.printStackTrace();
    }

}