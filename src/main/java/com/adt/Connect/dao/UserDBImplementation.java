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

    private static UserDBImplementation instance;

    public static UserDBImplementation getInstance() {
        if (instance == null) {
            instance = new UserDBImplementation();
        }
        return instance;
    }

    private Connection conn;
    private PreparedStatement stmt;

    // SQL Statements
    final String SQLCHECKUSERMAIL = "SELECT * FROM user WHERE email = ?";
    final String SQLCHECKNAME = "SELECT * FROM user WHERE name = ?";
    final String SQLINSERT = "INSERT INTO user (name, email, password, phone_num) VALUES (?, ?, ?, ?)";

    public UserDBImplementation() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public boolean checkUserMail(String mail) {
        boolean exists = false;
        User user = null;
        try {
            stmt = conn.prepareStatement(SQLCHECKUSERMAIL);
            stmt.setString(1, mail);
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
    public User checkUserName(String username) {
        User user = null;
        try {
            stmt = conn.prepareStatement(SQLCHECKNAME);
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                user = new User();
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPhoneNumber(result.getString("phone_num"));
            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error al verificar credenciales: " + e.getMessage());
        }
        return user;
    }

    @Override
    public boolean createUser(User user) {
        boolean ok = false;
        if (checkUserName(user.getName()) == null && !checkUserMail(user.getEmail())) {
            try {
                stmt = conn.prepareStatement(SQLINSERT);
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
        }else{
            System.out.println("Email or username already used.");
        }
        return ok;
    }
}
