package com.example.mario.ej4_areas;

/**
 * Created by Mario on 07/10/2017.
 */

public class Cuadrado implements Figuras {

   double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public String calcular() {
        double area = 0;
        area=lado*lado;
        return(Double.valueOf(area).toString());
    }
}
