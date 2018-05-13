package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerNotas;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerUsuarios;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    DataBaseManagerAlumnos managerAlumnos;
    DataBaseManagerNotas managerNotas;
    DataBaseManagerUsuarios managerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imgInicial);

        /*managerAlumnos = new DataBaseManagerAlumnos(this);
        managerNotas = new DataBaseManagerNotas(this);
        managerUsuarios = new DataBaseManagerUsuarios(this);

        managerAlumnos.insertarTodosEnBD();
        managerNotas.insertarTodosEnBD();
        managerUsuarios.insertarTodosEnBD();*/

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.burgos);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(this.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);

        imagen.setImageDrawable(roundedBitmapDrawable);

    /*    imagen = (ImageView) findViewById(R.id.imgInicial);
        imagen.setImageResource(R.drawable.inicial2);*/
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
            Intent intent = new Intent(MainActivity.this, login.class);
            startActivity(intent);
        }
    }
}
