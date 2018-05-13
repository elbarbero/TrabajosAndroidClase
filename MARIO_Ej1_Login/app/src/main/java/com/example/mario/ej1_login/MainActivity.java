package com.example.mario.ej1_login;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText pass;
    private EditText user;
    private Button aceptar;
    ArrayList<Usuario> miUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        String[] nombre,contra,apellido,curso;
        int i = 0;

        Resources res = MainActivity.this.getResources();
        nombre = res.getStringArray(R.array.usuarios);//así accedemos a los valores que contriene la variable string de la carpeta Recursos
        contra = res.getStringArray(R.array.contraseñas);
        apellido= res.getStringArray(R.array.apellidos);
        curso= res.getStringArray(R.array.curso);
        Intent intent=null;
        try {
            while (i < ((nombre.length)) && !existe) {

                if (nombre[i].compareToIgnoreCase(user.getText().toString()) == 0 &&
                        contra[i].compareToIgnoreCase(pass.getText().toString()) == 0) {
                    existe = true;
                    Usuario u = new Usuario(user.getText().toString(), apellido[i], curso[i], pass.getText().toString());
                    intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("xxx", u);

                } else {
                    i++;
                }
            }
            if (!existe) {
                Toast.makeText(MainActivity.this, R.string.mensaje, Toast.LENGTH_SHORT).show();
            } else
            {
                startActivity(intent);
            }

        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(MainActivity.this, "EEROR", Toast.LENGTH_SHORT).show();
        }
    }

}
