package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.R;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {

    Button continuar;
    Button autor;

    public static ArrayList<Usuario> miUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        miUsuario = new ArrayList<Usuario>();

        continuar = (Button) findViewById(R.id.btnContinuar);
        autor = (Button) findViewById(R.id.btnAutor);

        autor.setOnClickListener(escucha);
        continuar.setOnClickListener(login);

        cargarUsuarios();
    }

    public View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Inicio.this, Autor.class);
            startActivity(intent);
        }
    };

    public View.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Inicio.this, Login.class);
            startActivity(intent);
        }
    };

    public void cargarUsuarios() {

        Resources res = this.getResources();
        String[] nombre = res.getStringArray(R.array.usuarios);
        String[] contra = res.getStringArray(R.array.contraseñas);
        int[] imagenes = {R.drawable.icono1, R.drawable.icono2, R.drawable.icono3, R.drawable.icono4};

        for (int i = 0; i < nombre.length; i++) {
            miUsuario.add(new Usuario(nombre[i], contra[i], imagenes[i]));
        }
    }

}
