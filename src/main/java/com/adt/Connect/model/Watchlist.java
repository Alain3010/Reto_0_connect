/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Watchlist implements Serializable {
    
    private Integer id;
    private String name;
    private LocalDate creationDate;
    private Integer mov_count;
    private User user;
    private List<Movie> movies;

    public Watchlist() {
        this.movies = new ArrayList<>();
    }

    public Watchlist(Integer id, String name, LocalDate creationDate, Integer mov_count, User user) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.mov_count = mov_count;
        this.user = user;
        this.movies = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getMovieCount() {
        return mov_count;
    }

    public void setMovieCount(Integer mov_count) {
        this.mov_count = mov_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
