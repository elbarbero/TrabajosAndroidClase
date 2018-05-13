package com.acme.mario_ej1_abogados_bd.vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerCasos;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerGestiones;

public class MainActivity extends AppCompatActivity {

    DataBaseManagerCasos managerCasos;
    DataBaseManagerGestiones managerGestiones;
    DataBaseManagerAbogados managerAbogados;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imgInicial);
       /* managerCasos = new DataBaseManagerCasos(this);
        managerGestiones = new DataBaseManagerGestiones(this);
        managerAbogados = new DataBaseManagerAbogados(this);

        managerAbogados.insertarTodosEnBD();
        managerCasos.insertarTodosEnBD();
        managerGestiones.insertarTodosEnBD();*/

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.abogados);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(this.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imagen.setImageDrawable(roundedBitmapDrawable);

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
            Intent intent = new Intent(MainActivity.this, MenuInicio.class);
            startActivity(intent);
        }
    }
}
