package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;

public class Consultar extends AppCompatActivity {

    Button buscar;
    DataBaseManagerAlumnos managerAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        managerAlumnos=new DataBaseManagerAlumnos(this);

        buscar=(Button)findViewById(R.id.btnBuscar);
    }
}
