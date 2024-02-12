package test2.src.test2;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AvailableTripsFrame extends JFrame {
    private JourneyDAO journeyDAO;
    private JTable table;

    public AvailableTripsFrame() {
        setTitle("Available Trips");
        setSize(600, 400);
        setLocationRelativeTo(null);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB", "root", "")) {
            journeyDAO = new JourneyDAO(connection);

            List<Journey> availableJourneys = journeyDAO.getAllJourneys();

            Object[][] data = new Object[availableJourneys.size()][5];
            for (int i = 0; i < availableJourneys.size(); i++) {
                Journey journey = availableJourneys.get(i);
                data[i] = new Object[]{journey.getId(), journey.getOrigin(), journey.getDestination(), journey.getDepartureTime(), journey.getArrivalTime()};
            }

            String[] columnNames = {"ID", "Origin", "Destination", "Departure Time", "Arrival Time"};

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane, BorderLayout.CENTER);

            // Ajoute un écouteur d'événements pour détecter les clics sur les trajets
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            Journey selectedJourney = availableJourneys.get(selectedRow);
                            PaymentOptionsFrame paymentOptionsFrame = new PaymentOptionsFrame(selectedJourney, connection); // Pass the connection to PaymentOptionsFrame
                            paymentOptionsFrame.setVisible(true);
                        }
                    }
                }
            });

        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}