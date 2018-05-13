package com.example.mario.mario_ej2_pisos.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mario on 17/11/2017.
 */

public class Piso implements Parcelable {

    private String nombre;
    private String precio;
    private String Ubicación;
    private String nHabitaciones;
    private String nBanos;
    private String garaje;
    private String trastero;
    private String mCuadrados;
    private String antiguedad;
    int foto;

    public Piso(String nombre, String precio, String ubicación, int foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.Ubicación = ubicación;
        this.foto = foto;
    }

    public Piso(String nombre, String precio, String ubicación, String nHabitaciones, String nBanos, String garaje,
                String trastero, String mCuadrados, String antiguedad, int foto) {
        this.nombre = nombre;
        this.precio = precio;
        this.Ubicación = ubicación;
        this.nHabitaciones = nHabitaciones;
        this.nBanos = nBanos;
        this.garaje = garaje;
        this.trastero = trastero;
        this.mCuadrados = mCuadrados;
        this.antiguedad = antiguedad;
        this.foto = foto;
    }

    public Piso() {
    }

    public Piso(Parcel in) {
        nombre = in.readString();
        precio = in.readString();
        Ubicación = in.readString();
        nHabitaciones = in.readString();
        nBanos = in.readString();
        garaje = in.readString();
        trastero = in.readString();
        mCuadrados = in.readString();
        antiguedad = in.readString();
        foto = in.readInt();
    }

    public static final Creator<Piso> CREATOR = new Creator<Piso>() {
        @Override
        public Piso createFromParcel(Parcel in) {
            return new Piso(in);
        }

        @Override
        public Piso[] newArray(int size) {
            return new Piso[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getUbicación() {
        return Ubicación;
    }

    public void setUbicación(String ubicación) {
        Ubicación = ubicación;
    }

    public String getnHabitaciones() {
        return nHabitaciones;
    }

    public void setnHabitaciones(String nHabitaciones) {
        this.nHabitaciones = nHabitaciones;
    }

    public String getnBanos() {
        return nBanos;
    }

    public void setnBanos(String nBanos) {
        this.nBanos = nBanos;
    }

    public String getGaraje() {
        return garaje;
    }

    public void setGaraje(String garaje) {
        this.garaje = garaje;
    }

    public String getTrastero() {
        return trastero;
    }

    public void setTrastero(String trastero) {
        this.trastero = trastero;
    }

    public String getmCuadrados() {
        return mCuadrados;
    }

    public void setmCuadrados(String mCuadrados) {
        this.mCuadrados = mCuadrados;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(precio);
        dest.writeString(Ubicación);
        dest.writeString(nHabitaciones);
        dest.writeString(nBanos);
        dest.writeString(garaje);
        dest.writeString(trastero);
        dest.writeString(mCuadrados);
        dest.writeString(antiguedad);
        dest.writeInt(foto);
    }
}
