package com.acme.mario_ej2_recetario_bd.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerRecetas;
import com.acme.mario_ej2_recetario_bd.controlador.Adaptador;
import com.acme.mario_ej2_recetario_bd.controlador.ClickRecycler;
import com.acme.mario_ej2_recetario_bd.modelo.Receta;

import java.util.ArrayList;
import java.util.List;

public class Consultas extends AppCompatActivity {

    Spinner miSpinner;
    Button buscar;
    int posicionSpinner = 0;
    List<Receta> listReceta;
    EditText porIngrediente, porId, porTiempo, porCategoria;
    TextView eCategoria, eTiempo, eId, eIngrediente;
    private DataBaseManagerRecetas managerReceta;
    private RecyclerView reciclado;
    private RecyclerView.LayoutManager linearManager;
    private Adaptador adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultas);

        managerReceta = new DataBaseManagerRecetas(this);
        miSpinner = (Spinner) findViewById(R.id.spCriterios);
        buscar = (Button) findViewById(R.id.btnBuscar);

        porIngrediente = (EditText) findViewById(R.id.txtIngrediente);
        porId = (EditText) findViewById(R.id.txtId);
        porTiempo = (EditText) findViewById(R.id.txtTiempo);
        porCategoria = (EditText) findViewById(R.id.txtCategoria);

        eIngrediente = (TextView) findViewById(R.id.etiqIngrediente);
        eId = (TextView) findViewById(R.id.etiqId);
        eTiempo = (TextView) findViewById(R.id.etiqTiempo);
        eCategoria = (TextView) findViewById(R.id.etiqCategoria);

        cargarSpinner();
        miSpinner.setOnItemSelectedListener(escucha);
        buscar.setOnClickListener(escucha2);
    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            habilitarTipos();
            posicionSpinner = miSpinner.getSelectedItemPosition();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listReceta = new ArrayList<>();
            if (posicionSpinner == 0) {
                listReceta = managerReceta.buscarRecetaPorCualquierCriterio(posicionSpinner, 0, null, 0, 0);
            } else if (posicionSpinner == 1) {
                if (porCategoria.getText().toString().compareTo("") == 0) {
                    Toast.makeText(Consultas.this, "Debes introducir una categoría", Toast.LENGTH_SHORT).show();
                } else {
                    listReceta = managerReceta.buscarRecetaPorCualquierCriterio(posicionSpinner, Integer.parseInt(porCategoria.getText().toString()), null, 0, 0);
                }
            } else if (posicionSpinner == 2) {
                if (porId.getText().toString().compareTo("") == 0) {
                    Toast.makeText(Consultas.this, "Debes introducir un número valido", Toast.LENGTH_SHORT).show();
                } else {
                    listReceta = managerReceta.buscarRecetaPorCualquierCriterio(posicionSpinner, 0, null, 0, Integer.parseInt(porId.getText().toString()));
                }
            } else if (posicionSpinner == 3) {
                if (porTiempo.getText().toString().compareTo("") == 0) {
                    Toast.makeText(Consultas.this, "Debes introducir un tiempo valido", Toast.LENGTH_SHORT).show();
                } else {
                    listReceta = managerReceta.buscarRecetaPorCualquierCriterio(posicionSpinner, 0, null, Integer.parseInt(porTiempo.getText().toString()), 0);
                }
            } else {
                if (porIngrediente.getText().toString().compareTo("") == 0) {
                    Toast.makeText(Consultas.this, "Debes introducir un ingrediente", Toast.LENGTH_SHORT).show();
                } else {
                    listReceta = managerReceta.buscarRecetaPorCualquierCriterio(posicionSpinner, 0, porIngrediente.getText().toString(), 0, 0);
                }
            }
            if (listReceta.size() == 0) {
                Toast.makeText(Consultas.this, "No existen recetas con esas condiciones", Toast.LENGTH_SHORT).show();
            } else {
                inicializarRecicler();
            }
        }
    };

    public void cargarSpinner() {
        ArrayList<String> criterios = new ArrayList<>();
        criterios.add("Todas");
        criterios.add("Por Categoria");
        criterios.add("Por id");
        criterios.add("Por tiempo");
        criterios.add("Por Ingrediente");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, criterios);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        miSpinner.setAdapter(adaptador);
    }

    public void habilitarTipos() {
        int pos = miSpinner.getSelectedItemPosition();


        if (pos == 0) {
            porCategoria.setVisibility(View.GONE);
            porIngrediente.setVisibility(View.GONE);
            porId.setVisibility(View.GONE);
            porTiempo.setVisibility(View.GONE);

            eCategoria.setVisibility(View.GONE);
            eIngrediente.setVisibility(View.GONE);
            eId.setVisibility(View.GONE);
            eTiempo.setVisibility(View.GONE);
        } else if (pos == 1) {
            porCategoria.setVisibility(View.VISIBLE);
            porIngrediente.setVisibility(View.GONE);
            porId.setVisibility(View.GONE);
            porTiempo.setVisibility(View.GONE);

            eCategoria.setVisibility(View.VISIBLE);
            eIngrediente.setVisibility(View.GONE);
            eId.setVisibility(View.GONE);
            eTiempo.setVisibility(View.GONE);

        } else if (pos == 2) {
            porCategoria.setVisibility(View.GONE);
            porIngrediente.setVisibility(View.GONE);
            porId.setVisibility(View.VISIBLE);
            porTiempo.setVisibility(View.GONE);

            eCategoria.setVisibility(View.GONE);
            eIngrediente.setVisibility(View.GONE);
            eId.setVisibility(View.VISIBLE);
            eTiempo.setVisibility(View.GONE);

        } else if (pos == 3) {
            porCategoria.setVisibility(View.GONE);
            porIngrediente.setVisibility(View.GONE);
            porId.setVisibility(View.GONE);
            porTiempo.setVisibility(View.VISIBLE);

            eCategoria.setVisibility(View.GONE);
            eIngrediente.setVisibility(View.GONE);
            eId.setVisibility(View.GONE);
            eTiempo.setVisibility(View.VISIBLE);
        } else {
            porCategoria.setVisibility(View.GONE);
            porIngrediente.setVisibility(View.VISIBLE);
            porId.setVisibility(View.GONE);
            porTiempo.setVisibility(View.GONE);

            eCategoria.setVisibility(View.GONE);
            eIngrediente.setVisibility(View.VISIBLE);
            eId.setVisibility(View.GONE);
            eTiempo.setVisibility(View.GONE);
        }
    }


    public void inicializarRecicler() {

        // Obtener el Recycler
        reciclado = (RecyclerView) findViewById(R.id.reciclerRecetas);
        reciclado.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        linearManager = new LinearLayoutManager(this);
        reciclado.setLayoutManager(linearManager);

        adapter = new Adaptador(listReceta, escuchaRecicler);
        reciclado.setAdapter(adapter);
        reciclado.setItemAnimator(new DefaultItemAnimator());

    }

    private ClickRecycler escuchaRecicler = new ClickRecycler() {
        @Override
        public void onClick(int posicion) {
            Intent intent = new Intent(Consultas.this, DatosReceta.class);
            intent.putExtra("receta", listReceta.get(posicion).getId());//LE PASAMOS EL ID DE LA POSICION SELECCIONADA

            startActivity(intent);
        }
    };


}
