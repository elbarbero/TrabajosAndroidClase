package com.mario.fragments.modelo;


public class Correo {

        int imagenRemitente;
        String idCorreo;
        String nombreR;
        String asunto;
        String contenido;

    public Correo(String idCorreo, int imagenRemitente, String nombreR, String asunto, String contenido)
        {
            this.idCorreo=idCorreo;
            this.imagenRemitente = imagenRemitente;
            this.nombreR = nombreR;
            this.asunto = asunto;
            this.contenido = contenido;
        }

    public String getIdCorreo() {
        return idCorreo;
    }
    public int getImagenRemitente() {
        return imagenRemitente;
    }

    public String getNombreR() {
        return nombreR;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }



}
