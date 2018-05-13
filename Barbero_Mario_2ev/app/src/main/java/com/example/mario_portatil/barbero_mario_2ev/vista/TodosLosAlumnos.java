package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;

public class TodosLosAlumnos extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener {

    DataBaseManagerAlumnos managerAlumnos;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos_los_alumnos);
        if (findViewById(R.id.fragmento_Contenedor) != null && savedInstanceState == null) {
            FragmentoLista lista = new FragmentoLista();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_Contenedor, lista).commit();
        }
    }

    @Override
    public void onFragmentInteraction(int posicion) {
        FragmentoDetalle detalleFragment = (FragmentoDetalle) getSupportFragmentManager().findFragmentById(R.id.fragment_detalle);
        managerAlumnos=new DataBaseManagerAlumnos(this);
       id=FragmentoLista.listaAlumnos.get(posicion).getId();
        if (detalleFragment != null) {//EN CASO DE QUE EXISTE (TABLET)
            detalleFragment.cambio(id);
        } else {
            FragmentoDetalle nuevo = new FragmentoDetalle();
            Bundle args = new Bundle();
            args.putInt("id", id);//LE PASO EL ID DE ESE ALUIMNO SELECCIONADO
            nuevo.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmento_Contenedor, nuevo).addToBackStack(null).commit();
        }
    }
}
