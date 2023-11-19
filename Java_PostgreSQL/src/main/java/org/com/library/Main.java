package org.com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/library_management",
                "prog_admin",
                "123456")) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}