package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.examen.mario.barbero_santillan_mario_exam.R;

public class AltaRecetas extends AppCompatActivity {

    EditText nombre;
    EditText tiempo;
    EditText ingrediente;
    EditText proceso;
    EditText raciones;
    ImageView foto;
    Button grabar;
    int[] imagenes = {R.drawable.uno, R.drawable.dos, R.drawable.tres};
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_recetas);

        nombre = (EditText) findViewById(R.id.txtNombreRec);
        tiempo = (EditText) findViewById(R.id.txtTiempo);
        ingrediente = (EditText) findViewById(R.id.txtIngrediente);
        proceso = (EditText) findViewById(R.id.txtProcesoElab);
        raciones = (EditText) findViewById(R.id.txtRaciones);
        foto = (ImageView) findViewById(R.id.imgFoto);

        grabar = (Button) findViewById(R.id.btnGrabar);
        grabar.setOnClickListener(escucharGrabar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nrecetas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDeconexion:
                Intent intent = null;
                intent = new Intent(AltaRecetas.this, MenuUsuario.class);
                setResult(RESULT_OK);//ESTO ME ENVIA UN -1
                finish();
                break;
            case R.id.mMenu:
                finish();
                break;
            case R.id.mImagen:
                lanzarSitio();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener escucharGrabar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (comprobarImagen()) {
                buscarUsuario();
                nombre.setText("");
                tiempo.setText("");
                ingrediente.setText("");
                proceso.setText("");
                raciones.setText("");
            }
        }
    };

    public void buscarUsuario() {
        InicioActivity.miUsuario.get(Login.pos).AnadirReceta(nombre.getText().toString(), tiempo.getText().toString(), raciones.getText().toString(),
                ingrediente.getText().toString(), proceso.getText().toString(), imagenes[id]);
    }

    public boolean comprobarImagen() {
        if (id < 0 || id > 2) {
            Resources res = this.getResources();
            String mens = res.getString(R.string.mensImagen);
            Toast.makeText(AltaRecetas.this, mens, Toast.LENGTH_SHORT).show();
            lanzarSitio();
            return false;
        } else {
            return true;
        }
    }

    private void lanzarSitio() {
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this).setTitle("Elegir Imagen")
                .setMessage("Introduce el id de la imagen (0,1 o 2")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        id = Integer.parseInt(entrada.getText().toString());
                        if (id >= 0 && id <= 2) {
                            foto.setImageResource(imagenes[id]);
                        }
                    }

                }).setNegativeButton("Cancelar", null).show();
    }
}
