/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.model;

public class Movie {
    
    private Integer id;
    private String title;
    private String director;
    private Genre genre;
    private boolean adults; 
    private String route;

    public Movie() {
    }

    public Movie(Integer id, String title, String director, Genre genre, boolean adults, String route) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.adults = adults;
        this.route = route;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isAdult() {
        return adults;
    }

    public void setAdult(boolean adults) {
        this.adults = adults;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
