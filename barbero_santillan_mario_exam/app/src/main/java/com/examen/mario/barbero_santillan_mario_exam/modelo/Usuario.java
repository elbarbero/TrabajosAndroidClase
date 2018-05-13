package com.examen.mario.barbero_santillan_mario_exam.modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by alumnoDAM on 01/12/2017.
 */

public class Usuario implements Parcelable{

    String nombre;
    String contrasenna;
    ArrayList<Receta>mireceta;

    public Usuario(String nombre, String contrasenna) {
        this.nombre = nombre;
        this.contrasenna = contrasenna;
        mireceta=new ArrayList<>();
    }

    public Usuario( ArrayList<Receta> mireceta) {

        this.mireceta = mireceta;
        mireceta=new ArrayList<>();
    }

    public Usuario() {
    }


    public Usuario(Parcel in) {
        nombre = in.readString();
        contrasenna = in.readString();
        mireceta = in.createTypedArrayList(Receta.CREATOR);
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public ArrayList<Receta> getMireceta() {
        return mireceta;
    }

    public void setMireceta(ArrayList<Receta> mireceta) {
        this.mireceta = mireceta;
    }



    public void AnadirReceta(String nombre, String tiempo, String raciones, String proceso, String ingrediente, int foto){
        mireceta.add(new Receta(nombre,tiempo,raciones,proceso,ingrediente,foto));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(contrasenna);
        dest.writeTypedList(mireceta);
    }
}
