package com.example.mario.mario_ej2_pisos.VISTA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej2_pisos.MODELO.Inmobiliaria;
import com.example.mario.mario_ej2_pisos.MODELO.Piso;
import com.example.mario.mario_ej2_pisos.R;

import java.util.ArrayList;
import java.util.List;

public class DatosPiso extends AppCompatActivity {

    Button atras;
    TextView nombre;
    TextView ubicacion;
    TextView precio;
    TextView garaje;
    TextView trastero;
    TextView habitaciones;
    TextView banos;
    TextView metros;
    TextView anos;
    ImageView foto;
    public List<Piso> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_piso);
        Piso miPiso = (Piso) getIntent().getExtras().getParcelable("p");

        atras=(Button)findViewById(R.id.btnAtras);
        nombre=(TextView)findViewById(R.id.txtNombre);
        ubicacion=(TextView)findViewById(R.id.txtUbicacion);
        precio=(TextView)findViewById(R.id.txtPrecio);
        garaje=(TextView)findViewById(R.id.txtGaraje);
        trastero=(TextView)findViewById(R.id.txtTrastero);
        habitaciones=(TextView)findViewById(R.id.txtHabitaciones);
        banos=(TextView)findViewById(R.id.txtBanos);
        metros=(TextView)findViewById(R.id.txtTamanno);
        anos=(TextView)findViewById(R.id.txtAnnos);
        foto = (ImageView) findViewById(R.id.imgFoto);

        nombre.setText(miPiso.getNombre());
        ubicacion.setText(miPiso.getUbicación());
        precio.setText(miPiso.getPrecio());
        garaje.setText(miPiso.getGaraje());
        trastero.setText(miPiso.getTrastero());
        habitaciones.setText(miPiso.getnHabitaciones());
        banos.setText(miPiso.getnBanos());
        metros.setText(miPiso.getmCuadrados());
        anos.setText(miPiso.getAntiguedad());
        foto.setImageResource(miPiso.getFoto());
        atras.setOnClickListener(escuchar);

    }

    View.OnClickListener escuchar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(DatosPiso.this, MainScrollingActivity.class);
            startActivity(intent);
        }
    };

}