/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.User;
import com.adt.Connect.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jaime.Diaz
 */
public class UserDBImplementation implements UserDAO {

    private Connection conn;
    private PreparedStatement stmt;

    // SQL Statements
    final String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
    final String sql1 = "SELECT * FROM user WHERE email = ?";
    final String sqlInsert = "INSERT INTO user (name, email, password, phone_num) VALUES (?, ?, ?, ?)";

    public UserDBImplementation() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public boolean checkUser(User user) {
        boolean exists = false;

        try {
            stmt = conn.prepareStatement(sql1);
            stmt.setString(1, user.getEmail());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                exists = true;
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return exists;
    }

    @Override
    public boolean createUser(User user) {
        boolean ok = false;

        if (!checkUser(user)) {

            try {
                stmt = conn.prepareStatement(sqlInsert);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setString(4, user.getPhoneNumber());
                if (stmt.executeUpdate() > 0) {
                    ok = true;
                }
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error while verifying credentials: " + e.getMessage());
            }
        }
        return ok;
    }
}
