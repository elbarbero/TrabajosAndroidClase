package com.mbs.mario_ej4_bibliotecas.vista;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mbs.mario_ej4_bibliotecas.R;
import com.mbs.mario_ej4_bibliotecas.modelo.Biblioteca;

public class DatosBiblioteca extends AppCompatActivity {

    TextView titulo;
    TextView web;
    TextView tlf;
    TextView email;
    ImageView imagenWeb;
    ImageView imagenTlf;
    ImageView imagenCorreo;
    LinearLayout lnW;
    LinearLayout lnC;
    LinearLayout lnT;
    Biblioteca biblio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_biblioteca);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titulo = (TextView) findViewById(R.id.txtTituloBiblio);
        web = (TextView) findViewById(R.id.txtWeb);
        tlf = (TextView) findViewById(R.id.txtTelefono);
        email = (TextView) findViewById(R.id.txtCorreo);
        imagenWeb = (ImageView) findViewById(R.id.imgWeb);
        imagenTlf = (ImageView) findViewById(R.id.imgTlf);
        imagenCorreo = (ImageView) findViewById(R.id.imgCorreo);
        lnW = (LinearLayout) findViewById(R.id.lnWeb);
        lnT = (LinearLayout) findViewById(R.id.lnTlf);
        lnC = (LinearLayout) findViewById(R.id.lnCorreo);

        biblio = (Biblioteca) getIntent().getExtras().getParcelable("xxx");
        String tituloBiblioteca = biblio.getNombre();
        titulo.setText(tituloBiblioteca);
        web.setText("Pagina Web");
        tlf.setText("Telefono");
        email.setText("Correo");

        imagenWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(biblio.getUrll());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        imagenCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                correo(i);
            }
        });

        imagenTlf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(biblio.getTelefono()), null));
                startActivity(intent);
            }
        });

        lnW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(biblio.getUrll());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        lnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = null;
                correo(i);
            }
        });


        lnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", String.valueOf(biblio.getTelefono()), null));
                startActivity(intent);
            }
        });

    }

    public void correo(Intent emailIntent) {
        String[] to = {biblio.getCorreo()};
        String[] cc = {"m.b.s.d.a.m@gmail.com"};
        enviar(to, cc, "Hola",
                "¿Cómo estamos?", emailIntent);
    }

    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje, Intent emailIntent) {
        emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse(biblio.getCorreo()));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        /*Con la siguiente línea elegimos una aplicacion para mandar el correo si tenemos mas de una instalada*/
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }

}
