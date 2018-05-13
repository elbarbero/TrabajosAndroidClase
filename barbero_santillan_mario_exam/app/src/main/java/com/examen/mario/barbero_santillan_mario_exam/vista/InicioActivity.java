package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.examen.mario.barbero_santillan_mario_exam.R;
import com.examen.mario.barbero_santillan_mario_exam.modelo.Usuario;

import java.util.ArrayList;

public class InicioActivity extends AppCompatActivity {

    Button continuar;
    ImageView imagen;
    public static ArrayList<Usuario> miUsuario=new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        continuar=(Button)findViewById(R.id.btnContinuar);
        imagen=(ImageView)findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.inicio);

        continuar.setOnClickListener(login);

        cargarUsuarios();

    }

    public View.OnClickListener login=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(InicioActivity.this, Login.class);
            startActivity(intent);
        }
    };

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                Intent intent=new Intent(InicioActivity.this,Autor.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void cargarUsuarios(){

        Resources res = this.getResources();
        String [] nombre = res.getStringArray(R.array.usuarios);//así accedemos a los valores que contriene la variable string de la carpeta Recursos
        String [] contra = res.getStringArray(R.array.contraseñas);


        for(int i=0;i<nombre.length;i++) {
            miUsuario.add(new Usuario(nombre[i], contra[i]));
        }
    }


}
