package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerUsuarios;

public class login extends AppCompatActivity {

    private EditText pass;
    private EditText user;
    private Button aceptar;
    DataBaseManagerUsuarios managerUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = (EditText) findViewById(R.id.txtPassword);
        user = (EditText) findViewById(R.id.txtUser);
        aceptar = (Button) findViewById(R.id.btnAceptar);

        aceptar.setOnClickListener(escuchar);
    }

    private View.OnClickListener escuchar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buscar();
        }
    };

    public void buscar() {
        managerUsuarios = new DataBaseManagerUsuarios(this);
        Intent intent = new Intent(login.this, Menu.class);

        try {

            if (managerUsuarios.buscarUsuarioPorLogin(user.getText().toString(), pass.getText().toString())) {
              //  managerUsuarios.actualizarFechaAcceso(user.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.mensaje, Toast.LENGTH_SHORT).show();
            }
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(login.this, R.string.mensTry, Toast.LENGTH_SHORT).show();
        }
    }

}
