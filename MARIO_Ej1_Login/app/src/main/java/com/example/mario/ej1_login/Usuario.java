package com.example.mario.ej1_login;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumnoDAM on 19/10/2017.
 */

public class Usuario implements Parcelable {
    String nombre;
    String apellido;
    String curso;
    String pass;

    public Usuario(String nombre, String apellido, String curso, String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.curso);
        dest.writeString(this.pass);
    }

    protected Usuario(Parcel in) {
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.curso = in.readString();
        this.pass = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
