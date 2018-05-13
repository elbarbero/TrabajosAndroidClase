package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerInmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.InmuebleAdapter;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

import java.util.List;

public class Menu extends AppCompatActivity /*implements InmuebleAdapter.OnItemClickListener*/ {

    Button verTodos, consultar;

    private RecyclerView reciclado;
    private DataBaseManagerInmueble managerInmueble;

    private List<Inmueble> listaItemsInmuebles;
    private RecyclerView.LayoutManager linearManager;
    private InmuebleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        managerInmueble = new DataBaseManagerInmueble(this);

        listaItemsInmuebles = managerInmueble.consultarInmueblesMasVistos(5);

        inicializarRecicler();
        verTodos = findViewById(R.id.btnBuscarTodos);
        consultar = findViewById(R.id.btnConsultar);

        verTodos.setOnClickListener(escucha);
        consultar.setOnClickListener(escucha2);
    }

    View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BusquedaInmuebles.busqueda = false;
            Intent intent = new Intent(Menu.this, TodosInmuebles.class);
            startActivity(intent);
        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BusquedaInmuebles.busqueda = false;
            Intent intent = new Intent(Menu.this, BusquedaInmuebles.class);
            startActivity(intent);
        }
    };

    public void inicializarRecicler() {
        // Obtener el Recycler
        reciclado = findViewById(R.id.reciclerInmuebles);
        reciclado.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        linearManager = new LinearLayoutManager(this);
        reciclado.setLayoutManager(linearManager);

        adapter = new InmuebleAdapter(listaItemsInmuebles, this,true);
        reciclado.setAdapter(adapter);
        reciclado.setItemAnimator(new DefaultItemAnimator());
    }

}

