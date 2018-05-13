package com.acme.mario_ej2_recetario_bd.modelo;

/**
 * Created by Mario on 27/01/2018.
 */

public class Categoria {

    int id;
    String denominacion;
    int imagen;

    public Categoria(int id,String denominacion, int imagen) {
        this.id=id;
        this.denominacion = denominacion;
        this.imagen = imagen;
    }

    public Categoria(String denominacion, int imagen) {
        this.denominacion = denominacion;
        this.imagen = imagen;
    }

    public Categoria(int id) {
        this.id = id;
    }

    public Categoria() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
