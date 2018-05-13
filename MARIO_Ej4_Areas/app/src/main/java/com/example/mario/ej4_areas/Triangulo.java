package com.example.mario.ej4_areas;

/**
 * Created by Mario on 07/10/2017.
 */

public class Triangulo  implements Figuras {

    double base;
    double altura;

    public Triangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public String calcular() {
        double area = 0;
        area=(base*altura)/2;
        return(Double.valueOf(area).toString());
    }
}
