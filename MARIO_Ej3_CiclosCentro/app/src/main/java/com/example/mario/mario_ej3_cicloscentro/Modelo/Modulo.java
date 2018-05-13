package com.example.mario.mario_ej3_cicloscentro.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mario on 12/11/2017.
 */

public class Modulo implements Parcelable {
    String idCurso;
    String nombre;

    public Modulo(String nombre) {
        this.nombre = nombre;
    }

    public Modulo() {

    }

    protected Modulo(Parcel in) {
        idCurso = in.readString();
        nombre = in.readString();
    }

    public static final Creator<Modulo> CREATOR = new Creator<Modulo>() {
        @Override
        public Modulo createFromParcel(Parcel in) {
            return new Modulo(in);
        }

        @Override
        public Modulo[] newArray(int size) {
            return new Modulo[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idCurso);
        parcel.writeString(nombre);
    }
}
