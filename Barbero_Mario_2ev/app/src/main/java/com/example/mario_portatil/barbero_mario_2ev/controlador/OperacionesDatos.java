package com.example.mario_portatil.barbero_mario_2ev.controlador;

import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Notas;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Usuario;

import java.util.ArrayList;

/**
 * Created by Mario on 31/12/2017.
 */

public class OperacionesDatos {

    public static ArrayList<Alumno> misAlumnos;
    public static ArrayList<Notas> misNotas;
    public static ArrayList<Usuario> misUsuarios;


    public static void agregarNotasAlArray() {
        misNotas = new ArrayList<>();

        misNotas.add(new Notas(1,"2017-04-21",8));
        misNotas.add(new Notas(2,"2018-01-21",4));
        misNotas.add(new Notas(3,"2017-12-21",6));
    }

    public static void agragarAlumnosAlArray() {

        misAlumnos = new ArrayList<>();

        misAlumnos.add(new Alumno("Mario"));
        misAlumnos.add(new Alumno("Julia"));
        misAlumnos.add(new Alumno("Antonio"));
        misAlumnos.add(new Alumno("Eric"));
        misAlumnos.add(new Alumno("Lucia"));
    }

    public static void agregarUsuariosAlArray() {
        misUsuarios = new ArrayList<>();

        misUsuarios.add(new Usuario("Mario", "elbarber", "2018-01-28"));
        misUsuarios.add(new Usuario("Lucia", "lalucia", "2017-11-21"));
        misUsuarios.add(new Usuario("Luis", "elluis", "2018-01-15"));
    }
}




