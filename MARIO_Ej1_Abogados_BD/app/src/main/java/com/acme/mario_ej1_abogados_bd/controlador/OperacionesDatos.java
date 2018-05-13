package com.acme.mario_ej1_abogados_bd.controlador;

import com.acme.mario_ej1_abogados_bd.modelo.Abogado;
import com.acme.mario_ej1_abogados_bd.modelo.Caso;
import com.acme.mario_ej1_abogados_bd.modelo.Gestion;
import java.util.ArrayList;

/**
 * Created by Mario on 31/12/2017.
 */

public class OperacionesDatos {

    public static ArrayList<Abogado> misAbogados;
    public static ArrayList<Caso> misCasos;
    public static ArrayList<Gestion> misGestiones;

    public static void agregarAbogadosAlArray() {
        misAbogados = new ArrayList<>();

        misAbogados.add(new Abogado("A123456", "Mario"));
        misAbogados.add(new Abogado("B789012", "Oscar"));
        misAbogados.add(new Abogado("C345678", "Jose"));
        misAbogados.add(new Abogado("D901234", "Nuria"));
        misAbogados.add(new Abogado("C567890", "Andrea"));
        misAbogados.add(new Abogado("E123456", "David"));
    }

    public static void agregarCasosAlArray(){

        misCasos = new ArrayList<>();

        misCasos.add(new Caso("Caso 1","2017-04-21","Caso muy largo1","A123456"));
        misCasos.add(new Caso("Caso 2","2017-04-21","Caso muy largo2","B789012"));
        misCasos.add(new Caso("Caso 3","2017-04-21","Caso muy largo3","C345678"));
        misCasos.add(new Caso("Caso 4","2017-04-21","Caso muy largo4","D901234"));
        misCasos.add(new Caso("Caso 5","2018-01-30","Caso muy largo5","D901234"));
    }

    public static void agregarGestionesAlArray(){

        misGestiones = new ArrayList<>();

        misGestiones.add(new Gestion(1, "2017-04-21", "Gestion Nº1"));
        misGestiones.add(new Gestion(3, "2017-07-21", "Gestion Nº2"));
        misGestiones.add(new Gestion(4, "2016-04-14", "Gestion Nº3"));
        misGestiones.add(new Gestion(2, "2011-04-01", "Gestion Nº4"));
        misGestiones.add(new Gestion(1, "2017-04-21", "Gestion Nº5"));
    }
}




