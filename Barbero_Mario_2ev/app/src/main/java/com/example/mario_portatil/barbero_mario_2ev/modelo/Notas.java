package com.example.mario_portatil.barbero_mario_2ev.modelo;

/**
 * Created by Mario-Portatil on 02/02/2018.
 */

public class Notas {

    int id;
    Alumno alumno;
    String fechaControl;
    float calificacion;

    public Notas(int id, int codAlumno, String fechaControl, float calificacion) {
        this.id = id;
        this.alumno = new Alumno(codAlumno);
        this.fechaControl = fechaControl;
        this.calificacion = calificacion;
    }

    public Notas( int codAlumno, String fechaControl, float calificacion) {
        this.alumno = new Alumno(codAlumno);
        this.fechaControl = fechaControl;
        this.calificacion = calificacion;
    }

    public Notas( int codAlumno, float calificacion) {
        this.alumno = new Alumno(codAlumno);
        this.calificacion = calificacion;
    }

    public Notas(int id) {
        this.id = id;
    }

    public Notas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getFechaControl() {
        return fechaControl;
    }

    public void setFechaControl(String fechaControl) {
        this.fechaControl = fechaControl;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString(){
        return String.valueOf(id);
    }
}
