package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mario_portatil.barbero_mario_2ev.R;

public class Menu extends AppCompatActivity {

    Button notas, consultas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        consultas=(Button)findViewById(R.id.btnConsultar);
        notas=(Button)findViewById(R.id.btnAltaNotas);

        consultas.setOnClickListener(escucha);
        notas.setOnClickListener(escucha2);
    }

    View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Menu.this, TodosLosAlumnos.class);
            startActivity(intent);
        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Menu.this, Alta_Notas.class);
            startActivity(intent);
        }
    };
}
