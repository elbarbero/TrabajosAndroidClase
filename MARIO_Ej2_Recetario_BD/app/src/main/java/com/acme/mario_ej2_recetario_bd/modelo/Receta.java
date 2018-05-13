package com.acme.mario_ej2_recetario_bd.modelo;

import java.util.ArrayList;

/**
 * Created by Mario on 27/01/2018.
 */

public class Receta {

    int id;
    Categoria categoria;
    String nombre;
    int tiempo;
    ArrayList<Ingredientes> misIngredientes;
    int foto;

    public Receta(int id, int id_categoria, String nombre, int tiempo, ArrayList<Ingredientes> misIngredientes, int foto) {
        this.id = id;
        this.categoria = new Categoria(id_categoria);
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.misIngredientes = misIngredientes;
        this.foto = foto;
    }

    public Receta(int id_categoria, String nombre, int tiempo, ArrayList<Ingredientes> misIngredientes, int foto) {
        this.categoria = new Categoria(id_categoria);
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.misIngredientes = misIngredientes;
        this.foto = foto;
    }

    public Receta(String nombre, int foto) {
        this.nombre = nombre;
        this.foto = foto;
    }

    public Receta(int id) {
        this.id = id;
    }

    public Receta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public ArrayList<Ingredientes> getMisIngredientes() {
        return misIngredientes;
    }

    public void setMisIngredientes(ArrayList<Ingredientes> misIngredientes) {
        this.misIngredientes = misIngredientes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void AnadirIngredientes(String nombre){
        misIngredientes.add(new Ingredientes(nombre));
    }
}

