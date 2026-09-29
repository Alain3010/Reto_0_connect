/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.controller;

import com.adt.Connect.dao.UserDBImplementation;
import com.adt.Connect.model.User;

/**
 *
 * @author asola
 */
public class UserController {
    UserDBImplementation dao = UserDBImplementation.getInstance();
    
    public boolean createUser(User user) {
        return dao.createUser(user);
    }
    
   public User checkUserName(String username) {
        return dao.checkUserName(username);
    }
}
