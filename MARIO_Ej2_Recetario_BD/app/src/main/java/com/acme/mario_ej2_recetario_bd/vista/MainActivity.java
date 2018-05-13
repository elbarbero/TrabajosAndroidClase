package com.acme.mario_ej2_recetario_bd.vista;

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

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerCategorias;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerIngredientes;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerRecetas;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerUsuarios;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;

    DataBaseManagerCategorias managerCategorias;
    DataBaseManagerIngredientes managerIngredientes;
    DataBaseManagerRecetas managerRecetas;
    DataBaseManagerUsuarios managerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagen = (ImageView) findViewById(R.id.imgInicial);

        //INSERTAMOS LOS DATOS EN LA BASE DE DATOS
        managerCategorias = new DataBaseManagerCategorias(this);
        managerIngredientes = new DataBaseManagerIngredientes(this);
        managerRecetas = new DataBaseManagerRecetas(this);
        managerUsuarios = new DataBaseManagerUsuarios(this);
        managerCategorias.insertarTodosEnBD();
        managerIngredientes.insertarTodosEnBD();
        managerRecetas.insertarTodosEnBD();
        managerUsuarios.insertarTodosEnBD();

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.inicial2);
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
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }
}
