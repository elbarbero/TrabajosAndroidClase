package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerNotas;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Notas;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDetalle extends Fragment {

    EditText nota;
    Spinner spinnerExamenes;
    int posicionSpinner=0;
    int idAlumno=0;
    private DataBaseManagerAlumnos managerAlumnos;
    private DataBaseManagerNotas managerNotas;
    List<Notas> listNotas;

    public FragmentoDetalle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragmento_detalle, container, false);
        Bundle args = getArguments();
        spinnerExamenes = (Spinner) vista.findViewById(R.id.spExamen);
        spinnerExamenes.setOnItemSelectedListener(escucha);

        if (args != null) {//SI TIENE INMUEBLES...
            int idPos = args.getInt("id");
            idAlumno=idPos;
            incializarControles(idPos, vista/*,helper*/);
        } else {//...SI NO TIENE INMUEBLES
            idAlumno=1;
            incializarControles(1, vista);//ACTUALIZA LA POSICION POR DEFECTO, QUE ES 0
        }
        return vista;
    }

    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerExamenes.getSelectedItemPosition();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void cargarSpinner() {
        //List<Alumno> misAlumnos = managerAlumnos.obtenerNombresAlumnos();
        listNotas=managerNotas.obtenerNotasAlumno(idAlumno);//aqui guardo el id del examen y la nota
        List<Notas> soloNota = new ArrayList<>();
        for (int i = 0; i < listNotas.size(); i++) {
            soloNota.add(new Notas(listNotas.get(i).getId()));//añado el id
        }
        ArrayAdapter<Notas> adaptador = new ArrayAdapter<Notas>(this.getActivity(), android.R.layout.simple_spinner_item, soloNota);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerExamenes.setAdapter(adaptador);
    }

    public void incializarControles(int pos, View v) {
        managerAlumnos= new DataBaseManagerAlumnos(this.getActivity());
        managerNotas=new DataBaseManagerNotas(this.getActivity());

        nota = (EditText) v.findViewById(R.id.txtNota);

        cargarSpinner();
        if(pos==0){
            nota.setText(String.valueOf(listNotas.get(0).getCalificacion()));
        }else {
            nota.setText(String.valueOf(listNotas.get(posicionSpinner).getCalificacion()));
        }
    }

    public void cambio(int pos) {
        incializarControles(pos, getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }
}
