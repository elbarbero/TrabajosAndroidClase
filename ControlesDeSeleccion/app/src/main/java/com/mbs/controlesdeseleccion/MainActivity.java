package com.mbs.controlesdeseleccion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner miSpinner;
    ListView miListView;
    final String []datos={"Elemento 1","Elemento 2","Elemento 3"};
    String[]datos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       miSpinner=(Spinner)findViewById(R.id.spinner2);
        miListView=(ListView)findViewById(R.id.lstView);
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        //el Main que queramos, el diseño que le vamos a poner (el layout que queramos), y la variable donde guargemos los datos, que lo hemos llamado "datos"
        miListView.setAdapter(adaptador);
        miListView.setOnItemClickListener(operacion);//para las listas hay que poner un "OnItem.... No vale con el spinner"
       adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        miSpinner.setAdapter(adaptador);
       // miSpinner.setOnItemClickListener(operacion);//Esto no funcionaria xk pertenece al List y no al Spinner
        //EN EL SPINNER SELECCIONAS ELEMENTOS Y EN EL LIST VIEW HACES ONCLICK
        miSpinner.setOnItemSelectedListener(accion2);

        //------------PARA EL ARRAY QUE HEMOS CREDO EN LA CARPETA VALUES QUE LE HEMOS LLAMADO CIUDADES-------------/
        datos2=getResources().getStringArray(R.array.ciudades);
        ArrayAdapter<CharSequence>adaptador2=ArrayAdapter.createFromResource(MainActivity.this,R.array.ciudades,android.R.layout.simple_spinner_dropdown_item);
        miListView.setAdapter(adaptador2);
        miListView.setOnItemClickListener(operacion);
    }
    //en el ListView se utiliza "OnItemClickListener"
    AdapterView.OnItemClickListener operacion=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           //Toast.makeText(MainActivity.this,datos[i],Toast.LENGTH_LONG).show();
            Toast.makeText(MainActivity.this,datos2[i],Toast.LENGTH_LONG).show();
        }
    };
    //en el Spinner se utiliza el "OnItemSelectedListener"
    AdapterView.OnItemSelectedListener accion2=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this,datos[i],Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {//la ? puede ser cualquier valor

        }
    };
}
