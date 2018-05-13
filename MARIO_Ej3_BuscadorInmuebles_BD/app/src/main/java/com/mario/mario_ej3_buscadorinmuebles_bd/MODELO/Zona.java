package com.mario.mario_ej3_buscadorinmuebles_bd.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumnoDAM on 19/01/2018.
 */

public class Zona implements Parcelable{
    int id;
    String denominacion;

    public Zona(int id, String denominacion) {
        this.id = id;
        this.denominacion = denominacion;
    }

    public Zona(int id) {
        this.id = id;
    }

    public Zona() {
    }

    protected Zona(Parcel in) {
        id = in.readInt();
        denominacion = in.readString();
    }

    public static final Creator<Zona> CREATOR = new Creator<Zona>() {
        @Override
        public Zona createFromParcel(Parcel in) {
            return new Zona(in);
        }

        @Override
        public Zona[] newArray(int size) {
            return new Zona[size];
        }
    };

    public int getId() {
        return id;
    }

    public Zona(String denominacion) {
        this.denominacion = denominacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(denominacion);
    }
}
