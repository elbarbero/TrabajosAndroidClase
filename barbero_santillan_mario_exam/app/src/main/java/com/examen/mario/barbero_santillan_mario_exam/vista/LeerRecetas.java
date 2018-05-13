package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.examen.mario.barbero_santillan_mario_exam.R;
import com.examen.mario.barbero_santillan_mario_exam.controlador.Adaptador;
import com.examen.mario.barbero_santillan_mario_exam.controlador.ClickRecycler;
import com.examen.mario.barbero_santillan_mario_exam.modelo.Receta;
import com.examen.mario.barbero_santillan_mario_exam.modelo.Usuario;

import java.util.ArrayList;

public class LeerRecetas extends AppCompatActivity {

    private RecyclerView reciclado;
     private ArrayList<Usuario> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_recetas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inicializar();
        reciclado = (RecyclerView) findViewById(R.id.recicler);
        reciclado.setLayoutManager(new LinearLayoutManager(this));
        reciclado.setAdapter(new Adaptador(datos, escucha));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_leer_recetas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDeconexion://TODO no me hace bien el activity for result
                Intent intent = null;
                intent = new Intent(LeerRecetas.this, MenuUsuario.class);
                setResult(RESULT_OK);//ESTO ME ENVIA UN -1
                finish();
                break;
            case R.id.mMenu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ClickRecycler escucha = new ClickRecycler() {
        @Override
        public void onClick(int posicion) {
            ArrayList<Receta> r= (datos.get(posicion).getMireceta());
            Intent intent = new Intent(LeerRecetas.this, DatosReceta.class);
            intent.putExtra("r", r);
            intent.putExtra("r2", posicion);

            startActivity(intent);
        }
    };

    private void inicializar() {
        Resources res = LeerRecetas.this.getResources();
        ArrayList<Receta> miReceta = new ArrayList<>();
        String[] nombre = res.getStringArray(R.array.usuarios);

        datos = new ArrayList<>();

        for (int a=0;a<InicioActivity.miUsuario.get(Login.pos).getMireceta().size();a++) {
            datos.add(new Usuario(InicioActivity.miUsuario.get(Login.pos).getMireceta()));
        }

    }
}
