/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Watchlist;
import com.adt.Connect.model.Movie;
import com.adt.Connect.model.User;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author AdamFixed
 */
public interface WatchlistDAO {
    public boolean createWatchlist(Watchlist watchlist);
    public boolean addMovieToWatchList(Movie movie);
    public boolean viewUserWatchList(User user);
    public ArrayList<Movie> viewWatchlistMovies(File fich, Watchlist watchlist);
}
