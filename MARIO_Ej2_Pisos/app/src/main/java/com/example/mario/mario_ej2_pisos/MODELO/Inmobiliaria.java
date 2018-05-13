package com.example.mario.mario_ej2_pisos.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mario on 17/11/2017.
 */

public class Inmobiliaria implements Parcelable{

    String nombre;
    ArrayList<Piso>miAlquiler;

    public Inmobiliaria(String nombre) {
        this.nombre = nombre;
        miAlquiler=new ArrayList<>();
    }

    protected Inmobiliaria(Parcel in) {
        nombre = in.readString();
        miAlquiler = in.createTypedArrayList(Piso.CREATOR);
    }

    public Inmobiliaria() {
    }

    public static final Creator<Inmobiliaria> CREATOR = new Creator<Inmobiliaria>() {
        @Override
        public Inmobiliaria createFromParcel(Parcel in) {
            return new Inmobiliaria(in);
        }

        @Override
        public Inmobiliaria[] newArray(int size) {
            return new Inmobiliaria[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Piso> getMiAlquiler() {
        return miAlquiler;
    }

    public void setMiAlquiler(ArrayList<Piso> miAlquiler) {
        this.miAlquiler = miAlquiler;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeTypedList(miAlquiler);
    }
}
