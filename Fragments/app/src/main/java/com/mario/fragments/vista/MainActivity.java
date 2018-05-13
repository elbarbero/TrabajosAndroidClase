package com.mario.fragments.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mario.fragments.R;
import com.mario.fragments.controlador.OperacionesDatos;

public class MainActivity extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener {//Implementamos es metodo que escucha el fragmento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OperacionesDatos.cargarDatos();
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragmento_Contenedor) != null && savedInstanceState == null) {
            FragmentoLista lista = new FragmentoLista();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_Contenedor, lista).commit();
        }
    }


    @Override
    public void onFragmentInteraction(int posicion) {
        FragmentoDetalle detalleFragment = (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
//DAME EL FRAGMENTO QUE SE LLAMA fragmentoDetalle
        if (detalleFragment != null) {//EN CASO DE QUE EXISTE (TABLET)
            detalleFragment.cambio(posicion);
        } else {
            FragmentoDetalle nuevo = new FragmentoDetalle();
            Bundle args = new Bundle();
            args.putInt("posicion", posicion);
            nuevo.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_Contenedor, nuevo).addToBackStack(null).commit();
        }
    }
}
