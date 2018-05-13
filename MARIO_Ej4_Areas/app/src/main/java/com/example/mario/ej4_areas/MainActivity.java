package com.example.mario.ej4_areas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button calcular;
    EditText miLado, miBase, miAltura;
    TextView result;
    CheckBox triangulo, cuadrado, rectangulo;
    ImageView figura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuadrado = (CheckBox) findViewById(R.id.chkCuadrado);
        triangulo = (CheckBox) findViewById(R.id.chkTriangulo);
        rectangulo = (CheckBox) findViewById(R.id.chkRectangulo);
        calcular = (Button) findViewById(R.id.btnCalcular);
        miLado = (EditText) findViewById(R.id.txtLado);
        miAltura = (EditText) findViewById(R.id.txtAltura);
        miBase = (EditText) findViewById(R.id.txtBase);
        result = (TextView) findViewById(R.id.txtResultado);

        cuadrado.setOnCheckedChangeListener(mostrar);
        triangulo.setOnCheckedChangeListener(mostrar2);
        rectangulo.setOnCheckedChangeListener(mostrar2);
        calcular.setOnClickListener(escuchar);
        figura = (ImageView) findViewById(R.id.imgImagen);
    }

    private CompoundButton.OnCheckedChangeListener mostrar = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            /*es lo mismo que un if-else
            la ? hace referencia a una pregunta, es necesaria
            * El primer View hace referencia al primer if y el segundo al else
            * es decir, k si es true (por defecto la variable "b" se inicializa a true)
            * ponte VISIBLE, por lo contrario (un else), si es falso ponte en GONE*/
            int visibility = b ? View.VISIBLE : View.GONE;
            miLado.setText("");

            if (b) {
                figura.setVisibility(visibility);//PARA QUE LA IMAGEN SEA VISIBLE
                figura.setImageResource(R.drawable.cuad);
                triangulo.setEnabled(false);
                rectangulo.setEnabled(false);
            } else {
                //figura.setImageResource(android.R.mipmap.sym_def_app_icon);
                figura.setVisibility(visibility);//PARA QUE LA IMAGEN SE VUELVA A HACER INVISIBLE
                triangulo.setEnabled(true);
                rectangulo.setEnabled(true);
            }
            miLado.setVisibility(visibility);
        }
    };

    private CompoundButton.OnCheckedChangeListener mostrar2 = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int visibility = b ? View.VISIBLE : View.GONE;
            miBase.setText("");
            miAltura.setText("");
            if (b) {
                if (rectangulo.isChecked()) {
                    figura.setVisibility(visibility);//PARA QUE LA IMAGEN SEA VISIBLE EN CASO DE QUE SEA EL RECTANGULO
                    figura.setImageResource(R.drawable.rectan);
                    triangulo.setEnabled(false);
                } else {
                    figura.setVisibility(visibility);//PARA QUE LA IMAGEN SEA VISIBLE EN CASO DE QUE SEA EL TRIÁNGULO
                    figura.setImageResource(R.drawable.trian);
                    rectangulo.setEnabled(false);
                }
                cuadrado.setEnabled(false);
            } else {
                //figura.setImageResource(android.R.mipmap.sym_def_app_icon);
                figura.setVisibility(visibility);//PARA QUE LA IMAGEN SE VUELVA A HACER INVISIBLE
                rectangulo.setEnabled(true);
                triangulo.setEnabled(true);
                cuadrado.setEnabled(true);
            }
            miBase.setVisibility(visibility);
            miAltura.setVisibility(visibility);
        }
    };

    private View.OnClickListener escuchar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cuadrado miCuadrado;
            Rectangulo miRectandulo;
            Triangulo miTriangulo;
            double lados = 0, bases = 0, alturas = 0;
            String total = "";

            if (rectangulo.isChecked()) {
                if (miAltura.getText().toString().isEmpty() || miBase.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.mensajeRectangulo, Toast.LENGTH_SHORT).show();
                } else {
                    alturas = (Double.parseDouble(miAltura.getText().toString()));
                    bases = (Double.parseDouble(miBase.getText().toString()));
                    miRectandulo = new Rectangulo(bases, alturas);
                    total = miRectandulo.calcular();
                }
            } else if (cuadrado.isChecked()) {
                if (miLado.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.mensajeCuadrado, Toast.LENGTH_SHORT).show();
                } else {
                    lados = (Double.parseDouble(miLado.getText().toString()));
                    miCuadrado = new Cuadrado(lados);
                    total = miCuadrado.calcular();
                }
            } else {
                if (miAltura.getText().toString().isEmpty() || miBase.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.mensajeTriangulo, Toast.LENGTH_SHORT).show();
                } else {
                    alturas = (Double.parseDouble(miAltura.getText().toString()));
                    bases = (Double.parseDouble(miBase.getText().toString()));
                    miTriangulo = new Triangulo(bases, alturas);
                    total = miTriangulo.calcular();
                }
            }
            result.setText(total);
        }
    };

}
