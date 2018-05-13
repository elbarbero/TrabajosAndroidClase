package com.mbs.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txtSaludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtSaludo=(TextView)findViewById(R.id.textSaludo);
        /*Lo que le pasamos entre los parentesis es la "key" que
        le hemos puesto en el intent del intent del "MainActivity"*/
        String dato=getIntent().getExtras().getString("xxx");// el "xxx" es la key
        txtSaludo.setText(dato);
    }
}
