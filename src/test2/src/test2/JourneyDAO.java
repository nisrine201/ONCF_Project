package test2.src.test2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JourneyDAO {
    private Connection connection;

    public JourneyDAO(Connection connection) {
        this.connection = connection;
    }

    public void addJourney(Journey journey) throws SQLException {
        String sql = "INSERT INTO Journeys (origin, destination, departureTime, arrivalTime, fare) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, journey.getOrigin());
            statement.setString(2, journey.getDestination());
            statement.setString(3, journey.getDepartureTime());
            statement.setString(4, journey.getArrivalTime());
            statement.setDouble(5, journey.getFare());
            statement.executeUpdate();
        }
    }

    public List<Journey> getAllJourneys() throws SQLException {
        List<Journey> journeys = new ArrayList<>();
        String sql = "SELECT * FROM Journeys";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Journey journey = new Journey();
                journey.setId(resultSet.getInt("id"));
                journey.setOrigin(resultSet.getString("origin"));
                journey.setDestination(resultSet.getString("destination"));
                journey.setDepartureTime(resultSet.getString("departureTime"));
                journey.setArrivalTime(resultSet.getString("arrivalTime"));
                journey.setFare(resultSet.getDouble("fare"));
                journeys.add(journey);
            }
        }
        return journeys;
    }

    // Ajoutez des méthodes pour la mise à jour et la suppression des trajets si nécessaire
}
