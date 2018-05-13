package com.mbs.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editNombre;
    private Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*La siguiente línea te "infla", te muestra, lo que contiene
        * el archivo xml que le hemos llamado "layout_comienzo"*/
        setContentView(R.layout.layout_comienzo);
        /*Las 2 siguientes nos buscan en el layout un id llamado button
        * que sea boton, por eso lo casteamos. Igual con el editNombre*/
        btnAceptar = (Button) findViewById(R.id.button);// si no lo encuentra te devuelve "null"
        editNombre = (EditText) findViewById(R.id.editNombre);
        /*btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Hola",Toast.LENGTH_SHORT).show();
                editNombre.setText("Mario");
            }
        });*/

        btnAceptar.setOnClickListener(escucha);

    }//FIN DE ONCREATE


    private View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /*El objeto "intent" es la comunicación entre dos o más activitys.
            * Con el "intent" podemos abrir otro activity que ha sido
            * llamado desde otro activity*/
            String nombre=editNombre.getText().toString();// recogemos el nombre
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            /*inten.putExtra(key,valor);
            en el primero le pasamos lam clave (tiene que ser la misma en este intent y en el intent del 2 activity)
             y en el segundo el nombre de la variable*/
            intent.putExtra("xxx",nombre);// aquí cogemos la key y el valor que tenga la variable
            startActivity(intent);
        }
    };

}
