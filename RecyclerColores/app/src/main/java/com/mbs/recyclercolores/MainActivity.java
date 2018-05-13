package com.mbs.recyclercolores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Color> colores;
    RecyclerView recicla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarDatos();
        recicla=(RecyclerView)findViewById(R.id.reciclado);
        recicla.setLayoutManager(new LinearLayoutManager(this));
        //recicla.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));//Te lo pone en horizontal
        //recicla.setLayoutManager(new GridLayoutManager(this,2));//Me pone en modo rejilla donde el numero 2 es el numero de columnas

        //AdaptadorElemento adaptador=new AdaptadorElemento(colores);
        AdaptadorElemento adaptador=new AdaptadorElemento(colores,escucha);


        recicla.setAdapter(adaptador);
    }

    private RecyclerViewOnItemClickListener escucha= new RecyclerViewOnItemClickListener() {
        @Override
        public void onClick(View v, int posicion) {
            switch(v.getId()) {
                case R.id.button:
                    Toast.makeText(MainActivity.this, "Has pulsado en el boton", Toast.LENGTH_SHORT).show();
                    break;
                default:
                Toast.makeText(MainActivity.this, colores.get(posicion).getColor(), Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void inicializarDatos(){
        colores=new ArrayList<>();
        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

        colores.add(new Color(getResources().getString(R.string.amarillo),"yellow"));
        colores.add(new Color(getResources().getString(R.string.verde),"green"));
        colores.add(new Color(getResources().getString(R.string.negro),"black"));
        colores.add(new Color(getResources().getString(R.string.rojo),"red"));

    }

}
