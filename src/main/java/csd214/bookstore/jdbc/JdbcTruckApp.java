package csd214.bookstore.jdbc;

import csd214.bookstore.pojos.Truck;

import java.sql.*;
import java.util.UUID;

public class JdbcTruckApp {
    // Connection details match docker-compose.yaml
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to Database.");

            // 1. Create Table
            createTable(conn);

            // 2. Insert (Create)
            System.out.println("\n--- INSERTING ---");
            // Constructor: id, name, price, make, model, year, mileage, towingCapacity
            Truck truck1 = new Truck(
                    UUID.randomUUID().toString(),
                    "Workhorse 3000",
                    45000.00,
                    "Ford",
                    "F-150",
                    2024,
                    150,
                    13000.0
            );
            insertTruck(conn, truck1);

            Truck truck2 = new Truck(
                    UUID.randomUUID().toString(),
                    "Hauler Max",
                    55000.00,
                    "Chevy",
                    "Silverado",
                    2023,
                    12000,
                    14500.0
            );
            insertTruck(conn, truck2);

            // 3. Read
            System.out.println("\n--- READING ---");
            listTrucks(conn);

            // 4. Update
            System.out.println("\n--- UPDATING ---");
            // Update the towing capacity of the "Workhorse 3000"
            updateTruckTowingCapacity(conn, "Workhorse 3000", 15000.0);

            // 5. Delete
            System.out.println("\n--- DELETING ---");
            deleteTruck(conn, "Hauler Max");

            // Final List
            System.out.println("\n--- FINAL LIST ---");
            listTrucks(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        // Flattening the inheritance hierarchy (Product -> Vehicle -> Truck) into one table
        String sql = "CREATE TABLE IF NOT EXISTS trucks (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "name VARCHAR(255), " +
                "price DOUBLE, " +
                "make VARCHAR(255), " +
                "model VARCHAR(255), " +
                "year INT, " +
                "mileage INT, " +
                "towing_capacity DOUBLE)";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'trucks' is ready.");
        }
    }

    private static void insertTruck(Connection conn, Truck t) throws SQLException {
        String sql = "INSERT INTO trucks (product_id, name, price, make, model, year, mileage, towing_capacity) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Product fields
            ps.setString(1, t.getProductId());
            ps.setString(2, t.getName());
            ps.setDouble(3, t.getPrice());

            // Vehicle fields
            ps.setString(4, t.getMake());
            ps.setString(5, t.getModel());
            ps.setInt(6, t.getYear());
            ps.setInt(7, t.getMileage());

            // Truck fields
            ps.setDouble(8, t.getTowingCapacity());

            ps.executeUpdate();
            System.out.println("Saved Truck: " + t.getName() + " (" + t.getMake() + " " + t.getModel() + ")");
        }
    }

    private static void listTrucks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM trucks";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-5s %-20s %-15s %-15s %-10s %-10s %-10s %-10s%n",
                    "ID", "Name", "Make", "Model", "Year", "Price", "Miles", "Towing");
            System.out.println("----------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s %-15s %-10d $%-9.2f %-10d %-10.1f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getInt("mileage"),
                        rs.getDouble("towing_capacity"));
            }
        }
    }

    private static void updateTruckTowingCapacity(Connection conn, String truckName, double newCapacity) throws SQLException {
        String sql = "UPDATE trucks SET towing_capacity = ? WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newCapacity);
            ps.setString(2, truckName);
            int rows = ps.executeUpdate();
            System.out.println("Updated towing capacity for " + rows + " truck(s) named '" + truckName + "'.");
        }
    }

    private static void deleteTruck(Connection conn, String truckName) throws SQLException {
        String sql = "DELETE FROM trucks WHERE name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, truckName);
            int rows = ps.executeUpdate();
            System.out.println("Deleted " + rows + " truck(s) named '" + truckName + "'.");
        }
    }
}