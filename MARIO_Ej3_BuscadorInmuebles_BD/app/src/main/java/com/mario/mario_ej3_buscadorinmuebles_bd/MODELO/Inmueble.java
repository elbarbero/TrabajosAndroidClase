package com.mario.mario_ej3_buscadorinmuebles_bd.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumnoDAM on 19/01/2018.
 */

public class Inmueble implements Parcelable{
    int id;
    Zona id_zona;
    String ubicacion;
    double precio;
    int tipo_propiedad;
    int num_habitaciones;
    int ruta_imagen;
    int num_consultas;

    public Inmueble(int id) {
        this.id = id;
    }

    public Inmueble(int id, int id_zona, String ubicacion, double precio, int tipo_propiedad, int num_habitaciones, int ruta_imagen, int num_consultas) {
        this.id = id;
        this.id_zona = new Zona(id_zona);
        //this.id_zona = id_zona;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.tipo_propiedad = tipo_propiedad;
        this.num_habitaciones = num_habitaciones;
        this.ruta_imagen = ruta_imagen;
        this.num_consultas = num_consultas;
    }

    public Inmueble(int id_zona, String ubicacion, double precio, int tipo_propiedad, int num_habitaciones, int ruta_imagen, int num_consultas) {
        this.id_zona = new Zona(id_zona);
        //this.id_zona = id_zona;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.tipo_propiedad = tipo_propiedad;
        this.num_habitaciones = num_habitaciones;
        this.ruta_imagen = ruta_imagen;
        this.num_consultas = num_consultas;
    }

    public Inmueble() {
    }

    protected Inmueble(Parcel in) {
        id = in.readInt();
        id_zona = in.readParcelable(Zona.class.getClassLoader());
        ubicacion = in.readString();
        precio = in.readDouble();
        tipo_propiedad = in.readInt();
        num_habitaciones = in.readInt();
        ruta_imagen = in.readInt();
        num_consultas = in.readInt();
    }

    public static final Creator<Inmueble> CREATOR = new Creator<Inmueble>() {
        @Override
        public Inmueble createFromParcel(Parcel in) {
            return new Inmueble(in);
        }

        @Override
        public Inmueble[] newArray(int size) {
            return new Inmueble[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Zona getId_zona() {
        return id_zona;
    }

    public void setId_zona(Zona id_zona) {
        this.id_zona = id_zona;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getTipo_propiedad() {
        return tipo_propiedad;
    }

    public void setTipo_propiedad(int tipo_propiedad) {
        this.tipo_propiedad = tipo_propiedad;
    }

    public int getNum_habitaciones() {
        return num_habitaciones;
    }

    public void setNum_habitaciones(int num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public int getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(int ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public int getNum_consultas() {
        return num_consultas;
    }

    public void setNum_consultas(int num_consultas) {
        this.num_consultas = num_consultas;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(id_zona, flags);
        dest.writeString(ubicacion);
        dest.writeDouble(precio);
        dest.writeInt(tipo_propiedad);
        dest.writeInt(num_habitaciones);
        dest.writeInt(ruta_imagen);
        dest.writeInt(num_consultas);
    }
}
