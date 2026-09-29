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
        Properties props;
        InputStream is;
        String url;
        String user;
        String password;
        String driver;
        boolean propLoaded;

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
                driver = props.getProperty("db.driver");

                // Forzamos la carga del driver que hayas puesto en el properties
                if (driver != null && !driver.isEmpty()) {
                    Class.forName(driver);
                }

                url = props.getProperty("db.url");
                user = props.getProperty("db.user");
                password = props.getProperty("db.password");

                this.connection = DriverManager.getConnection(url, user, password);
            }

        } catch (SQLException e) {
            // Error si MySQL está apagado, la BBDD no existe o hay mal usuario/contraseña
            throw new RuntimeException("MySQL connection error. Check your credentials or if XAMPP/MySQL is on. Detail: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            // Error si Maven no ha descargado correctamente la dependencia de MySQL
            throw new RuntimeException("Error: MySQL driver not found in project. Check pom.xml. Detail: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading db.properties: " + e.getMessage(), e);
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

    public static DatabaseConnection getInstance() {
        boolean instanceIsNull;
        instanceIsNull = (instance == null);

        if (instanceIsNull) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        try {
            // Validación por si la conexión se ha caído/cerrado posteriormente
            if (this.connection == null || this.connection.isClosed()) {
                System.err.println("Critical warning: Database connection is null or has been closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection state: " + e.getMessage());
        }
        return connection;
    }
}
