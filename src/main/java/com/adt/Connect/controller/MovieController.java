/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.controller;

import com.adt.Connect.dao.*;
import com.adt.Connect.model.*;

/**
 *
 * @author asola
 */
public class MovieController {

    MovieDAO dao = new MovieBDImplementation();

    public boolean registerMovie(Movie movie) {
        boolean added = false;
        added = dao.registerMovie(movie);
        return added;
    }
}
