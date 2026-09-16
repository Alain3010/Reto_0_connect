/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Movie;
import com.adt.Connect.model.User;

/**
 *
 * @author AdamFixed
 */
public interface WatchlistDAO {
    public boolean createWatchList();
    
    public boolean addMovieToWatchList(Movie movie);
    
    public boolean viewUserWatchList(User user);
}
