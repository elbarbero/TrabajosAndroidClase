package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examen.mario.barbero_santillan_mario_exam.R;

public class MenuUsuario extends AppCompatActivity {

    Button NuevaRec;
    Button Consultarrec;
    Button atras;
    TextView textoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        Resources res = this.getResources();

        textoMenu = (TextView) findViewById(R.id.txtMenuUser);
        Consultarrec = (Button) findViewById(R.id.btnVerRecetas);
        NuevaRec = (Button) findViewById(R.id.btnNuevaReceta);
        atras = (Button) findViewById(R.id.btnAtras);

        textoMenu.setText(res.getString(R.string.menUser) + " " + InicioActivity.miUsuario.get(Login.pos).getNombre());

       NuevaRec.setOnClickListener(escuchar);
        Consultarrec.setOnClickListener(escuchar2);
        atras.setOnClickListener(escuchar3);
}

    private View.OnClickListener escuchar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            intent = new Intent(MenuUsuario.this, AltaRecetas.class);
            startActivityForResult(intent,2);
        }
    };

    private View.OnClickListener escuchar2 = new View.OnClickListener() {//PARA LEER
        @Override
        public void onClick(View view) {
            Intent intent = null;
            intent = new Intent(MenuUsuario.this, LeerRecetas.class);
            startActivityForResult(intent,1);
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
                if (resultCode != 0){
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
