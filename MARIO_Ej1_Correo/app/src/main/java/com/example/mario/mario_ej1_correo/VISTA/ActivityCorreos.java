package com.example.mario.mario_ej1_correo.VISTA;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.MODELO.Correo;
import com.example.mario.mario_ej1_correo.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityCorreos extends AppCompatActivity {

    Button atras;
    TextView asunto;
    TextView remitente;
    TextView fecha;
    TextView destinatario;
    TextView mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correos);
        ArrayList<Correo> misCorreos = getIntent().getExtras().getParcelableArrayList("c");
        int pos = getIntent().getExtras().getInt("c2");
        Date d = new Date();

        asunto = (TextView) findViewById(R.id.txtAsunto);
        remitente = (TextView) findViewById(R.id.txtRemitente);
        fecha = (TextView) findViewById(R.id.txtFecha);
        destinatario = (TextView) findViewById(R.id.txtDestinatario);
        mensaje = (TextView) findViewById(R.id.txtMensaje);
        atras = (Button) findViewById(R.id.btnAtras);

        atras.setOnClickListener(escucha);

        asunto.setText(misCorreos.get(pos).getAsunto());
        remitente.setText(Inicio.miUsuario.get(Integer.parseInt(misCorreos.get(pos).getCodigo())).getNombre());
        fecha.setText((CharSequence) DateFormat.getTimeInstance().format(d.getTime()));
        destinatario.setText(Inicio.miUsuario.get(Login.pos).getNombre());
        mensaje.setText(misCorreos.get(pos).getTexto());
    }

    View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    public void onBackPressed() {//CON ESTE MÉTODO ANULAMOS LOS BOTONES DEL SISTEMA
    }

}
