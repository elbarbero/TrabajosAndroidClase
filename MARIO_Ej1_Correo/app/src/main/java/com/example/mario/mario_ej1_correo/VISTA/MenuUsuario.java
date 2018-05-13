package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.R;

public class MenuUsuario extends AppCompatActivity {

    Button VerMens;
    Button EnviarMens;
    Button atras;
    TextView textoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        Resources res = this.getResources();

        textoMenu = (TextView) findViewById(R.id.txtMenuUser);
        VerMens = (Button) findViewById(R.id.btnVerMensajes);
        EnviarMens = (Button) findViewById(R.id.btnEnviarMensaje);
        atras = (Button) findViewById(R.id.btnAtras);

        textoMenu.setText(res.getString(R.string.menUser) + " " + Inicio.miUsuario.get(Login.pos).getNombre());


        EnviarMens.setOnClickListener(escucharEnviar);
        VerMens.setOnClickListener(escucharLeer);
        atras.setOnClickListener(escuchar3);
    }

    private View.OnClickListener escucharEnviar = new View.OnClickListener() {//PARA ENVIAR
        @Override
        public void onClick(View view) {
            Intent intent = null;
            intent = new Intent(MenuUsuario.this, VEnvio.class);
            startActivityForResult(intent, 2);
        }
    };

    private View.OnClickListener escucharLeer = new View.OnClickListener() {//PARA LEER
        @Override
        public void onClick(View view) {
            Intent intent = null;
            intent = new Intent(MenuUsuario.this, VLeer.class);
            startActivityForResult(intent, 1);
        }
    };

    private View.OnClickListener escuchar3 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode != 0) {
                    finish();
                }
                break;
            case 2:
                if (resultCode != 0) {
                    finish();
                }
                break;
        }
    }
}
