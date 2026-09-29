/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.controller;

/**
 *
 * @author asola
 */
import com.adt.Connect.dao.*;
import com.adt.Connect.model.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WatchlistController {

    WatchlistDAO dao = WatchlistFileImplementation.getInstance();

    public ArrayList<Movie> viewWatchlistMovies(File file, String name) {
        return dao.viewWatchlistMovies(file, name);
    }
    
    public Watchlist selectWatchlist(File file, String name){
        return dao.selectWatchlist(file, name);
    }
    
    public boolean createWatchlist(Watchlist watchlist){
        return dao.createWatchlist(watchlist);
    }
   
    public boolean addMovieToWatchList(File fich, Movie movie, Integer id) {
        return dao.addMovieToWatchList(fich, movie, id);
    }
    
    public boolean viewUserWatchList(File fich, User user) {
        return dao.viewUserWatchList(fich, user);
    }
}
