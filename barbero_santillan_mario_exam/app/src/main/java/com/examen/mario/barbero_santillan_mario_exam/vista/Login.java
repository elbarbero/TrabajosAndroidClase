package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.examen.mario.barbero_santillan_mario_exam.R;

public class Login extends AppCompatActivity {

    private EditText pass;
    private EditText user;
    private Button aceptar;
    private Button atras;
    public static int pos = 0;
    int contadorErrores = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pass = (EditText) findViewById(R.id.txtPassword);
        user = (EditText) findViewById(R.id.txtUser);
        aceptar = (Button) findViewById(R.id.btnAceptar);
        atras = (Button) findViewById(R.id.btnAtras);

        aceptar.setOnClickListener(escuchar);
        atras.setOnClickListener(escuchar2);
    }

    private View.OnClickListener escuchar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            buscar();
        }
    };

    public void buscar() {
        boolean existe = false;
        int i = 0;

        Intent intent = null;
        try {

            while (i < InicioActivity.miUsuario.size() && !existe) {
                if (InicioActivity.miUsuario.get(i).getNombre().compareToIgnoreCase(user.getText().toString()) == 0 &&
                        InicioActivity.miUsuario.get(i).getContrasenna().compareToIgnoreCase(pass.getText().toString()) == 0) {
                    existe = true;

                    String nombreUser = InicioActivity.miUsuario.get(i).getNombre();
                    intent = new Intent(Login.this, MenuUsuario.class);
                    pos=i;

                } else {
                    i++;
                }
            }
            if (contadorErrores == 2) {
                finish();
            }
            if (!existe) {
                Toast.makeText(this, R.string.mensaje, Toast.LENGTH_SHORT).show();
                contadorErrores++;
            } else {
                startActivity(intent);
                contadorErrores = 0;
            }

        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(Login.this, R.string.mensTry, Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener escuchar2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
}
