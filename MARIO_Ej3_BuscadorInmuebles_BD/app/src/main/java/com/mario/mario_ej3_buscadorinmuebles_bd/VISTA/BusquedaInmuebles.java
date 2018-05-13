package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerInmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

import java.util.ArrayList;
import java.util.List;

public class BusquedaInmuebles extends AppCompatActivity {

    Spinner miSpinner;
    RadioGroup radioGrupo;
    RadioButton piso, estudio, apartamento;
    EditText precioMayor, precioMenor, numHabita, numZona;
    TextView precio1, precio2, habitaciones, zona;
    Button buscar;

    int posicionSpinner = -1;
    private DataBaseManagerInmueble managerInmueble;
    public static boolean busqueda = false;
    public static List<Inmueble> listInmueble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_inmuebles);

        managerInmueble= new DataBaseManagerInmueble(this);
        miSpinner = (Spinner) findViewById(R.id.spCriterios);
        radioGrupo = (RadioGroup) findViewById(R.id.radioGroup);
        precioMayor = (EditText) findViewById(R.id.txtPrecioMayor);
        precioMenor = (EditText) findViewById(R.id.txtPrecioMenor);
        numHabita = (EditText) findViewById(R.id.txtNumHabita);
        numZona = (EditText) findViewById(R.id.txtNumZona);

        precio1 = (TextView) findViewById(R.id.textViewPrecio1);
        precio2 = (TextView) findViewById(R.id.textViewPrecio2);
        habitaciones = (TextView) findViewById(R.id.textViewHabitaciones);
        zona = (TextView) findViewById(R.id.textViewZona);

        piso = (RadioButton) findViewById(R.id.rbtPiso);
        estudio = (RadioButton) findViewById(R.id.rbtEstudio);
        apartamento = (RadioButton) findViewById(R.id.rbtApartamento);

        buscar = (Button) findViewById(R.id.btnBuscar);

        cargarSpinner();
        miSpinner.setOnItemSelectedListener(escucha);
        buscar.setOnClickListener(escucha2);
    }

    public int conseguirPosicionRadioButon() {
        int pos = -1;
        if (piso.isChecked()) {
            pos = 0;
        } else if (estudio.isChecked()) {
            pos = 1;
        } else if (apartamento.isChecked()) {
            pos = 2;
        }else{
            Toast.makeText(BusquedaInmuebles.this, "No has seleccionado ningún criterio", Toast.LENGTH_SHORT).show();
        }
        return pos;
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

                listInmueble = new ArrayList<>();
                if (posicionSpinner == 0) {
                    if(precioMayor.getText().toString().compareTo("")==0 || precioMenor.getText().toString().compareTo("")==0
                            || Integer.parseInt(precioMayor.getText().toString())<0 || Integer.parseInt(precioMenor.getText().toString())<0){
                        Toast.makeText(BusquedaInmuebles.this, "Rellena los campos correctamente", Toast.LENGTH_SHORT).show();
                    }else {
                        listInmueble = managerInmueble.buscarInmueblesPorPrecio(precioMayor.getText().toString(), precioMenor.getText().toString());
                    }
                } else if (posicionSpinner == 1) {
                    int posRadioButon = conseguirPosicionRadioButon();
                    listInmueble = managerInmueble.buscarInmueblesPorTipo(posRadioButon);
                } else if (posicionSpinner == 2) {
                    if(numHabita.getText().toString().compareTo("")==0 || Integer.parseInt(numHabita.getText().toString())<0){
                        Toast.makeText(BusquedaInmuebles.this, "Rellena los campos correctamente", Toast.LENGTH_SHORT).show();
                    }else {
                        listInmueble = managerInmueble.buscarInmueblesPorHabitaciones(numHabita.getText().toString());
                    }
                } else {
                    if(numZona.getText().toString().compareTo("")==0 || Integer.parseInt(numZona.getText().toString())<0){
                        Toast.makeText(BusquedaInmuebles.this, "Rellena los campos correctamente", Toast.LENGTH_SHORT).show();
                    }else {
                        listInmueble = managerInmueble.buscarInmueblesPorZona(numZona.getText().toString());
                    }
                }
                if (listInmueble.size() == 0) {
                    Toast.makeText(BusquedaInmuebles.this, "No existen inmuebles con esas condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    managerInmueble.actualizarNumConsultas();
                    Intent intent = new Intent(BusquedaInmuebles.this, TodosInmuebles.class);
                    startActivity(intent);
                }
            }
    };

    public void cargarSpinner() {
        ArrayList<String> criterios = new ArrayList<>();
        criterios.add("Rango de Precio");
        criterios.add("Tipo de Propiedad");
        criterios.add("Numero de Habitaciones");
        criterios.add("Zona");

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, criterios);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        miSpinner.setAdapter(adaptador);
    }

    public void habilitarTipos() {
        int pos = miSpinner.getSelectedItemPosition();

        if (pos == 0) {
            radioGrupo.setVisibility(View.GONE);

            precioMayor.setVisibility(View.VISIBLE);
            precioMenor.setVisibility(View.VISIBLE);
            numHabita.setVisibility(View.INVISIBLE);
            numZona.setVisibility(View.INVISIBLE);

            precio1.setVisibility(View.VISIBLE);
            precio2.setVisibility(View.VISIBLE);
            habitaciones.setVisibility(View.INVISIBLE);
            zona.setVisibility(View.INVISIBLE);
        } else if (pos == 1) {
            radioGrupo.setVisibility(View.VISIBLE);

            precioMayor.setVisibility(View.INVISIBLE);
            precioMenor.setVisibility(View.INVISIBLE);
            numHabita.setVisibility(View.INVISIBLE);
            numZona.setVisibility(View.INVISIBLE);

            precio1.setVisibility(View.INVISIBLE);
            precio2.setVisibility(View.INVISIBLE);
            habitaciones.setVisibility(View.INVISIBLE);
            zona.setVisibility(View.INVISIBLE);

        } else if (pos == 2) {
            radioGrupo.setVisibility(View.GONE);

            precioMayor.setVisibility(View.INVISIBLE);
            precioMenor.setVisibility(View.INVISIBLE);
            numHabita.setVisibility(View.VISIBLE);
            numZona.setVisibility(View.INVISIBLE);

            precio1.setVisibility(View.INVISIBLE);
            precio2.setVisibility(View.INVISIBLE);
            habitaciones.setVisibility(View.VISIBLE);
            zona.setVisibility(View.INVISIBLE);
        } else {
            radioGrupo.setVisibility(View.GONE);

            precioMayor.setVisibility(View.INVISIBLE);
            precioMenor.setVisibility(View.INVISIBLE);
            numHabita.setVisibility(View.INVISIBLE);
            numZona.setVisibility(View.VISIBLE);

            precio1.setVisibility(View.INVISIBLE);
            precio2.setVisibility(View.INVISIBLE);
            habitaciones.setVisibility(View.INVISIBLE);
            zona.setVisibility(View.VISIBLE);
        }
    }

    public void onBackPressed() {//HAGO ESTO PARA QUE AL VOLVE ATRAS, ME REFRESQUE EL ADAPTER
        startActivity(new Intent(this,Menu.class));
        finish();
    }

}
