package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.R;

public class Autor extends AppCompatActivity {

    Button atras;
    TextView nombre;
    TextView curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);
        atras=(Button)findViewById(R.id.btnAtras);
        nombre=(TextView)findViewById(R.id.txtNombre);
        curso=(TextView)findViewById(R.id.txtCurso);

        atras.setOnClickListener(escucha);

        nombre.setText(R.string.Autor);
        curso.setText(R.string.cursoAutor);
    }

    public View.OnClickListener escucha=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
