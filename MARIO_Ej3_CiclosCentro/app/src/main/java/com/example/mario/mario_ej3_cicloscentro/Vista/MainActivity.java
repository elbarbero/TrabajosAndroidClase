package com.example.mario.mario_ej3_cicloscentro.Vista;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mario.mario_ej3_cicloscentro.R;


public class MainActivity extends AppCompatActivity {

    Spinner miSpinner;
    private EditText pass;
    TableRow fila1;
    TableRow fila2;
    TableRow fila3;
    Button aceptar;
    int contadorErrores = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miSpinner = (Spinner) findViewById(R.id.spinner1);
        pass = (EditText) findViewById(R.id.txtPass);
        fila1 = (TableRow) findViewById(R.id.row1);
        fila2 = (TableRow) findViewById(R.id.row2);
        fila3 = (TableRow) findViewById(R.id.row3);
        aceptar = (Button) findViewById(R.id.btnAceptar);
        String[] datosSpinner = new String[]{MainActivity.this.getResources().getString(R.string.alum), MainActivity.this.getResources().getString(R.string.invi)};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datosSpinner);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        miSpinner.setAdapter(adaptador);

        aceptar.setOnClickListener(compruebo);
    }

    View.OnClickListener compruebo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            comprobarPass();
        }
    };

    public boolean comprobarPass() {
        boolean existe = false;
        String[] nombre, contra;
        int i = 0;
        View v=null;
        boolean tipoUsuario=false;//PARA DIFERENCIAR QUE USUARIO HA ENTRADO Y DEJAR HACER CLICK EN EL ALUMNO

        Intent intent = null;
        Resources res = MainActivity.this.getResources();
        nombre = MainActivity.this.getResources().getStringArray(R.array.users);
        contra = MainActivity.this.getResources().getStringArray(R.array.contras);

        if (miSpinner.getSelectedItem().toString().equals("")) {
            Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.mensajeUser), Toast.LENGTH_SHORT).show();
        } else {

            while (i < ((nombre.length)) && !existe) {

                if (nombre[i].compareToIgnoreCase(miSpinner.getSelectedItem().toString()) == 0 &&
                        contra[i].compareToIgnoreCase(pass.getText().toString()) == 0) {
                    contadorErrores = 0;
                    existe = true;
                    Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.mensajeCorrecto), Toast.LENGTH_SHORT).show();
                } else {
                    i++;
                }
            }
            if (contadorErrores == 2) {
                finish();
            }
            if (!existe) {
                Toast.makeText(MainActivity.this, R.string.mensajeLogin, Toast.LENGTH_SHORT).show();
                contadorErrores++;
            } else {
                    intent = new Intent(MainActivity.this, AlumnoActivity.class);
                if (miSpinner.getSelectedItem().toString().compareToIgnoreCase(nombre[0]) == 0) {
                    tipoUsuario=true;//PARA EL ALUMNO
                    intent.putExtra("xxx",tipoUsuario);
                }else{
                    tipoUsuario=false;//PARA EL INVITADO
                    intent.putExtra("xxx",tipoUsuario);
                }
                startActivity(intent);
            }
        }
        return existe;
    }

}
