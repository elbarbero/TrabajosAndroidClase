package com.example.mario.ej1_login;

import android.media.tv.TvContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView logo;
    TextView nombApe;
    TextView curso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nombApe=(TextView)findViewById(R.id.txtNombre);
        curso=(TextView)findViewById(R.id.txtCurso);
        logo=(ImageView)findViewById(R.id.imgLogo);
        logo.setImageResource(R.drawable.logo_artesano);

        Usuario user = (Usuario) getIntent().getExtras().getParcelable("xxx");
        nombApe.setText(user.getNombre() +" "+ user.getApellido());
        curso.setText(user.getCurso());
    }
}
