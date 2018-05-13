package com.example.mario_portatil.barbero_mario_2ev.modelo;

/**
 * Created by Mario-Portatil on 02/02/2018.
 */

public class Alumno {

    int id;
    String nombre;

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Alumno( String nombre) {
        this.nombre = nombre;
    }

    public Alumno(int id) {
        this.id = id;
    }

    public Alumno() {
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

    @Override
    public String toString(){
        return nombre;
    }
}
