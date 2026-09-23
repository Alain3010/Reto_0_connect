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

public class WatchlistController {

    WatchlistDAO dao = new WatchlistFileImplementation();

    public ArrayList<Movie> viewWatchlistMovies(File file, Watchlist watchlist) {
        return dao.viewWatchlistMovies(file, watchlist);
    }
}
