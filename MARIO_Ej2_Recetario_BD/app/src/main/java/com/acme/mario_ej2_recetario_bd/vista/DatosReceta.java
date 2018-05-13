package com.acme.mario_ej2_recetario_bd.vista;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerCategorias;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerRecetas;
import com.acme.mario_ej2_recetario_bd.modelo.Categoria;
import com.acme.mario_ej2_recetario_bd.modelo.Receta;

import java.util.List;

public class DatosReceta extends AppCompatActivity {

    ImageView imagen;
    TextView idReceta, idCategoria, nombre, ingrediente, tiempo;
    List<Receta> listReceta;
    List<Categoria> listCategoria;
    private DataBaseManagerRecetas managerReceta;
    private DataBaseManagerCategorias managerCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_receta);
        managerReceta = new DataBaseManagerRecetas(this);
        managerCategoria = new DataBaseManagerCategorias(this);

        imagen = (ImageView) findViewById(R.id.foto);
        idReceta = (TextView) findViewById(R.id.txtId);
        idCategoria = (TextView) findViewById(R.id.txtIdCategoria);
        nombre = (TextView) findViewById(R.id.txtNombre);
        ingrediente = (TextView) findViewById(R.id.txtIngredientes);
        tiempo = (TextView) findViewById(R.id.txtTiempo);
        String ingredientes = "";

        listReceta = managerReceta.buscarRecetaPorID(getIntent().getExtras().getInt("receta"));
        listCategoria = managerCategoria.buscarCategoriaPorID(listReceta.get(0).getCategoria().getId());

        idReceta.setText(String.valueOf(listReceta.get(0).getId()));
        idCategoria.setText(listCategoria.get(0).getDenominacion());
        nombre.setText(listReceta.get(0).getNombre());
        for (int i = 0; i < listReceta.get(0).getMisIngredientes().size(); i++) {
            ingredientes = ingredientes + ", " + listReceta.get(0).getMisIngredientes().get(i).getNombre();
        }
        ingrediente.setText(ingredientes);
        tiempo.setText(String.valueOf(listReceta.get(0).getTiempo()) + " " + "minutos");

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), listReceta.get(0).getFoto());
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(this.getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);

        imagen.setImageDrawable(roundedBitmapDrawable);
    }
}
