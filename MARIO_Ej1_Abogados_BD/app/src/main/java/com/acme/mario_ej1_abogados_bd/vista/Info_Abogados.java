package com.acme.mario_ej1_abogados_bd.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;

public class Info_Abogados extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener  {

    DataBaseManagerAbogados manaegerAbogados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__abogados);
        if (findViewById(R.id.fragmento_Contenedor) != null && savedInstanceState == null) {
            FragmentoLista lista = new FragmentoLista();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_Contenedor, lista).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int posicion) {
        FragmentoDetalle detalleFragment = (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
        manaegerAbogados=new DataBaseManagerAbogados(this);
        int  id=FragmentoLista.listAbogados.get(posicion).getId();
        if (detalleFragment != null) {//EN CASO DE QUE EXISTE (TABLET)
            detalleFragment.cambio(id);
        } else {
            FragmentoDetalle nuevo = new FragmentoDetalle();
            Bundle args = new Bundle();
            args.putInt("id", id);//LE PASO EL ID PARA EN VEZ DE LA POSICION
            nuevo.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_Contenedor, nuevo).addToBackStack(null).commit();
        }
    }
}
