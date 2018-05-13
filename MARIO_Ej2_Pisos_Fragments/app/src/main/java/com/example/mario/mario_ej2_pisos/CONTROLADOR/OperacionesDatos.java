package com.example.mario.mario_ej2_pisos.CONTROLADOR;


import android.content.res.Resources;
import android.view.View;

import com.example.mario.mario_ej2_pisos.MODELO.Inmobiliaria;
import com.example.mario.mario_ej2_pisos.MODELO.Piso;
import com.example.mario.mario_ej2_pisos.R;
import com.example.mario.mario_ej2_pisos.VISTA.MainScrollingActivity;


import java.util.ArrayList;

/**
 * Created by adminmj on 13/12/2017.
 */

public class OperacionesDatos {
    public static Inmobiliaria inmobiliaria=new Inmobiliaria();
    public static MainScrollingActivity main=new MainScrollingActivity();
    public static ArrayList<Piso> datosPiso;

    public static ArrayList<Piso> inicializar(View v) {
        Resources res =v.getResources();
        String[] nombre = res.getStringArray(R.array.nombres);
        String[] ubicacion = res.getStringArray(R.array.ubicaciones);
        String[] precio = res.getStringArray(R.array.precios);
        String[] nhabita = res.getStringArray(R.array.NumHabita);
        String[] nBano = res.getStringArray(R.array.NumBanos);
        String[] garaje = res.getStringArray(R.array.garaje);
        String[] trastero = res.getStringArray(R.array.trastero);
        String[] tamano = res.getStringArray(R.array.metros);
        String[] annos = res.getStringArray(R.array.antiguedad);

        datosPiso=new ArrayList<Piso>();
        new Inmobiliaria(v.getResources().getString(R.string.informacion2));
        datosPiso.add(new Piso(nombre[0], precio[0], ubicacion[0], nhabita[0], nBano[0], garaje[1], trastero[0], tamano[0], annos[0], R.drawable.piso1));
        datosPiso.add(new Piso(nombre[1], precio[1], ubicacion[1], nhabita[2], nBano[0], garaje[0], trastero[1], tamano[1], annos[1], R.drawable.piso2));
        datosPiso.add(new Piso(nombre[2], precio[2], ubicacion[2], nhabita[1], nBano[1], garaje[1], trastero[0], tamano[2], annos[2], R.drawable.piso3));
        datosPiso.add(new Piso(nombre[3], precio[3], ubicacion[3], nhabita[1], nBano[1], garaje[0], trastero[1], tamano[3], annos[3], R.drawable.piso4));
        datosPiso.add(new Piso(nombre[4], precio[4], ubicacion[4], nhabita[0], nBano[1], garaje[1], trastero[0], tamano[4], annos[4], R.drawable.piso5));
        datosPiso.add(new Piso(nombre[5], precio[5], ubicacion[5], nhabita[0], nBano[0], garaje[0], trastero[1], tamano[5], annos[5], R.drawable.piso6));
        datosPiso.add(new Piso(nombre[6], precio[6], ubicacion[6], nhabita[2], nBano[1], garaje[1], trastero[0], tamano[6], annos[6], R.drawable.piso7));
    return datosPiso;
    }
}
