package com.example.mario.mario_ej2_pisos.VISTA;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mario.mario_ej2_pisos.R;

public class MainScrollingActivity extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener {//Implementamos es metodo que escucha el fragmento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrolling);

        if (findViewById(R.id.fragmento_Contenedor) != null && savedInstanceState == null) {
            FragmentoLista lista = new FragmentoLista();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmento_Contenedor, lista).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_scrolling, menu);
        return true;
    }

    private View.OnClickListener escucha = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            lanzarSitio();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                lanzarSitio();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    private void lanzarSitio() {
        new AlertDialog.Builder(this).setTitle(MainScrollingActivity.this.getResources().getString(R.string.informacion1).toUpperCase() + "\n")
                .setMessage(MainScrollingActivity.this.getResources().getString(R.string.informacion2)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion3)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion4)
                        + "\n" + MainScrollingActivity.this.getResources().getString(R.string.informacion5))
                .setNegativeButton("OK", null).show();
    }
}





