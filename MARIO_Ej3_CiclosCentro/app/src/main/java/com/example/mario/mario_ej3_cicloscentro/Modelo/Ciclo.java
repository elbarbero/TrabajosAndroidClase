package com.example.mario.mario_ej3_cicloscentro.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mario on 12/11/2017.
 */

public class Ciclo implements Parcelable {
    String id;
    String nombre;
    String tipo;
    String duracion;
    private int imagen;
    ArrayList<Modulo> misModulos;//hacerlo con pacelable de oarrays de objetos

    public Ciclo() {
    }

    public Ciclo(String nombre, int imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public Ciclo(String nombre, String tipo, String duracion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.duracion = duracion;
        misModulos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public ArrayList<Modulo> getMisModulos() {
        return misModulos;
    }

    public void setMisModulos(ArrayList<Modulo> misModulos) {
        this.misModulos = misModulos;
    }

    public void AnadirModulo(String[] nombre){
        for(int i=0;i<nombre.length;i++){
            misModulos.add(new Modulo(nombre[i]));
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.tipo);
        dest.writeString(this.duracion);
        dest.writeInt(this.imagen);
       dest.writeList(this.misModulos);
    }

    protected Ciclo(Parcel in) {
        this.id = in.readString();
        this.nombre = in.readString();
        this.tipo = in.readString();
        this.duracion = in.readString();
        this.imagen = in.readInt();
        this.misModulos = new ArrayList<Modulo>();
        in.readList(this.misModulos, Modulo.class.getClassLoader());
    }

    public static final Creator<Ciclo> CREATOR = new Creator<Ciclo>() {
        @Override
        public Ciclo createFromParcel(Parcel source) {
            return new Ciclo(source);
        }

        @Override
        public Ciclo[] newArray(int size) {
            return new Ciclo[size];
        }
    };
}
