package test2.src.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentOptionsFrame extends JFrame {
    private Connection connection;

    public PaymentOptionsFrame(Journey journey, Connection connection) {
        this.connection = connection;

        setTitle("Payment Options");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(new JLabel("Selected Journey: " + journey.getOrigin() + " to " + journey.getDestination() + " for " + journey.getFare() + " dh"));

        JButton discountPaymentButton = new JButton("Payment with discount card");
        discountPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptDiscountCardPayment(journey);
            }
        });
        panel.add(discountPaymentButton);

        JButton regularPaymentButton = new JButton("Payment without card");
        regularPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                promptRegularPayment(journey);
            }
        });
        panel.add(regularPaymentButton);

        add(panel);
    }

    private void promptDiscountCardPayment(Journey journey) {
        JPanel cardInfoPanel = new JPanel(new GridLayout(2, 2));
        JTextField cardIdField = new JTextField();
        JTextField cardTypeField = new JTextField();
        cardInfoPanel.add(new JLabel("Card ID:"));
        cardInfoPanel.add(cardIdField);
        cardInfoPanel.add(new JLabel("Card Type:"));
        cardInfoPanel.add(cardTypeField);

        int result = JOptionPane.showConfirmDialog(this, cardInfoPanel, "Enter Discount Card Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int cardId = Integer.parseInt(cardIdField.getText());
                String cardType = cardTypeField.getText();
                if (isDiscountCardValid(cardId, cardType)) {
                    double discountPercentage = getDiscountPercentage(cardId);
                    double discountedFare = journey.getFare() * (1 - discountPercentage / 100);
                    JOptionPane.showMessageDialog(this, "Discounted Fare: " + discountedFare + " dh", "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
                    promptOnlinePaymentInfo(journey);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid or expired discount card.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid card ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean isDiscountCardValid(int cardId, String cardType) {
        String query = "SELECT * FROM discountcards WHERE id = ? AND cardType = ? AND CURDATE() <= validityPeriod";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cardId);
            statement.setString(2, cardType);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error accessing database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private double getDiscountPercentage(int cardId) {
        String query = "SELECT discountPercentage FROM discountcards WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cardId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("discountPercentage");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving discount percentage: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    private void promptRegularPayment(Journey journey) {
        // Insert code to prompt for regular payment here
        JOptionPane.showMessageDialog(this, "Regular payment prompt placeholder.", "Regular Payment", JOptionPane.INFORMATION_MESSAGE);
    }

    private void promptOnlinePaymentInfo(Journey journey) {
        // Insert code to prompt for online payment information here
        JOptionPane.showMessageDialog(this, "Online payment information prompt placeholder.", "Online Payment", JOptionPane.INFORMATION_MESSAGE);
    }
}