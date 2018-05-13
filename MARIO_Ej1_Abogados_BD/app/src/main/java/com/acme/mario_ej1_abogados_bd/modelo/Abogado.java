package com.acme.mario_ej1_abogados_bd.modelo;


/**
 * Created by Mario on 29/01/2018.
 */

public class Abogado {

    int id;
    String numColegiado;
    String nombre;

    public Abogado(int id, String numColegiado, String nombre) {
        this.id = id;
        this.numColegiado = numColegiado;
        this.nombre = nombre;
    }

    public Abogado(String numColegiado, String nombre) {
        this.numColegiado = numColegiado;
        this.nombre = nombre;
    }

    public Abogado(int id) {
        this.id = id;
    }

    public Abogado(String numColegiado) {
        this.numColegiado = numColegiado;
    }

    public Abogado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
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
