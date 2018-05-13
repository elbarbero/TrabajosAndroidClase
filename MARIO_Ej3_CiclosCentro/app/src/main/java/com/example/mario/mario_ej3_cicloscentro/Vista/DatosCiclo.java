package com.example.mario.mario_ej3_cicloscentro.Vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mario.mario_ej3_cicloscentro.Controlador.AdaptadorCiclos;
import com.example.mario.mario_ej3_cicloscentro.Modelo.Ciclo;
import com.example.mario.mario_ej3_cicloscentro.Modelo.Modulo;
import com.example.mario.mario_ej3_cicloscentro.R;

import java.util.ArrayList;
import java.util.List;

public class DatosCiclo extends AppCompatActivity {

    EditText nomModulo;
    TextView nomCiclo;
    TextView tipo;
    TextView duracion;
    private List<Modulo> datos;
    private RecyclerView reciclado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datosciclo);
        nomCiclo = (TextView) findViewById(R.id.txtNombreCiclo);
        tipo = (TextView) findViewById(R.id.txtTipo);
        duracion = (TextView) findViewById(R.id.txtDuracion);

        Ciclo miCiclo = (Ciclo) getIntent().getExtras().getParcelable("c");
        ArrayList<Modulo> modulos = miCiclo.getMisModulos();

        nomCiclo.setText(miCiclo.getNombre());
        tipo.setText(miCiclo.getTipo());
        duracion.setText(miCiclo.getDuracion());

        inicializarCiclos(miCiclo);
        reciclado = (RecyclerView) findViewById(R.id.reciclerCiclos);
        reciclado.setLayoutManager(new LinearLayoutManager(this));
        reciclado.setAdapter(new AdaptadorCiclos(datos));
    }

    private void inicializarCiclos(Ciclo miCiclo) {//INICIALIZAMOS LOS MODULOS DEL CICLO PARA MOSTRARLES EN EL RECICLER VIEW
        ArrayList<Modulo> modulos = miCiclo.getMisModulos();
        datos = new ArrayList<>();
        for (int i = 0; i < modulos.size(); i++) {
            datos.add(new Modulo(modulos.get(i).getNombre()));
        }
    }

}
