/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.model;

import com.adt.Connect.dao.WatchlistDAO;

/**
 *
 * @author AdamFixed
 */
public class ImplWatchListFile implements WatchlistDAO {

    @Override
    public boolean createWatchList() {
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
