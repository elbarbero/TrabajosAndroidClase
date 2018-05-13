package com.example.mario.mario_ej1_correo.VISTA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mario.mario_ej1_correo.R;

public class VLeer extends AppCompatActivity implements FragmentoLista.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Inicio.miUsuario.get(Login.pos).getMiCorreo().size() > 0) {//si el usuario no tienes correos, que me muestre un mensaje en vez de crasear la app
            setContentView(R.layout.activity_vleer);
        } else {
            Toast.makeText(this.getApplicationContext(), getString(R.string.mensajeNoHayCorreos), Toast.LENGTH_LONG).show();
        }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vleer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mDeconexion:
                Intent intent = null;
                intent = new Intent(VLeer.this, MenuUsuario.class);
                setResult(RESULT_OK);//ESTO ME ENVIA UN -1
                finish();
                break;
            case R.id.mMenu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
