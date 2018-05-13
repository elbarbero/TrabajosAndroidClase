package com.examen.mario.barbero_santillan_mario_exam.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.examen.mario.barbero_santillan_mario_exam.R;

public class Autor extends AppCompatActivity {

    Button atras;
    TextView nombre;
    TextView curso;
    TextView modulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor);

        atras=(Button)findViewById(R.id.btnAtras);
        nombre=(TextView)findViewById(R.id.txtNombre);
        curso=(TextView)findViewById(R.id.txtCurso);
        modulo=(TextView)findViewById(R.id.txtModulo);

        atras.setOnClickListener(escucha);

        nombre.setText(R.string.Autor);
        curso.setText(R.string.cursoAutor);
        modulo.setText(R.string.moduloAutor);
    }
    public View.OnClickListener escucha=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(Autor.this, Inicio.class);
           // startActivity(intent);
            finish();
        }
    };
}
