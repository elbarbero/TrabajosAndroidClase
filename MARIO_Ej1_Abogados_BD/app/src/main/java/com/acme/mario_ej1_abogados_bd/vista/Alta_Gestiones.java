package com.acme.mario_ej1_abogados_bd.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerCasos;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerGestiones;
import com.acme.mario_ej1_abogados_bd.modelo.Caso;

import java.util.ArrayList;
import java.util.List;

public class Alta_Gestiones extends AppCompatActivity {

    EditText descripcion, fecha;
    Spinner spinnerCasos;
    Button grabar, botonPicker;
    DatePicker pickerfecha;
    DataBaseManagerGestiones managerGestiones;
    DataBaseManagerCasos managerCasos;
    int posicionSpinner = 0;
    boolean pickerVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__gestiones);
        managerGestiones=new DataBaseManagerGestiones(this);
        managerCasos=new DataBaseManagerCasos(this);

        descripcion=(EditText) findViewById(R.id.txtDescripcion);
        fecha=(EditText) findViewById(R.id.txtFecha);
        grabar=(Button)findViewById(R.id.btnGrabar);
        botonPicker=(Button)findViewById(R.id.btnPicker);
        pickerfecha=(DatePicker)findViewById(R.id.datePickerGestion);
        spinnerCasos=(Spinner) findViewById(R.id.spCaso);

        cargarSpinner();
        spinnerCasos.setOnItemSelectedListener(escucha);
        grabar.setOnClickListener(escucha2);
        botonPicker.setOnClickListener(escucha3);
    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerCasos.getSelectedItemPosition();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabarGestion();
        }
    };

    View.OnClickListener escucha3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pickerVisible) {
                pickerfecha.setVisibility(View.VISIBLE);
                pickerVisible = true;
            } else {
                int anno=0;
                String dia,mes;
                pickerfecha.setVisibility(View.GONE);

                if(pickerfecha.getDayOfMonth()<10){
                    dia="0"+ String.valueOf(pickerfecha.getDayOfMonth());
                }else{
                    dia=String.valueOf(pickerfecha.getDayOfMonth());
                }

                if(pickerfecha.getMonth()<10){
                    mes="0"+ String.valueOf(pickerfecha.getMonth() + 1);
                }else{
                    mes=String.valueOf(pickerfecha.getMonth() + 1);
                }
                anno = pickerfecha.getYear();
                fecha.setText(anno + "-" + mes + "-" + dia);
                pickerVisible = false;
            }
        }
    };

    public void borrarCampos() {
        descripcion.setText("");
        fecha.setText("");
    }

    public void cargarSpinner() {
        List<Caso> misCasos = managerCasos.obtenerCasosDeBD();
        List<Caso> nombres = new ArrayList<>();
        for (int i = 0; i < misCasos.size(); i++) {
            nombres = managerCasos.obtenerNombresCasos();
        }
        ArrayAdapter<Caso> adaptador = new ArrayAdapter<Caso>(this, android.R.layout.simple_spinner_item, nombres);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerCasos.setAdapter(adaptador);
    }

    public void grabarGestion(){
        if(descripcion.getText().toString().compareTo("")==0 || fecha.getText().toString().compareTo("")==0){
            Toast.makeText(Alta_Gestiones.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            List<Caso> caso = managerCasos.obtenerUnCasoPorNombre(spinnerCasos.getSelectedItem().toString());
            long id = managerGestiones.insertarUnaGestionEnBD(caso.get(0).getId(), fecha.getText().toString(),descripcion.getText().toString());
            Toast.makeText(Alta_Gestiones.this, "Nuevo Gestión insertada con id " + id, Toast.LENGTH_SHORT).show();
            borrarCampos();
        }
    }
}
