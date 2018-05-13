package com.acme.mario_ej2_recetario_bd.modelo;

/**
 * Created by Mario on 27/01/2018.
 */

public class Ingredientes {

    int id;
    String nombre;

    public Ingredientes(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Ingredientes(String nombre) {
        this.nombre = nombre;
    }

    public Ingredientes(int id) {
        this.id = id;
    }

    public Ingredientes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
