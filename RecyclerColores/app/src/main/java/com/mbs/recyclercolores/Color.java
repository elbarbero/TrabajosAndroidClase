package com.mbs.recyclercolores;

/**
 * Created by alumnoDAM on 16/10/2017.
 */

public class Color {

    private String color;
    private String colorIngles;//nombre del color en ingles. Así como los tiene puesto android por defecto

    public Color(String color,String colorIngles) {

        this.color = color;
        this.colorIngles=colorIngles;
    }

    public String getColor() {
        return color;
    }

    public String getColorIngles() {
        return colorIngles;
    }
}
