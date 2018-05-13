package com.example.mario_portatil.barbero_mario_2ev.vista;

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

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerNotas;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;

import java.util.ArrayList;
import java.util.List;

public class Alta_Notas extends AppCompatActivity {

    EditText calificacion, fecha;
    Spinner spinnerAlumnos;
    Button grabar, botonPicker;
    DatePicker pickerfechaControl;
    int posicionSpinner = 0;
    boolean pickerVisible = false;
    DataBaseManagerAlumnos managerAlumnos;
    DataBaseManagerNotas managerNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__notas);
        managerAlumnos=new DataBaseManagerAlumnos(this);
        managerNotas=new DataBaseManagerNotas(this);

        spinnerAlumnos = (Spinner) findViewById(R.id.spAlumnos);
        grabar = (Button) findViewById(R.id.btnGrabar);
        calificacion = (EditText) findViewById(R.id.txtCalificacion);
        fecha = (EditText) findViewById(R.id.txtFecha);
        pickerfechaControl = (DatePicker) findViewById(R.id.datePickerNota);
        botonPicker = (Button) findViewById(R.id.btnPicker);

        cargarSpinner();
        spinnerAlumnos.setOnItemSelectedListener(escucha);
        grabar.setOnClickListener(escucha2);
        botonPicker.setOnClickListener(escucha3);
    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerAlumnos.getSelectedItemPosition();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabarNota();
        }
    };

    View.OnClickListener escucha3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!pickerVisible) {
                pickerfechaControl.setVisibility(View.VISIBLE);
                pickerVisible = true;
            } else {
                pickerfechaControl.setVisibility(View.GONE);
                int dia = pickerfechaControl.getDayOfMonth();
                int mes = pickerfechaControl.getMonth() + 1;
                int anno = pickerfechaControl.getYear();
                fecha.setText(anno + "-" + mes + "-" + dia);
                pickerVisible = false;
            }
        }
    };

    public void borrarCampos() {
        fecha.setText("");
        calificacion.setText("");
    }

    public void cargarSpinner() {
        List<Alumno> misAlumnos = managerAlumnos.obtenerNombresAlumnos();
       /* List<Alumno> nombres = new ArrayList<>();
        for (int i = 0; i < misAbogados.size(); i++) {
            nombres = managerAlumnos.obtenerNombresAbogados();
        }*/
        ArrayAdapter<Alumno> adaptador = new ArrayAdapter<Alumno>(this, android.R.layout.simple_spinner_item, misAlumnos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerAlumnos.setAdapter(adaptador);
    }

    public void grabarNota(){
        if(calificacion.getText().toString().compareTo("")==0 || fecha.getText().toString().compareTo("")==0 ){
            Toast.makeText(Alta_Notas.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            List<Alumno> alumno = managerAlumnos.obtenerUnAlumnoPorNombre(spinnerAlumnos.getSelectedItem().toString());
            if (managerNotas.compararFechas(fecha.getText().toString(),alumno.get(0).getId())){
                Toast.makeText(Alta_Notas.this, "Ya hay una nota con esa fecha", Toast.LENGTH_SHORT).show();
            }else {
                long id = managerNotas.insertarUnaNotaEnBD(alumno.get(0).getId(), fecha.getText().toString(), Float.parseFloat(calificacion.getText().toString()));
                Toast.makeText(Alta_Notas.this, "Nueva Nota insertada con id " + id, Toast.LENGTH_SHORT).show();
                borrarCampos();
            }
        }
    }

}
