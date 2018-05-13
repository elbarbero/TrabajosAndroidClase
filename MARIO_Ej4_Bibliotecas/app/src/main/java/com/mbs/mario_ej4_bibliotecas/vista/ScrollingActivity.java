package com.mbs.mario_ej4_bibliotecas.vista;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mbs.mario_ej4_bibliotecas.R;
import com.mbs.mario_ej4_bibliotecas.controlador.Adaptador;
import com.mbs.mario_ej4_bibliotecas.controlador.ClickRecycler;
import com.mbs.mario_ej4_bibliotecas.modelo.Biblioteca;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private List<Biblioteca> datos;
    private RecyclerView reciclado;

    private ClickRecycler escucha = new ClickRecycler() {
        @Override
        public void onClick(int posicion) {
            misBiblio().get(posicion).getCorreo();
            Biblioteca b = new Biblioteca(misBiblio().get(posicion).getNombre(),
                    misBiblio().get(posicion).getUrll(), misBiblio().get(posicion).getTelefono(),
                    misBiblio().get(posicion).getCorreo());
            Intent intent = new Intent(ScrollingActivity.this, DatosBiblioteca.class);
            intent.putExtra("xxx", b);

            startActivity(intent);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicializar();
        //el "recicla" es el id del RecyclerView que hemos puesto en el layout de Recursos llamado content_scrolling
        reciclado = (RecyclerView) findViewById(R.id.recicla);
        reciclado.setLayoutManager(new LinearLayoutManager(this));//con el this le decimos que trabaje sobre este contexto
        reciclado.setAdapter(new Adaptador(datos, escucha));
    }

    private void inicializar() {
        Resources res = ScrollingActivity.this.getResources();
        String[] nombre = res.getStringArray(R.array.bibliotecas);//así accedemos a los valores que contriene la variable string de la carpeta Recursos
        String[] direccion = res.getStringArray(R.array.direcciones);

        datos = new ArrayList<>();
        datos.add(new Biblioteca(nombre[0], direccion[0], R.drawable.bcastillayleon));
        datos.add(new Biblioteca(nombre[1], direccion[1], R.drawable.bavila));
        datos.add(new Biblioteca(nombre[2], direccion[2], R.drawable.bburgos));
        datos.add(new Biblioteca(nombre[3], direccion[3], R.drawable.bleon));
        datos.add(new Biblioteca(nombre[4], direccion[4], R.drawable.bpalecia));
        datos.add(new Biblioteca(nombre[5], direccion[5], R.drawable.bsalamanca));
        datos.add(new Biblioteca(nombre[6], direccion[6], R.drawable.bsegovia));
        datos.add(new Biblioteca(nombre[7], direccion[7], R.drawable.bsoria));
        datos.add(new Biblioteca(nombre[8], direccion[8], R.drawable.bvalladolid));
        datos.add(new Biblioteca(nombre[9], direccion[9], R.drawable.bzamora));
    }

    private ArrayList<Biblioteca> misBiblio () {
        Resources res = ScrollingActivity.this.getResources();
        String[] nombre = res.getStringArray(R.array.bibliotecas);
        String[] web = res.getStringArray(R.array.webs);
        int[]tlfs=res.getIntArray(R.array.telefonos);
        String[] email = res.getStringArray(R.array.correos);
        ArrayList<Biblioteca> misBibliotecas = new ArrayList<Biblioteca>();

        misBibliotecas.add(new Biblioteca(nombre[0], web[0], tlfs[0], email[0]));
        misBibliotecas.add(new Biblioteca(nombre[1], web[1], tlfs[1], email[1]));
        misBibliotecas.add(new Biblioteca(nombre[2], web[2], tlfs[2], email[2]));
        misBibliotecas.add(new Biblioteca(nombre[3], web[3], tlfs[3], email[3]));
        misBibliotecas.add(new Biblioteca(nombre[4], web[4], tlfs[4], email[4]));
        misBibliotecas.add(new Biblioteca(nombre[5], web[5], tlfs[5], email[5]));
        misBibliotecas.add(new Biblioteca(nombre[6], web[6], tlfs[6], email[6]));
        misBibliotecas.add(new Biblioteca(nombre[7], web[7], tlfs[7], email[7]));
        misBibliotecas.add(new Biblioteca(nombre[8], web[8], tlfs[8], email[8]));
        misBibliotecas.add(new Biblioteca(nombre[9], web[9], tlfs[9], email[9]));

        return misBibliotecas;
    }

}
