package com.example.mario.mario_ej2_pisos.VISTA;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mario.mario_ej2_pisos.CONTROLADOR.Adaptador;
import com.example.mario.mario_ej2_pisos.CONTROLADOR.ClickRecycler;
import com.example.mario.mario_ej2_pisos.MODELO.Inmobiliaria;
import com.example.mario.mario_ej2_pisos.MODELO.Piso;
import com.example.mario.mario_ej2_pisos.R;

import java.util.ArrayList;
import java.util.List;

public class MainScrollingActivity extends AppCompatActivity {

    public ArrayList<Piso> datosPiso;
    private RecyclerView reciclado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        datosPiso=new ArrayList<>();

        inicializar();
        reciclado = (RecyclerView) findViewById(R.id.reciclerInicial);
        reciclado.setLayoutManager(new LinearLayoutManager(this));
        reciclado.setAdapter(new Adaptador(datosPiso, escucha));
    }

    private ClickRecycler escucha = new ClickRecycler() {
        @Override
        public void onClick(int posicion) {

            Piso p = (datosPiso.get(posicion));
            Intent intent = new Intent(MainScrollingActivity.this, DatosPiso.class);
            intent.putExtra("p", p);

            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                lanzarSitio();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lanzarSitio() {
        new AlertDialog.Builder(this).setTitle(MainScrollingActivity.this.getResources().getString(R.string.informacion1).toUpperCase() + "\n")
                .setMessage(MainScrollingActivity.this.getResources().getString(R.string.informacion2)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion3)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion4)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion5))
                .setNegativeButton("OK", null).show();
    }

    private void inicializar() {
        Resources res = MainScrollingActivity.this.getResources();
        String[] nombre = res.getStringArray(R.array.nombres);
        String[] ubicacion = res.getStringArray(R.array.ubicaciones);
        String[] precio = res.getStringArray(R.array.precios);
        String[] nhabita = res.getStringArray(R.array.NumHabita);
        String[] nBano = res.getStringArray(R.array.NumBanos);
        String[] garaje = res.getStringArray(R.array.garaje);
        String[] trastero = res.getStringArray(R.array.trastero);
        String[] tamano = res.getStringArray(R.array.metros);
        String[] annos = res.getStringArray(R.array.antiguedad);

        new Inmobiliaria(MainScrollingActivity.this.getResources().getString(R.string.informacion2));
        datosPiso.add(new Piso(nombre[0], precio[0], ubicacion[0], nhabita[0], nBano[0], garaje[1], trastero[0], tamano[0], annos[0], R.drawable.piso1));
        datosPiso.add(new Piso(nombre[1], precio[1], ubicacion[1], nhabita[2], nBano[0], garaje[0], trastero[1], tamano[1], annos[1], R.drawable.piso2));
        datosPiso.add(new Piso(nombre[2], precio[2], ubicacion[2], nhabita[1], nBano[1], garaje[1], trastero[0], tamano[2], annos[2], R.drawable.piso3));
        datosPiso.add(new Piso(nombre[3], precio[3], ubicacion[3], nhabita[1], nBano[1], garaje[0], trastero[1], tamano[3], annos[3], R.drawable.piso4));
        datosPiso.add(new Piso(nombre[4], precio[4], ubicacion[4], nhabita[0], nBano[1], garaje[1], trastero[0], tamano[4], annos[4], R.drawable.piso5));
        datosPiso.add(new Piso(nombre[5], precio[5], ubicacion[5], nhabita[0], nBano[0], garaje[0], trastero[1], tamano[5], annos[5], R.drawable.piso6));
        datosPiso.add(new Piso(nombre[6], precio[6], ubicacion[6], nhabita[2], nBano[1], garaje[1], trastero[0], tamano[6], annos[6], R.drawable.piso7));
    }
}





