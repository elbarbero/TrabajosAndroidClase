package com.mbs.recyclercard.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.mbs.recyclercard.R;
import com.mbs.recyclercard.controlador.Adaptador;
import com.mbs.recyclercard.controlador.ClickRecycler;
import com.mbs.recyclercard.modelo.Anime;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    private List<Anime> datos;
    private RecyclerView reciclado;

    private ClickRecycler escucha= new ClickRecycler() {
        @Override
        public void onClick(int posicion) {
Intent envio=new Intent();
            envio.setAction(Intent.ACTION_SEND);
            envio.putExtra(Intent.EXTRA_TEXT,datos.get(posicion).getNombre());
            envio.setType("text/plain");
            startActivity(envio);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        inicializar();
        //el "recicla" es el id del RecyclerView que hemos puesto en el layout de Recursos llamado content_scrolling
        reciclado=(RecyclerView)findViewById(R.id.recicla);
        reciclado.setLayoutManager(new LinearLayoutManager(this));//con el this le decimos que trabaje sobre este contexto
        reciclado.setAdapter(new Adaptador(datos,escucha));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //int id = item.getItemId();

        switch(item.getItemId()){
            case R.id.autorcillo://autorcillo es el id del item del menu_scrolling.xml
                Intent i=new Intent(ScrollingActivity.this,Autor.class);//ABRE EL ACTIVITY DE AUTOR
                startActivity(i);
                break;
            default:
        }

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    private void inicializar(){
        /*en el mismo orden en el que están colocados en el constructor de la clase "anime",
        es decir, primero nombre, numero de visitas y por ultimo la imagen*/
        datos=new ArrayList<>();
        datos.add(new Anime("Angel beats",230,R.drawable.angel));
        datos.add(new Anime("Death fate",590,R.drawable.death));
        datos.add(new Anime("Fate Stay Night",98,R.drawable.fate));
        datos.add(new Anime("Welcome to the NHK",230,R.drawable.nhk));
        datos.add(new Anime("Suzumiya Harubi",1050,R.drawable.suzumiya));
    }

}
