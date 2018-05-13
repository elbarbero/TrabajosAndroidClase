package com.example.mario.ej2_numeros_primos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtResult;
    private TextView txtNum;
    private Button btnCalculo;
    Primos primos = new Primos();
    ArrayList<Integer> numPri = new ArrayList<>();//creamos un array List de tipo Integer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = (TextView) findViewById(R.id.txtResultado);
        txtNum = (TextView) findViewById(R.id.txtNumero);
        btnCalculo = (Button) findViewById(R.id.btnCalcular);
        btnCalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNum.getText().toString().compareToIgnoreCase("0") == 0) {//si el campo es un cero...
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Introduce una posición valida")//el mensaje que queremos que muestre
                            /*.setCancelable(false)*/
                            .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {//solo tendrá un botón, así que le ponemos el texto que queremos que ponga en el mismo
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();// para que me cree el mensaje de error
                    alert.show();//aquí me enseña el mensaje que hemos creado anteriormente
                } else {
                    try {
                        int numero = Integer.parseInt(txtNum.getText().toString());
                        txtResult.setText(String.valueOf(primos.meterEnArray(numero)));

                    } catch (NumberFormatException e) {
                        //txtNum.setFocusable(true);
                        //Toast.makeText(MainActivity.this,"Introduce un número",Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Introduce un número")//el mensaje que queremos que muestre
                            /*.setCancelable(false)*/
                                .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {//solo tendrá un botón, así que le ponemos el texto que queremos que ponga en el mismo
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();// para que me cree el mensaje de error
                        alert.show();//aquí me enseña el mensaje que hemos creado anteriormente
                    }
                }
            }
        });
    }//FIN DE ONCREATE
}
