package com.example.mario.mario_ej1_correo.CONTROLADOR;

import android.view.View;

import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.VISTA.Inicio;
import com.example.mario.mario_ej1_correo.VISTA.Login;

import java.util.ArrayList;

/**
 * Created by Mario on 31/12/2017.
 */

public class OperacionesDatos {

    public static ArrayList<Usuario> datos;

    public static void inicializar(View v) {
        datos = new ArrayList<>();
        if(Inicio.miUsuario.get(Login.pos).getMiCorreo().size()>0){
            for (int a = 0; a < Inicio.miUsuario.get(Login.pos).getMiCorreo().size(); a++) {
                int codRemitente = Integer.parseInt(Inicio.miUsuario.get(Login.pos).getMiCorreo().get(a).getCodigo());
                datos.add(new Usuario(Inicio.miUsuario.get(codRemitente).getFoto(), Inicio.miUsuario.get(Login.pos).getMiCorreo()));
            }
        }
    }
}
