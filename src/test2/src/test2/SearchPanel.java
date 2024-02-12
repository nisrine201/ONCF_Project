package test2.src.test2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchPanel extends JPanel {
    private JComboBox<String> departComboBox;
    private JComboBox<String> arriveeComboBox; // Corrected variable name
    private JTextField dateField;
    private JComboBox<String> classComboBox;
    private JComboBox<Integer> passengersComboBox;
    private JButton searchButton;

    public SearchPanel() {
        setLayout(new GridLayout(0, 2, 10, 10)); // Grid layout with 2 columns and gaps

        // Departure field
        add(new JLabel("Ma gare de départ :"));
        String[] depart = {"Marrakech","Rabat"};
        departComboBox = new JComboBox<>(depart);
        add(departComboBox);
        
        // Arrival field
        add(new JLabel("Ma gare d'arrivée :"));
        String[] arrivee = {"Marrakech","Rabat"};
        arriveeComboBox = new JComboBox<>(arrivee);
        add(arriveeComboBox);
        
        // Date field
        add(new JLabel("Date de départ :"));
        dateField = new JTextField("JJ/MM/AAAA");
        add(dateField);

        // Class selection
        add(new JLabel("Classe :"));
        String[] classes = {"1ère classe", "2ème classe"};
        classComboBox = new JComboBox<>(classes);
        add(classComboBox);

        // Passengers selection
        add(new JLabel("Nombre de passagers :"));
        Integer[] passengers = {1, 2, 3, 4, 5};
        passengersComboBox = new JComboBox<>(passengers);
        add(passengersComboBox);

        // Search button
        searchButton = new JButton("Rechercher");
        add(searchButton);
        searchButton.addActionListener(e -> {
            AvailableTripsFrame availableTripsFrame = new AvailableTripsFrame();
            availableTripsFrame.setVisible(true);
        });
    }

    // Method to get the departure station
    public String getDeparture() {
        return (String) departComboBox.getSelectedItem();
    }

    // Method to get the arrival station
    public String getArrival() {
        return (String) arriveeComboBox.getSelectedItem();
    }

    // Method to get the date
    public String getDate() {
        return dateField.getText();
    }

    // Method to get the selected class
    public String getClassSelection() {
        return (String) classComboBox.getSelectedItem();
    }

    // Method to get the selected number of passengers
    public int getPassengerCount() {
        return (int) passengersComboBox.getSelectedItem();
    }

    // Method to add action listener to the search button
    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
}
