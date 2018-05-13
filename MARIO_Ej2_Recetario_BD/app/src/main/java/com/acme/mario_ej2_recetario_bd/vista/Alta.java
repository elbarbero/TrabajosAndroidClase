package com.acme.mario_ej2_recetario_bd.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerCategorias;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerIngredientes;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerRecetas;
import com.acme.mario_ej2_recetario_bd.modelo.Ingredientes;

import java.util.ArrayList;

public class Alta extends AppCompatActivity {

    ImageView imagen;
    EditText idCategoria, nombre, ingrediente, tiempo;
    Spinner spinnerImagenes;
    Button grabar;
    DataBaseManagerRecetas managerRecetas;
    DataBaseManagerIngredientes managerIngredientes;
    DataBaseManagerCategorias managerCategorias;
    ArrayList<Integer> imagenes;
    int posicionSpinner = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta);

        spinnerImagenes = (Spinner) findViewById(R.id.spnImagenes);
        grabar = (Button) findViewById(R.id.btnGrabar);

        idCategoria = (EditText) findViewById(R.id.txtIdCategoria);
        nombre = (EditText) findViewById(R.id.txtNombre);
        ingrediente = (EditText) findViewById(R.id.txtIngredientes);
        tiempo = (EditText) findViewById(R.id.txtTiempo);
        imagen = (ImageView) findViewById(R.id.imgImagen);

        cargarSpinner();
        spinnerImagenes.setOnItemSelectedListener(escucha);
        grabar.setOnClickListener(escucha2);

    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerImagenes.getSelectedItemPosition();
            imagen.setImageResource(imagenes.get(posicionSpinner).intValue());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabarReceta();
        }
    };

    public void cargarSpinner() {
        imagenes = new ArrayList<>();
        int[] img = {R.drawable.uno, R.drawable.dos, R.drawable.tres, R.drawable.arrozconleche, R.drawable.pizza, R.drawable.tartachocolate};
        for (int i = 0; i < img.length - 1; i++) {
            imagenes.add(img[i]);
        }

        ArrayAdapter<Integer> adaptador = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, imagenes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerImagenes.setAdapter(adaptador);
    }

    public void borrarCampos() {
        idCategoria.setText("");
        nombre.setText("");
        ingrediente.setText("");
        tiempo.setText("");
    }

    public void grabarReceta() {
        ArrayList<Ingredientes> misIngredientes = new ArrayList<>();
        managerRecetas = new DataBaseManagerRecetas(this);
        managerCategorias = new DataBaseManagerCategorias(this);
        managerIngredientes = new DataBaseManagerIngredientes(this);

        if (managerCategorias.compruebaRegistro(idCategoria.getText().toString())) {
            if (managerIngredientes.compruebaRegistroPorNombre(ingrediente.getText().toString())) {
                if (tiempo.getText().toString().compareTo("") == 0 || Integer.parseInt(tiempo.getText().toString()) < 0) {
                    Toast.makeText(Alta.this, "Introduce un tiempo correcto", Toast.LENGTH_SHORT).show();
                } else {
                    misIngredientes.add(new Ingredientes(ingrediente.getText().toString()));
                    managerRecetas.insertarUnaRecetaEnBD(Integer.parseInt(idCategoria.getText().toString()),
                            nombre.getText().toString(), misIngredientes, imagenes.get(posicionSpinner), Integer.parseInt(tiempo.getText().toString()));
                    borrarCampos();
                    Toast.makeText(Alta.this, "Receta insertada correctamente", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(Alta.this, "Ese ingrediente no existe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(Alta.this, "Esa categoría no existe", Toast.LENGTH_SHORT).show();
        }
    }
}
