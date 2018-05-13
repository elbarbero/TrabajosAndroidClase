package com.mbs.recyclercard.modelo;

/**
 * Created by alumnoDAM on 25/10/2017.
 */

public class Anime {

    private String nombre;
    private int numVisitas;
    private int imagen;//LA DIRECCION DE LA IMAGEN EN HEXADECIMAL

    public Anime(String nombre, int numVisitas, int imagen) {
        this.nombre = nombre;
        this.numVisitas = numVisitas;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumVisitas() {
        return numVisitas;
    }

    public int getImagen() {
        return imagen;
    }
}
