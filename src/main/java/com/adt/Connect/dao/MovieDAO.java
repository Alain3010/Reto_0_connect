/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

/**
 *
 * @author 20ala
 */
import com.adt.Connect.model.Movie;
import java.util.ArrayList;


public interface MovieDAO {

    public boolean checkMovie(Movie movie);

    public boolean registerMovie(Movie movie);

    public ArrayList<Movie> viewAdultMovies();
}

