/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Genre;
import com.adt.Connect.model.Movie;
import com.adt.Connect.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author asola
 */
public class MovieBDImplementation implements MovieDAO {

    private static MovieBDImplementation instance;
    
    public static MovieBDImplementation getInstance() {
        if (instance == null) {
            instance = new MovieBDImplementation();
        }
        return instance;
    }

    private Connection conn;
    private PreparedStatement stmt;

    final String SQLREGISTERMOVIE = "INSERT INTO movie (title, director, genre, adults, route) VALUES(?, ?, ?, ?, ?)";
    final String SQLCHECKMOVIE = "SELECT * FROM movie WHERE title = ?";
    final String SQLVIEWADULTMOVIES = "SELECT * FROM movie WHERE adults = true";

    public MovieBDImplementation() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean registerMovie(Movie movie) {
        boolean added = false;
        if (!checkMovie(movie)) {
            try {
                stmt = conn.prepareStatement(SQLREGISTERMOVIE);
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getDirector());
                stmt.setString(3, movie.getGenre().toString());
                stmt.setBoolean(4, movie.isAdult());
                stmt.setString(5, movie.getRoute());

                int resultado = stmt.executeUpdate();
                if (resultado > 0) {
                    added = true;
                }
                stmt.close();
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
        } catch (SQLException e) {

        }
        return check;
    }

    @Override
    public ArrayList<Movie> viewAdultMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
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
        } catch (SQLException e) {
            System.out.println("Error retrieving adult movies: " + e.getMessage());
        }
        return movies;
    }

}
