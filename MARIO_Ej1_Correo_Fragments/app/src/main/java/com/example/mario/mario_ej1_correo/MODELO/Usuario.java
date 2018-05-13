package com.example.mario.mario_ej1_correo.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mario on 01/11/2017.
 */

public class Usuario implements Parcelable {

    String nombre;
    String contrasena;
    String codigoCorreo;
    int foto;
    ArrayList<Correo> miCorreo;

    public Usuario(String nombre, String contrasena,int foto) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.foto = foto;
        miCorreo = new ArrayList<>();
    }

    public Usuario(int foto, ArrayList<Correo> miCorreo) {
        this.foto = foto;
        this.miCorreo = miCorreo;
        miCorreo = new ArrayList<>();
    }

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        nombre = in.readString();
        contrasena = in.readString();
        codigoCorreo = in.readString();
        foto = in.readInt();
        miCorreo = in.createTypedArrayList(Correo.CREATOR);
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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public ArrayList<Correo> getMiCorreo() {
        return miCorreo;
    }

    public void setMiCorreo(ArrayList<Correo> miCorreo) {
        this.miCorreo = miCorreo;
    }

    public String getCodigoCorreo() {
        return codigoCorreo;
    }

    public void setCodigoCorreo(String codigoCorreo) {
        this.codigoCorreo = codigoCorreo;
    }

    public void AnadirCorreo(String asunto, String texto,String codigo){
        miCorreo.add(new Correo(asunto,texto,codigo));
    }

    @Override
    public String toString(){
        return nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(contrasena);
        dest.writeString(codigoCorreo);
        dest.writeInt(foto);
        dest.writeTypedList(miCorreo);
    }
}
