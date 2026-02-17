package csd214.bookstore.jdbc;

import csd214.bookstore.pojos.Pen;

import java.sql.*;

public class JdbcPenApp {
    private static final String URL = "jdbc:mysql://localhost:3333/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Create Table
            createTable(conn);
            // 2. Insert
            System.out.println("--- INSERTING ---");
            Pen pen=new Pen("BIC", 2.99, "RED");
            insertPen(conn, pen);
            // 3. Read
            System.out.println("--- READING ---");
            listWidgets(conn);
            // 4. Update
            System.out.println("--- UPDATING ---");
            updatePenPrice(conn, "BIC", 25.50);

            // 5. Delete
            System.out.println("--- DELETING ---");
            deletePen(conn, "BIC");
            listWidgets(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pen (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "brand VARCHAR(255), " +
                "color VARCHAR(255), " +
                "price DOUBLE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'pen' ready.");
        }
    }
    private static void insertPen(Connection conn, Pen p) throws SQLException {
        // SECURITY: Use ? to prevent SQL Injection
        String sql = "INSERT INTO pen (product_id, brand, color, price ) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getProductId()); // UUID
            ps.setString(2, p.getBrand());
            ps.setString(3, p.getColor());
            ps.setDouble(4, p.getPrice());
            ps.executeUpdate();
            System.out.println("Saved: " + p.toString());
        }
    }
    private static void listWidgets(Connection conn) throws SQLException {
        String sql = "SELECT * FROM pen";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | UUID: %s | Brand: %s | Color: %s | Price: $%.2f%n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("brand"),
                        rs.getString("color"),
                        rs.getDouble("price"));
            }
        }
    }
    private static void updatePenPrice(Connection conn, String brand, double newPrice) throws SQLException {
        String sql = "UPDATE pen SET price = ? WHERE brand = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, brand);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " pen(s).");
        }
    }

    private static void deletePen(Connection conn, String brand) throws SQLException {
        String sql = "DELETE FROM pen WHERE brand = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, brand);
            ps.executeUpdate();
            System.out.println("Deleted widget: " + brand);
        }
    }
}

