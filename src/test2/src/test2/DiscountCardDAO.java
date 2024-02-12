package test2.src.test2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountCardDAO {
    private Connection connection;

    public DiscountCardDAO(Connection connection) {
        this.connection = connection;
    }

    public void addDiscountCard(DiscountCard discountCard) throws SQLException {
        String sql = "INSERT INTO DiscountCards (cardType, discountPercentage, validityPeriod) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, discountCard.getCardType());
            statement.setDouble(2, discountCard.getDiscountPercentage());
            statement.setString(3, discountCard.getValidityPeriod());
            statement.executeUpdate();
        }
    }

    public List<DiscountCard> getAllDiscountCards() throws SQLException {
        List<DiscountCard> discountCards = new ArrayList<>();
        String sql = "SELECT * FROM DiscountCards";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DiscountCard discountCard = new DiscountCard();
                discountCard.setId(resultSet.getInt("id"));
                discountCard.setCardType(resultSet.getString("cardType"));
                discountCard.setDiscountPercentage(resultSet.getDouble("discountPercentage"));
                discountCard.setValidityPeriod(resultSet.getString("validityPeriod"));
                discountCards.add(discountCard);
            }
        }
        return discountCards;
    }

    // Ajoutez des méthodes pour la mise à jour et la suppression des cartes de réduction si nécessaire
}