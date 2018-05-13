package com.acme.mario_ej1_abogados_bd.vista;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerAbogados;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerCasos;
import com.acme.mario_ej1_abogados_bd.bbdd.DataBaseManagerGestiones;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;
import com.acme.mario_ej1_abogados_bd.modelo.Caso;
import com.acme.mario_ej1_abogados_bd.modelo.Gestion;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDetalle extends Fragment {

    private DataBaseManagerAbogados managerAbogados;
    private DataBaseManagerCasos managerCasos;
    private DataBaseManagerGestiones managerGestiones;
    Spinner spinnerCasos, spinnerGestiones;
    int posicionSpinner = 0;
    List<Caso> misCasos;
    List<Abogado> miAbogado;

    public FragmentoDetalle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragmento_detalle, container, false);
        Bundle args = getArguments();
        managerAbogados = new DataBaseManagerAbogados(this.getActivity());
        managerCasos = new DataBaseManagerCasos(this.getActivity());
        managerGestiones = new DataBaseManagerGestiones(this.getActivity());

        spinnerCasos = vista.findViewById(R.id.spCasos);
        spinnerGestiones = vista.findViewById(R.id.spGestiones);

        spinnerCasos.setOnItemSelectedListener(escucha);
        spinnerGestiones.setOnItemSelectedListener(escucha2);


        if (args != null) {
            int idPos = args.getInt("id");
            actualizar(idPos, vista/*,helper*/);
        } else {
            actualizar(0, vista);
        }
        return vista;
    }


    AdapterView.OnItemSelectedListener escucha = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerCasos.getSelectedItemPosition();
            cargarSpinnerGestiones();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener escucha2 = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            posicionSpinner = spinnerGestiones.getSelectedItemPosition();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    public void cargarSpinner(int idPos) {
        miAbogado = new ArrayList<>();
        misCasos = new ArrayList<>();
        if (idPos == 0) {
            miAbogado = managerAbogados.obtenerUnAbogado(idPos + 1);
        } else {
            miAbogado = managerAbogados.obtenerUnAbogado(idPos);
        }

        misCasos = managerCasos.buscarCasoPorAbogado(miAbogado.get(0).getNumColegiado());
        if (misCasos != null) {
            List<Caso> nombres = new ArrayList<>();
            for (int i = 0; i < misCasos.size(); i++) {
                nombres.add(new Caso(misCasos.get(i).getDenominacion()));
            }
            ArrayAdapter<Caso> adaptador = new ArrayAdapter<Caso>(this.getActivity(), android.R.layout.simple_spinner_item, nombres);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
            spinnerCasos.setAdapter(adaptador);
        }
    }

    public void cargarSpinnerGestiones() {

        List<Gestion> miGestion = new ArrayList<>();

        miGestion = managerGestiones.buscarGestionPorCaso(misCasos.get(posicionSpinner).getId());

        ArrayAdapter<Gestion> adaptador2 = new ArrayAdapter<Gestion>(this.getActivity(), android.R.layout.simple_spinner_item, miGestion);
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//le pasamos el layout correspondiente
        spinnerGestiones.setAdapter(adaptador2);
    }

    private void actualizar(int idPos, View v) {
        cargarSpinner(idPos);
        TextView numColegiado, nombre, id;

        nombre = (TextView) v.findViewById(R.id.txtNombre);
        numColegiado = (TextView) v.findViewById(R.id.txtNumColegiado);
        id = (TextView) v.findViewById(R.id.txtIdAbogado);
        id.setText(String.valueOf(miAbogado.get(0).getId()));
        nombre.setText(String.valueOf(miAbogado.get(0).getNombre()));
        numColegiado.setText(String.valueOf(miAbogado.get(0).getNumColegiado()));
    }

    public void cambio(int pos) {
        actualizar(pos, getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }
}
