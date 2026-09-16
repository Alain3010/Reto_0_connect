/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.model;

public class User {
    
    private Integer id;
    private String name;
    private String email;
    private String phone_num;

    public User() {
    }

    public User(Integer id, String name, String email, String phone_num) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_num;
    }

    public void setPhoneNumber(String phone_num) {
        this.phone_num = phone_num;
    }
}