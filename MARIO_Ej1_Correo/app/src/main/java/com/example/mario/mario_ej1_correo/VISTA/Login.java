package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mario.mario_ej1_correo.R;

public class Login extends AppCompatActivity {

    private EditText pass;
    private EditText user;
    private Button aceptar;
    public static int pos = 0;
    int contadorErrores = 0;

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
        boolean existe = false;
        int i = 0;

        Intent intent = null;
        try {

            while (i < Inicio.miUsuario.size() && !existe) {
                if (Inicio.miUsuario.get(i).getNombre().compareToIgnoreCase(user.getText().toString()) == 0 &&
                        Inicio.miUsuario.get(i).getContrasena().compareToIgnoreCase(pass.getText().toString()) == 0) {
                    existe = true;

                    String nombreUser = Inicio.miUsuario.get(i).getNombre();
                    intent = new Intent(Login.this, MenuUsuario.class);
                    pos=i;
                    intent.putExtra("xxxLogin", Inicio.miUsuario);
                    //intent.putExtra("xxxLogin2", nombreUser);
                    //intent.putExtra("xxxLogin3", i);//ENVIO LA POSICION DEL USUARIO QUE SE HA REGISTRADO
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
            Toast.makeText(Login.this, "EEROR", Toast.LENGTH_SHORT).show();
        }
    }

}




