package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerInmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerZonas;
import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.OperacionesDatos;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    private DataBaseManagerZonas managerZona;
    private DataBaseManagerInmueble managerInmueble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        managerZona = new DataBaseManagerZonas(this);
        managerInmueble = new DataBaseManagerInmueble(this);

        OperacionesDatos.agregarZonasAlArray();

        managerZona.insertarTodosEnBD();
        managerInmueble.insertarTodosEnBD();
        imagen = (ImageView) findViewById(R.id.imgInicial);
        imagen.setImageResource(R.drawable.logoimnobilias);
        new MiAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    class MiAsyncTask extends AsyncTask<Void, Void, Void> // <EL Nº de columna con la que queremos trabajar, y la salida que vamos a mostrar>
            //---cada class "MiAsyncTask" es un nuevo hilo

    {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            imagen.setVisibility(View.GONE);
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
        }
    }
};
