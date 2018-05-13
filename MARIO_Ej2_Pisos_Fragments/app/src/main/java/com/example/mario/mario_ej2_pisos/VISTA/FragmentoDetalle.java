package com.example.mario.mario_ej2_pisos.VISTA;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej2_pisos.CONTROLADOR.OperacionesDatos;
import com.example.mario.mario_ej2_pisos.MODELO.Piso;
import com.example.mario.mario_ej2_pisos.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDetalle extends Fragment {

    public FragmentoDetalle() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragmento_detalle, container, false);
        Bundle args = getArguments();
        OperacionesDatos.inicializar(vista);
        if (args != null) {//SI TIENE PISOS...
            int posicion = args.getInt("posicion");
            actualizar(posicion, vista);
        } else {//...SI NO TIENE PISOS
            actualizar(0, vista);//ACTUALIZA LA POSICION POR DEFECTO, QUE ES 0
        }
        return vista;
    }

    private void actualizar(int pos, View v) {
        TextView nombre;
        TextView ubicacion;
        TextView precio;
        TextView garaje;
        TextView trastero;
        TextView habitaciones;
        TextView banos;
        TextView metros;
        TextView anos;
        ImageView foto;

        nombre = v.findViewById(R.id.txtNombre);
        ubicacion = v.findViewById(R.id.txtUbicacion);
        precio = v.findViewById(R.id.txtPrecio);
        garaje = v.findViewById(R.id.txtGaraje);
        trastero = v.findViewById(R.id.txtTrastero);
        habitaciones = v.findViewById(R.id.txtHabitaciones);
        banos = v.findViewById(R.id.txtBanos);
        metros = v.findViewById(R.id.txtTamanno);
        anos = v.findViewById(R.id.txtAnnos);
        foto = v.findViewById(R.id.imgFoto);

        Piso miPiso = OperacionesDatos.datosPiso.get(pos);

        nombre.setText(miPiso.getNombre());
        ubicacion.setText(miPiso.getUbicación());
        precio.setText(miPiso.getPrecio());
        garaje.setText(miPiso.getGaraje());
        trastero.setText(miPiso.getTrastero());
        habitaciones.setText(miPiso.getnHabitaciones());
        banos.setText(miPiso.getnBanos());
        metros.setText(miPiso.getmCuadrados());
        anos.setText(miPiso.getAntiguedad());
        foto.setImageResource(miPiso.getFoto());
    }

    public void cambio(int pos) {
        actualizar(pos, getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }

}
