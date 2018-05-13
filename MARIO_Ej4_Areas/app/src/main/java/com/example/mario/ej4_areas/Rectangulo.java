package com.example.mario.ej4_areas;

/**
 * Created by Mario on 07/10/2017.
 */

public class Rectangulo  implements Figuras {

    double base;
    double altura;

    public Rectangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public String calcular() {
        double area = 0;
        area=base*altura;
        return(Double.valueOf(area).toString());
    }
}
