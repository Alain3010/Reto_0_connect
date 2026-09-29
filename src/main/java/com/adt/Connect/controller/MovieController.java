/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.controller;

import com.adt.Connect.dao.*;
import com.adt.Connect.model.*;
import java.util.ArrayList;

/**
 *
 * @author asola
 */
public class MovieController {

    MovieDAO dao = MovieBDImplementation.getInstance();

    public boolean registerMovie(Movie movie) {
        return dao.registerMovie(movie);
    }
    
    public ArrayList<Movie> viewAdultMovies() {
        return dao.viewAdultMovies();
    }
}
