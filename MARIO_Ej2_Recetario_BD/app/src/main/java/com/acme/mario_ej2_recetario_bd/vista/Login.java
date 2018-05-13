package com.acme.mario_ej2_recetario_bd.vista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.bbdd.DataBaseManagerUsuarios;

public class Login extends AppCompatActivity {

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
        boolean esInvitado = false;
        Bundle bundle = new Bundle();

        Intent intent = new Intent(Login.this, Menu.class);

        try {

            if (managerUsuarios.buscarUsuarioPorLogin(user.getText().toString(), pass.getText().toString())) {
                if (user.getText().toString().compareToIgnoreCase("invitado") == 0) {
                    esInvitado = true;
                    intent.putExtra("xxx", esInvitado);
                    intent.putExtra("nombre", user.getText().toString());
                } else {
                    esInvitado = false;
                    intent.putExtra("xxx", esInvitado);
                    intent.putExtra("nombre", user.getText().toString());
                }
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.mensaje, Toast.LENGTH_SHORT).show();
            }
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(Login.this, R.string.mensTry, Toast.LENGTH_SHORT).show();
        }
    }

}
