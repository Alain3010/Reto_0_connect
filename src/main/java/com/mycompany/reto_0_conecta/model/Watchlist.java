/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto_0_conecta.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Watchlist {
    
    private Integer id;
    private String nombre;
    private LocalDate fechaCreacion;
    private Integer num_pel;
    private Usuario usuario;
    private List<Pelicula> peliculas;

    public Watchlist() {
        this.peliculas = new ArrayList<>();
    }

    public Watchlist(Integer id, String nombre, LocalDate fechaCreacion, Integer num_pel, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.num_pel = num_pel;
        this.usuario = usuario;
        this.peliculas = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getNum_pel() {
        return num_pel;
    }

    public void setNum_pel(Integer num_pel) {
        this.num_pel = num_pel;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
