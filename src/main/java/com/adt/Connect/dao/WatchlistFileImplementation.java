/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Movie;
import com.adt.Connect.model.User;
import com.adt.Connect.model.Watchlist;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Jaime.Diaz
 */
public class WatchlistFileImplementation implements WatchlistDAO {

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

    @Override
    public ArrayList<Movie> viewWatchlistMovies(File fich, Watchlist watchlist) {
        ArrayList<Movie> movies = null;
        boolean fileEnd = false, found=false;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich));
            while(!fileEnd || !found){
                try {
                    Watchlist w = (Watchlist) ois.readObject();
                    if(watchlist.getName().equalsIgnoreCase(w.getName())){
                        movies = (ArrayList<Movie>) w.getMovies();
                        found = true;
                    }
                } catch (EOFException e) {
                    fileEnd = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
       
        }
        return movies;
    }

}
