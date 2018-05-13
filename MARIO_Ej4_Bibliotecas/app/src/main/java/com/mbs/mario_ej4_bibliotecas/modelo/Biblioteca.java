package com.mbs.mario_ej4_bibliotecas.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by MARIO on 08/10/2017..
 */

public class Biblioteca implements Parcelable {

    private String nombre;
    private String direccion;
    private String urll;
    private int telefono;
    private String correo;
    private int imagen;

    public Biblioteca() {

    }

    public Biblioteca(String nombre, String direccion, int imagen) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public Biblioteca(String nombre, String urll, int telefono, String correo) {
        this.nombre = nombre;
        this.urll = urll;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUrll() {
        return urll;
    }

    public void setUrll(String urll) {
        this.urll = urll;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.nombre);
        dest.writeString(this.urll);
        dest.writeInt(this.telefono);
        dest.writeString(this.correo);
    }

    public Biblioteca(Parcel in) {//AQUI LEEMOS DEL PARCELABLE
        this.nombre = in.readString();
        this.urll = in.readString();
        this.telefono = in.readInt();
        this.correo = in.readString();
    }

    public static final Creator<Biblioteca> CREATOR = new Creator<Biblioteca>() {//ESTO LO EJECUTA CUANDO LLEGA AL DESTINO
        @Override
        public Biblioteca createFromParcel(Parcel source) {
            return new Biblioteca(source);
        }

        //TE CREA UN OBJETO DE TIPO BIBLIOTECA Y TE CREA LOS THIS
        @Override
        public Biblioteca[] newArray(int size) {
            return new Biblioteca[size];
        }
    };
}
