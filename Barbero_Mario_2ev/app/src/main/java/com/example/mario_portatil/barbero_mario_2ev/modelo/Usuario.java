package com.example.mario_portatil.barbero_mario_2ev.modelo;

/**
 * Created by Mario-Portatil on 02/02/2018.
 */

public class Usuario {

    int id;
    String login;
    String contrasena;
    String fecha;

    public Usuario(int id, String login, String contrasena, String fecha) {
        this.id = id;
        this.login = login;
        this.contrasena = contrasena;
        this.fecha = fecha;
    }

    public Usuario(String login, String contrasena, String fecha) {
        this.login = login;
        this.contrasena = contrasena;
        this.fecha = fecha;
    }

    public Usuario(String login, String contrasena) {
        this.login = login;
        this.contrasena = contrasena;
    }

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
