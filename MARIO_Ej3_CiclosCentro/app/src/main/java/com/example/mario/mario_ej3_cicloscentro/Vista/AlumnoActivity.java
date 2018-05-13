package com.example.mario.mario_ej3_cicloscentro.Vista;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.mario.mario_ej3_cicloscentro.Controlador.Adaptador;
import com.example.mario.mario_ej3_cicloscentro.Controlador.ClickRecycler;
import com.example.mario.mario_ej3_cicloscentro.Modelo.Ciclo;
import com.example.mario.mario_ej3_cicloscentro.R;

import java.util.ArrayList;
import java.util.List;

public class AlumnoActivity extends AppCompatActivity {

    private List<Ciclo> datos;
    private RecyclerView reciclado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        boolean tipo=getIntent().getExtras().getBoolean("xxx");//PARA DIFERENCIAR EL TIPO DE ALUMNO

        inicializar();
        //el "reciclado" es el id del RecyclerView que hemos puesto en el layout de Recursos llamado content_scrolling
        reciclado = (RecyclerView) findViewById(R.id.reciclerAlumno);
        reciclado.setLayoutManager(new LinearLayoutManager(this));
        if(tipo) {
            reciclado.setAdapter(new Adaptador(datos, escucha, getIntent().getExtras().getBoolean("xxx")));
        }else{
            reciclado.setAdapter(new Adaptador(datos, getIntent().getExtras().getBoolean("xxx")));
        }
    }

    private ClickRecycler escucha = new ClickRecycler() {
            @Override
            public void onClick(int posicion) {
                Ciclo c = (misCiclos().get(posicion));
                Intent intent = new Intent(AlumnoActivity.this, DatosCiclo.class);
                intent.putExtra("c", c);

                startActivity(intent);
            }
        };


    private void inicializar() {
        Resources res = AlumnoActivity.this.getResources();
        String[] nombre = res.getStringArray(R.array.cursos);

        datos = new ArrayList<>();
        datos.add(new Ciclo(nombre[0], R.drawable.gmteleco));
        datos.add(new Ciclo(nombre[1], R.drawable.gmelecauto));
        datos.add(new Ciclo(nombre[2], R.drawable.gmmanteelect));
        datos.add(new Ciclo(nombre[3], R.drawable.gsautorobotindustri));
        datos.add(new Ciclo(nombre[4], R.drawable.gsdesaaplicmulti));
        datos.add(new Ciclo(nombre[5], R.drawable.gsadminfinanzas));
    }

    private ArrayList<Ciclo> misCiclos() {
        Resources res = AlumnoActivity.this.getResources();
        String[] nombre = res.getStringArray(R.array.cursos);
        String[] tipo = res.getStringArray(R.array.tipo);
        String duracion = res.getString(R.string.duracion);
        String[] mod1 = res.getStringArray(R.array.modulos1);
        String[] mod2 = res.getStringArray(R.array.modulos2);
        String[] mod3 = res.getStringArray(R.array.modulos3);
        String[] mod4 = res.getStringArray(R.array.modulos4);
        String[] mod5 = res.getStringArray(R.array.modulos5);
        String[] mod6 = res.getStringArray(R.array.modulos6);

        ArrayList<Ciclo> misCiclos = new ArrayList<Ciclo>();

        misCiclos.add(new Ciclo(nombre[0], tipo[0], duracion));
        misCiclos.add(new Ciclo(nombre[1], tipo[0], duracion));
        misCiclos.add(new Ciclo(nombre[2], tipo[0], duracion));
        misCiclos.add(new Ciclo(nombre[3], tipo[1], duracion));
        misCiclos.add(new Ciclo(nombre[4], tipo[1], duracion));
        misCiclos.add(new Ciclo(nombre[5], tipo[1], duracion));

        //INSERTAMOS TODOS LOS MODULOS DE CADA CICLO

        misCiclos.get(0).AnadirModulo(mod1);
        /*misCiclos.get(0).AnadirModulo(mod1[1]);
        misCiclos.get(0).AnadirModulo(mod1[2]);
        misCiclos.get(0).AnadirModulo(mod1[3]);
        misCiclos.get(0).AnadirModulo(mod1[4]);
        misCiclos.get(0).AnadirModulo(mod1[5]);*/

        misCiclos.get(1).AnadirModulo(mod2);
       /* misCiclos.get(1).AnadirModulo(mod2[1]);
        misCiclos.get(1).AnadirModulo(mod2[2]);
        misCiclos.get(1).AnadirModulo(mod2[3]);
        misCiclos.get(1).AnadirModulo(mod2[4]);
        misCiclos.get(1).AnadirModulo(mod2[5]);*/

        misCiclos.get(2).AnadirModulo(mod3);
        /*misCiclos.get(2).AnadirModulo(mod3[1]);
        misCiclos.get(2).AnadirModulo(mod3[2]);
        misCiclos.get(2).AnadirModulo(mod3[3]);
        misCiclos.get(2).AnadirModulo(mod3[4]);*/

        misCiclos.get(3).AnadirModulo(mod4);
       /* misCiclos.get(3).AnadirModulo(mod4[1]);
        misCiclos.get(3).AnadirModulo(mod4[2]);
        misCiclos.get(3).AnadirModulo(mod4[3]);
        misCiclos.get(3).AnadirModulo(mod4[4]);
        misCiclos.get(3).AnadirModulo(mod4[5]);
        misCiclos.get(3).AnadirModulo(mod4[6]);*/

        misCiclos.get(4).AnadirModulo(mod5);
       /* misCiclos.get(4).AnadirModulo(mod5[1]);
        misCiclos.get(4).AnadirModulo(mod5[2]);
        misCiclos.get(4).AnadirModulo(mod5[3]);
        misCiclos.get(4).AnadirModulo(mod5[4]);
        misCiclos.get(4).AnadirModulo(mod5[5]);*/

        misCiclos.get(5).AnadirModulo(mod6);
        /*misCiclos.get(5).AnadirModulo(mod6[1]);
        misCiclos.get(5).AnadirModulo(mod6[2]);
        misCiclos.get(5).AnadirModulo(mod6[3]);
        misCiclos.get(5).AnadirModulo(mod6[4]);
        misCiclos.get(5).AnadirModulo(mod6[5]);
        misCiclos.get(5).AnadirModulo(mod6[6]);*/

        return misCiclos;
    }

}

