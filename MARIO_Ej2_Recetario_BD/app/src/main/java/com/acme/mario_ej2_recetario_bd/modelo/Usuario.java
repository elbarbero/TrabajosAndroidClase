package com.acme.mario_ej2_recetario_bd.modelo;

/**
 * Created by Mario on 27/01/2018.
 */

public class Usuario {

    int id;
    String nombre;
    String nick;
    String contrasena;

    public Usuario(int id,String nombre, String nick, String contrasena) {
        this.id=id;
        this.nombre = nombre;
        this.nick = nick;
        this.contrasena = contrasena;
    }

    public Usuario(String nombre, String nick, String contrasena) {
        this.nombre = nombre;
        this.nick = nick;
        this.contrasena = contrasena;
    }

    public Usuario(String nick, String contrasena) {
        this.nick = nick;
        this.contrasena = contrasena;
    }

    public Usuario(int id) {
        this.id=id;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
