package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.BDHelper;
import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerInmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.OperacionesDatos;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoDetalle extends Fragment {

    int posSeleccionada = 0;
    private DataBaseManagerInmueble managerInmueble;
    List<Inmueble> listInmueble;

    public FragmentoDetalle() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_fragmento_detalle, container, false);
        Bundle args = getArguments();
        managerInmueble= new DataBaseManagerInmueble(this.getActivity());

        if (args != null) {//SI TIENE INMUEBLES...
            int posicion = args.getInt("posicion");
            posSeleccionada = posicion;
            actualizar(posicion, vista/*,helper*/);
        } else {//...SI NO TIENE INMUEBLES
            actualizar(0, vista);//ACTUALIZA LA POSICION POR DEFECTO, QUE ES 0
        }
        return vista;
    }

    private void actualizar(int pos, View v) {
        TextView idInmu;
        TextView idZona;
        TextView ubicacion;
        TextView precio;
        TextView tipoPropiedad;
        TextView NumHabitaciones;
        TextView NumConsultas;
        ImageView imagen;

        idInmu = (TextView) v.findViewById(R.id.txtIdInmueble);
        idZona = (TextView) v.findViewById(R.id.txtIdZona);
        ubicacion = (TextView) v.findViewById(R.id.txtUbicacion);
        precio = (TextView) v.findViewById(R.id.txtPrecio);

        tipoPropiedad = (TextView) v.findViewById(R.id.txtTipo);
        NumHabitaciones = (TextView) v.findViewById(R.id.txtNumHabitaciones);
        NumConsultas = (TextView) v.findViewById(R.id.txtNumConsultas);
        imagen = (ImageView) v.findViewById(R.id.imagen);

        if (BusquedaInmuebles.busqueda) {
            listInmueble=BusquedaInmuebles.listInmueble;
        }else{
            listInmueble=managerInmueble.getInmueblesList();
        }

        idInmu.setText(String.valueOf(listInmueble.get(pos).getId()));
        idZona.setText(String.valueOf(listInmueble.get(pos).getId_zona().getId()));
        ubicacion.setText(String.valueOf(listInmueble.get(pos).getUbicacion()));
        precio.setText(String.valueOf(String.valueOf(listInmueble.get(pos).getPrecio())));
        tipoPropiedad.setText(String.valueOf(listInmueble.get(pos).getTipo_propiedad()));
        NumHabitaciones.setText(String.valueOf(listInmueble.get(pos).getNum_habitaciones()));
        NumConsultas.setText(String.valueOf(listInmueble.get(pos).getNum_consultas()));
        imagen.setImageResource(listInmueble.get(pos).getRuta_imagen());
    }

    public void cambio(int pos) {
        actualizar(pos, getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }
}
