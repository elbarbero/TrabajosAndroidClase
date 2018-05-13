package com.mario.fragments.modelo;

import java.util.ArrayList;

/**
 * Created by adminmj on 13/12/2017.
 */

public class Usuario {

    String nombre;
    String curso;
    ArrayList<Correo> listaCorreos;

    public Usuario()
    {
        listaCorreos=new ArrayList<>();
    }
    public ArrayList<Correo> getListaCorreos() {
        return listaCorreos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }


}


