package test2.src.test2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public void addTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO Transactions (customerId, journeyId, timestamp, amount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transaction.getCustomerId());
            statement.setInt(2, transaction.getJourneyId());
            statement.setString(3, transaction.getTimestamp());
            statement.setDouble(4, transaction.getAmount());
            statement.executeUpdate();
        }
    }

    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transactions";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(resultSet.getInt("id"));
                transaction.setCustomerId(resultSet.getInt("customerId"));
                transaction.setJourneyId(resultSet.getInt("journeyId"));
                transaction.setTimestamp(resultSet.getString("timestamp"));
                transaction.setAmount(resultSet.getDouble("amount"));
                transactions.add(transaction);
            }
        }
        return transactions;
    }

    // Ajoutez des méthodes pour la mise à jour et la suppression des transactions si nécessaire
}