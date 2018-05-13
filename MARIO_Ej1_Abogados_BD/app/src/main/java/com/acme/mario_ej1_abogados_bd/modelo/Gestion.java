package com.acme.mario_ej1_abogados_bd.modelo;

import java.util.Date;

/**
 * Created by Mario on 29/01/2018.
 */

public class Gestion {

    int id;
    Caso caso;
    String fecha;
    String descripcion;

    public Gestion(int id, int idCaso, String fecha, String descripcion) {
        this.id = id;
        this.caso = new Caso(idCaso);
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Gestion(int idCaso, String fecha, String descripcion) {
        this.caso = new Caso(idCaso);
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public Gestion(int id,int idCaso) {
        this.caso = new Caso(idCaso);
        this.id = id;
    }

    public Gestion(int id) {
        this.id = id;
    }

    public Gestion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return descripcion;
    }
}
