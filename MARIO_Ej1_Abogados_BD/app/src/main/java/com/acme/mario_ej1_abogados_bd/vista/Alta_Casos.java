package com.acme.mario_ej1_abogados_bd.vista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerCasos;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;

import java.util.ArrayList;
import java.util.List;

public class Alta_Casos extends AppCompatActivity {

    EditText denominacion, caracteristicas, fecha;
    Spinner spinnerAbogados;
    Button grabar, botonPicker;
    DatePicker pickerfechaApertura;
    int posicionSpinner = 0;
    boolean pickerVisible = false;
    DataBaseManagerAbogados managerAbogados;
    DataBaseManagerCasos managerCasos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__casos);
        managerAbogados = new DataBaseManagerAbogados(this);
        managerCasos=new DataBaseManagerCasos(this);

        spinnerAbogados = (Spinner) findViewById(R.id.spAbogados);
        grabar = (Button) findViewById(R.id.btnGrabar);
        denominacion = (EditText) findViewById(R.id.txtDenominacion);
        caracteristicas = (EditText) findViewById(R.id.txtCaracteristicas);
        fecha = (EditText) findViewById(R.id.txtFecha);
        pickerfechaApertura = (DatePicker) findViewById(R.id.datePickerCaso);
        botonPicker = (Button) findViewById(R.id.btnPicker);

        cargarSpinner();
        spinnerAbogados.setOnItemSelectedListener(escucha);
        grabar.setOnClickListener(escucha2);
        botonPicker.setOnClickListener(escucha3);
    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerAbogados.getSelectedItemPosition();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabarCaso();
        }
    };

    View.OnClickListener escucha3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pickerVisible) {
                pickerfechaApertura.setVisibility(View.VISIBLE);
                pickerVisible = true;
            } else {
                int anno=0;
                String dia,mes;
                pickerfechaApertura.setVisibility(View.GONE);

                if(pickerfechaApertura.getDayOfMonth()<10){
                    dia="0"+ String.valueOf(pickerfechaApertura.getDayOfMonth());
                }else{
                    dia=String.valueOf(pickerfechaApertura.getDayOfMonth());
                }

                if(pickerfechaApertura.getMonth()<10){
                    mes="0"+ String.valueOf(pickerfechaApertura.getMonth() + 1);
                }else{
                    mes=String.valueOf(pickerfechaApertura.getMonth() + 1);
                }
                anno = pickerfechaApertura.getYear();
                fecha.setText(anno + "-" + mes + "-" + dia);
                pickerVisible = false;
            }
        }
    };

   public void borrarCampos() {
       denominacion.setText("");
       caracteristicas.setText("");
       fecha.setText("");
   }

    public void cargarSpinner() {
        List<Abogado> misAbogados = managerAbogados.obtenerAbogadosDeBD();
        List<Abogado> nombres = new ArrayList<>();
        for (int i = 0; i < misAbogados.size(); i++) {
            nombres = managerAbogados.obtenerNombresAbogados();
        }
        ArrayAdapter<Abogado> adaptador = new ArrayAdapter<Abogado>(this, android.R.layout.simple_spinner_item, nombres);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerAbogados.setAdapter(adaptador);
    }

    public void grabarCaso(){
        if(denominacion.getText().toString().compareTo("")==0 || fecha.getText().toString().compareTo("")==0 || caracteristicas.getText().toString().compareTo("")==0){
            Toast.makeText(Alta_Casos.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            List<Abogado> abogado = managerAbogados.obtenerUnAbogadoPorNombre(spinnerAbogados.getSelectedItem().toString());
            long id = managerCasos.insertarUnCasoEnBD(denominacion.getText().toString(), fecha.getText().toString(), caracteristicas.getText().toString(), abogado.get(0).getNumColegiado());
            Toast.makeText(Alta_Casos.this, "Nuevo Caso insertado con id " + id, Toast.LENGTH_SHORT).show();
            borrarCampos();
        }
    }

}
