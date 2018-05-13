package com.acme.mario_ej1_abogados_bd.modelo;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by Mario on 29/01/2018.
 */

public class Caso {

    int id;
    String denominacion;
    String fechaApertura;
    String caracteristicas;
    Abogado abogado;

    public Caso(int id, String denominacion, String fechaApertura, String caracteristicas, String codColegiado) {
        this.id = id;
        this.denominacion = denominacion;
        this.fechaApertura = fechaApertura;
        this.caracteristicas = caracteristicas;
        this.abogado = new Abogado(codColegiado);
    }

    public Caso(String denominacion, String fechaApertura, String caracteristicas, String codColegiado) {
        this.denominacion = denominacion;
        this.fechaApertura = fechaApertura;
        this.caracteristicas = caracteristicas;
        this.abogado = new Abogado(codColegiado);
    }

    public Caso(String denominacion) {
        this.denominacion = denominacion;
    }

    public Caso(int id) {
        this.id = id;
    }

    public Caso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Abogado getAbogado() {
        return abogado;
    }

    public void setAbogado(Abogado abogado) {
        this.abogado = abogado;
    }

    @Override
    public String toString(){
        return denominacion;
    }

}
