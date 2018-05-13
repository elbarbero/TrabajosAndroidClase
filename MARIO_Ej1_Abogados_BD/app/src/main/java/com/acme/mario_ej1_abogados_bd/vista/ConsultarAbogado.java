package com.acme.mario_ej1_abogados_bd.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;

import java.util.List;

public class ConsultarAbogado extends AppCompatActivity {

    Button todos;
    public static List<Abogado> listAbogado;
    DataBaseManagerAbogados managerAbogados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_abogado);
        managerAbogados=new DataBaseManagerAbogados(this);

        todos=(Button)findViewById(R.id.btnTodos);
        todos.setOnClickListener( escucha);
    }

    View.OnClickListener escucha=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listAbogado=managerAbogados.obtenerAbogadosDeBD();
            startActivity(new Intent(ConsultarAbogado.this, Info_Abogados.class));
        }
    };

}
