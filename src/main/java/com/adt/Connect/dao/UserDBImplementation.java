/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author Jaime.Diaz
 */
public class UserDBImplementation implements UserDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;

    // SQL Statements
    final String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
    final String sql1 = "SELECT * FROM user WHERE email = ?";
    final String sqlInsert = "INSERT INTO user (name, email, password, phone_num) VALUES (?, ?, ?, ?)";

    public UserDBImplementation() {
        this.configFile = ResourceBundle.getBundle("ClassProperties");
        this.driverDB = this.configFile.getString("db.driver");
        this.urlDB = this.configFile.getString("db.url");
        this.userDB = this.configFile.getString("db.user");
        this.passwordDB = this.configFile.getString("password");
    }

    private void openConnection() {
        try {
            conn = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
        } catch (SQLException e) {
            System.out.println("Error while trying to connect to Database");
        } catch (Exception e) {
        }
    }

    public boolean checkUser(User user) {
        boolean exists = false;
        this.openConnection();

        try {
            stmt = conn.prepareStatement(sql1);
            stmt.setString(1, user.getEmail());
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                exists = true;
            }
            result.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }

        return exists;
    }

    @Override
    public boolean createUser(User user) {
        boolean ok = false;

        if (!checkUser(user)) {
            this.openConnection();

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
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error while verifying credentials: " + e.getMessage());
            }
        }
        return ok;
    }
}
