package com.examen.mario.barbero_santillan_mario_exam.modelo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumnoDAM on 01/12/2017.
 */

public class Receta implements Parcelable{
    String nombre;
    String tiempo;
    String raciones;
    String proceso;
    String ingrediente;
    int foto;

    public Receta(String nombre, String tiempo, String raciones, String proceso, String ingrediente, int foto) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.raciones = raciones;
        this.proceso = proceso;
        this.ingrediente = ingrediente;
        this.foto = foto;
    }

    public Receta(String nombre, String tiempo, String raciones, String ingrediente, int foto) {
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.raciones = raciones;
        this.ingrediente = ingrediente;
        this.foto = foto;
    }

    protected Receta(Parcel in) {
        nombre = in.readString();
        tiempo = in.readString();
        raciones = in.readString();
        proceso = in.readString();
        ingrediente = in.readString();
        foto = in.readInt();
    }

    public static final Creator<Receta> CREATOR = new Creator<Receta>() {
        @Override
        public Receta createFromParcel(Parcel in) {
            return new Receta(in);
        }

        @Override
        public Receta[] newArray(int size) {
            return new Receta[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getRaciones() {
        return raciones;
    }

    public void setRaciones(String raciones) {
        this.raciones = raciones;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
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
        dest.writeString(tiempo);
        dest.writeString(raciones);
        dest.writeString(proceso);
        dest.writeString(ingrediente);
        dest.writeInt(foto);
    }
}
