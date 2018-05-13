package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

public class TodosInmuebles extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_inmuebles);

        if (findViewById(R.id.fragmento_Contenedor) != null && savedInstanceState == null) {
            FragmentoLista lista = new FragmentoLista();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_Contenedor, lista).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int posicion) {
        FragmentoDetalle detalleFragment = (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);

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
