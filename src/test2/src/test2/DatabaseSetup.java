package test2.src.test2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/MyDB";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create the Journeys table
            createJourneysTable(connection);

            // Create the DiscountCards table
            createDiscountCardsTable(connection);

            // Create the Transactions table
            createTransactionsTable(connection);

            System.out.println("Database setup completed successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database");
            e.printStackTrace();
        } finally {
            // Close the connection in the finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Error closing the connection");
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createJourneysTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Journeys (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "origin VARCHAR(255) NOT NULL," +
                "destination VARCHAR(255) NOT NULL," +
                "departureTime VARCHAR(255) NOT NULL," +
                "arrivalTime VARCHAR(255) NOT NULL," +
                "fare DOUBLE NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void createDiscountCardsTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS DiscountCards (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "cardType VARCHAR(255) NOT NULL," +
                "discountPercentage DOUBLE NOT NULL," +
                "validityPeriod VARCHAR(255) NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void createTransactionsTable(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Transactions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "customerId INT NOT NULL," +
                "journeyId INT NOT NULL," +
                "timestamp VARCHAR(255) NOT NULL," +
                "amount DOUBLE NOT NULL" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
