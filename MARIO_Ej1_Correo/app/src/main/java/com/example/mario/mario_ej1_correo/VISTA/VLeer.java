package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mario.mario_ej1_correo.CONTROLADOR.Adaptador;
import com.example.mario.mario_ej1_correo.CONTROLADOR.ClickRecycler;
import com.example.mario.mario_ej1_correo.MODELO.Correo;
import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.R;

import java.util.ArrayList;

public class VLeer extends AppCompatActivity {

    private RecyclerView reciclado;
    private ArrayList<Usuario> datos;
    ArrayList<Usuario> miUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vleer);
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
        getMenuInflater().inflate(R.menu.menu_vleer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDeconexion:
                Intent intent = null;
                intent = new Intent(VLeer.this, MenuUsuario.class);
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
            ArrayList<Correo> c = (datos.get(posicion).getMiCorreo());
            Intent intent = new Intent(VLeer.this, ActivityCorreos.class);
            intent.putExtra("c", c);
            intent.putExtra("c2", posicion);

            startActivity(intent);
        }
    };

    private void inicializar() {
        Resources res = VLeer.this.getResources();
        ArrayList<Correo> miCorreo = new ArrayList<>();
        datos = new ArrayList<>();
        for (int a = 0; a < Inicio.miUsuario.get(Login.pos).getMiCorreo().size(); a++) {
            int codRemitente = Integer.parseInt(Inicio.miUsuario.get(Login.pos).getMiCorreo().get(a).getCodigo());
            datos.add(new Usuario(Inicio.miUsuario.get(codRemitente).getFoto(), Inicio.miUsuario.get(Login.pos).getMiCorreo()));
        }
    }
}
