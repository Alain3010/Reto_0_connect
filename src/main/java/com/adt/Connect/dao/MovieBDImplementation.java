/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Genre;
import com.adt.Connect.model.Movie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author asola
 */
public class MovieBDImplementation implements MovieDAO {

    private Connection conn;
    private PreparedStatement stmt;
    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;

    final String SQLREGISTERMOVIE = "INSERT INTO movie VALUES(?,?,?,?,?)";
    final String SQLCHECKMOVIE = "SELECT * FROM movie WHERE title = ?";
    final String SQLVIEWADULTMOVIES = "SELECT * FROM movie WHERE adults = true";
    
    public MovieBDImplementation() {
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

    @Override
    public boolean registerMovie(Movie movie) {
        boolean added = false;
        if (!checkMovie(movie)) {
            this.openConnection();
            try {
                stmt = conn.prepareStatement(SQLREGISTERMOVIE);
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getDirector());
                stmt.setString(3, movie.getGenre().toString());
                stmt.setBoolean(4, movie.isAdult());
                stmt.setString(5, movie.getRoute());
                ResultSet resultado = stmt.executeQuery();
                if (resultado.next()) {
                    added = true;
                }
                resultado.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error inserting the movie: " + e.getMessage());
            }
        } else {
            System.out.println("The movie you are trying to add already exists.");
        }
        return added;
    }

    @Override
    public boolean checkMovie(Movie movie) {
        boolean check = false;
        this.openConnection();
        try {
            stmt = conn.prepareStatement(SQLCHECKMOVIE);
            stmt.setString(1, movie.getTitle());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                check = true;
            }
            System.out.println(resultado.getString(1));;
            resultado.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {

        }
        return check;
    }

    @Override
    public ArrayList<Movie> viewAdultMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        this.openConnection();
        try {
            stmt = conn.prepareStatement(SQLVIEWADULTMOVIES);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Movie movie = new Movie();
                movie.setId(resultado.getInt("id"));
                movie.setTitle(resultado.getString("title"));
                movie.setDirector(resultado.getString("director"));
                movie.setGenre(Genre.valueOf(resultado.getString("genre")));
                movie.setAdult(resultado.getBoolean("adults"));
                movie.setRoute(resultado.getString("route"));
                movies.add(movie);
            }
            resultado.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error retrieving adult movies: " + e.getMessage());
        }
        return movies;
    }

}
