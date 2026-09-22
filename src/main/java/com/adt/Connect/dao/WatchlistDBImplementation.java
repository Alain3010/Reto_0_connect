/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Movie;
import com.adt.Connect.model.User;
import com.adt.Connect.model.Watchlist;

/**
 *
 * @author Jaime.Diaz
 */
public class WatchlistDBImplementation implements WatchlistDAO {
    @Override
    public boolean createWatchlist(Watchlist watchlist) {
        return true;
    }

    @Override
    public boolean addMovieToWatchList(Movie movie) {
         return true;
    }

    @Override
    public boolean viewUserWatchList(User user) {
         return true;
    }
}
