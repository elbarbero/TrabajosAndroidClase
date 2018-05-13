package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.examen.mario.barbero_santillan_mario_exam.R;
import com.examen.mario.barbero_santillan_mario_exam.modelo.Receta;

import java.util.ArrayList;

public class DatosReceta extends AppCompatActivity {

    Button atras;
    TextView nombre;
    TextView tiempo;
    TextView raciones;
    TextView proceso;
    TextView ingrediente;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_receta);

        ArrayList<Receta> misRecetas = getIntent().getExtras().getParcelableArrayList("r");
        int pos = getIntent().getExtras().getInt("r2");

        nombre = (TextView) findViewById(R.id.txtNombre);
        tiempo = (TextView) findViewById(R.id.txtTiempo);
        raciones = (TextView) findViewById(R.id.txtRaciones);
        proceso = (TextView) findViewById(R.id.txtProceso);
        ingrediente = (TextView) findViewById(R.id.txtIngrediente);
        imagen=(ImageView)findViewById(R.id.imgFoto);
        atras=(Button)findViewById(R.id.btnAtras);

        atras.setOnClickListener(escucha);
        nombre.setText(misRecetas.get(pos).getNombre());
        tiempo.setText(misRecetas.get(pos).getTiempo());
        raciones.setText(misRecetas.get(pos).getRaciones());
        proceso.setText(misRecetas.get(pos).getProceso());
        ingrediente.setText(misRecetas.get(pos).getIngrediente());
        imagen.setImageResource(misRecetas.get(pos).getFoto());
    }

    View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public void onBackPressed() {
    }

}
