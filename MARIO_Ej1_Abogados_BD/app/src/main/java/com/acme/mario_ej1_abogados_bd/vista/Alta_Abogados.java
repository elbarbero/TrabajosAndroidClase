package com.acme.mario_ej1_abogados_bd.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;

public class Alta_Abogados extends AppCompatActivity {

    EditText nombre, numColegiado;
    Button grabar;
    DataBaseManagerAbogados managerAbogados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta__abogados);

        grabar = (Button) findViewById(R.id.btnGrabar);

        nombre = (EditText) findViewById(R.id.txtNombre);
        numColegiado = (EditText) findViewById(R.id.txtNumColegiado);

        grabar.setOnClickListener(escucha2);
    }

    View.OnClickListener escucha2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            grabarAbogado();
        }
    };

    public void borrarCampos() {
        nombre.setText("");
        numColegiado.setText("");
    }

    public void grabarAbogado(){
        managerAbogados=new DataBaseManagerAbogados(this);
        if(managerAbogados.compruebaRegistroporNumColegiado(numColegiado.getText().toString())){
            Toast.makeText(Alta_Abogados.this, "Ese número de colegiado ya existe", Toast.LENGTH_SHORT).show();
        }else{
            long id=managerAbogados.insertarUnAbogadoEnBD(nombre.getText().toString(),numColegiado.getText().toString());
            Toast.makeText(Alta_Abogados.this, "Nuevo abogado insertado con id " + id, Toast.LENGTH_SHORT).show();
            borrarCampos();
        }
    }

}
