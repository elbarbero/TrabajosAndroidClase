package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.R;

import java.text.DateFormat;
import java.util.Date;

public class VEnvio extends AppCompatActivity {

    Spinner miSpinner;
    EditText miAsunto;
    EditText miTexto;
    Button enviar;
    public static String fecha;

    int posUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venvio);

        miSpinner = (Spinner) findViewById(R.id.spDestinatario);
        miAsunto = (EditText) findViewById(R.id.txtAsunto);
        miTexto = (EditText) findViewById(R.id.txtMensaje);
        enviar = (Button) findViewById(R.id.btnEnviar);

        cargarSpinner();
        enviar.setOnClickListener(escucharEnviar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_venvio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDeconexion:
                Intent intent = null;
                intent = new Intent(VEnvio.this, MenuUsuario.class);
                setResult(RESULT_OK);//ESTO ME ENVIA UN -1
                finish();
                break;
            case R.id.mMenu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener escucharEnviar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Date d = new Date();
            buscarUsuario();
            miAsunto.setText("");
            miTexto.setText("");
            fecha= (String) DateFormat.getTimeInstance().format(d.getTime());
        }
    };

    @Override
    public void onBackPressed() {
    }

    public void cargarSpinner() {
        ArrayAdapter<Usuario> adaptador = new ArrayAdapter<Usuario>(this, android.R.layout.simple_spinner_item, Inicio.miUsuario);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        miSpinner.setAdapter(adaptador);
    }

    public void buscarUsuario() {
        posUser = miSpinner.getSelectedItemPosition();
        Inicio.miUsuario.get(posUser).AnadirCorreo(String.valueOf(miAsunto.getText()), String.valueOf(miTexto.getText()), String.valueOf(Login.pos));
    }
}
