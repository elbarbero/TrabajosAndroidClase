package com.example.mario.mario_ej1_correo.MODELO;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mario on 01/11/2017.
 */

public class Correo implements Parcelable {

    String codigo;
    String asunto;
    String texto;

    public Correo( String asunto, String texto,String codigo ) {
        this.asunto = asunto;
        this.texto = texto;
        this.codigo=codigo;
    }

    public Correo(String asunto) {
        this.asunto = asunto;
    }

    public Correo() {
    }

    protected Correo(Parcel in) {
        codigo = in.readString();
        asunto = in.readString();
        texto = in.readString();
    }

    public static final Creator<Correo> CREATOR = new Creator<Correo>() {
        @Override
        public Correo createFromParcel(Parcel in) {
            return new Correo(in);
        }

        @Override
        public Correo[] newArray(int size) {
            return new Correo[size];
        }
    };

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(asunto);
        dest.writeString(texto);
    }
}
