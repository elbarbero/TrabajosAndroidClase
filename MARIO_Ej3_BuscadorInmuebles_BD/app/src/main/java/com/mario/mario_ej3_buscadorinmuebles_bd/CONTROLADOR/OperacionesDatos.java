package com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR;

import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Zona;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;
import com.mario.mario_ej3_buscadorinmuebles_bd.VISTA.BusquedaInmuebles;

import java.util.ArrayList;

/**
 * Created by Mario on 31/12/2017.
 */

public class OperacionesDatos {

    public static ArrayList<Zona> misZonas;
    public static ArrayList<Inmueble> misInmuebles;

    public static void agregarZonasAlArray() {
        misZonas = new ArrayList<>();

        misZonas.add(new Zona("Zona A"));
        misZonas.add(new Zona("Zona B"));
        misZonas.add(new Zona("Zona C"));
        misZonas.add(new Zona("Zona D"));
        misZonas.add(new Zona("Zona E"));
    }

    public static void agregarInmueblesAlArray() {

        misInmuebles = new ArrayList<>();

        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Burgos", 123.23, 0, 3, R.drawable.iburgos, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Madrid", 1521.03, 1, 2, R.drawable.imadrid, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Asturias", 123.23, 2, 3, R.drawable.iasturias, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Toledo", 123.23, 0, 1, R.drawable.itoledo, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Sevilla", 123.23, 1, 2, R.drawable.isevilla, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Zaragoza", 455, 0, 3, R.drawable.izaragoza, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Teruel", 1212, 1, 2, R.drawable.iteruel, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Santander", 500, 2, 3, R.drawable.isantander, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Vigo", 600.24, 0, 1, R.drawable.ivigo, 0));
        misInmuebles.add(new Inmueble((int) (Math.random() * 5)+1, "Cuenca", 354, 1, 2, R.drawable.icuenca, 0));
    }
}




