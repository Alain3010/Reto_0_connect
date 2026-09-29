/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    // Instancia estática para el patrón Singleton
    private static DatabaseConnection instance;
    private Connection connection;

    // Constructor privado
    private DatabaseConnection() {
        // 1. Declaración de variables al principio
        Properties props;
        InputStream is;
        String url;
        String user;
        String password;
        boolean propLoaded;

        // 2. Inicialización
        props = new Properties();
        is = null;
        propLoaded = false;

        try {
            is = getClass().getClassLoader().getResourceAsStream("db.properties");

            // Validación usando booleanos
            if (is != null) {
                props.load(is);
                propLoaded = true;
            } else {
                System.err.println("File db.properties not found in classpath.");
            }

            if (propLoaded) {
                url = props.getProperty("db.url");
                user = props.getProperty("db.user");
                password = props.getProperty("db.password");

                this.connection = DriverManager.getConnection(url, user, password);
            }

        } catch (SQLException e) {
            System.err.println("Error while connecting to database: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    System.err.println("Error while closing InputStream: " + ex.getMessage());
                }
            }
        }
    }

    // Método de acceso global Singleton (SIN returns intermedios sucios)
    public static DatabaseConnection getInstance() {
        // Declaración al inicio
        boolean instanceIsNull;

        // Asignación
        instanceIsNull = (instance == null);

        if (instanceIsNull) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
