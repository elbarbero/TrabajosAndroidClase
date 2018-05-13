package com.example.mario.mario_ej1_correo.VISTA;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.MODELO.Correo;
import com.example.mario.mario_ej1_correo.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;


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
        if (args != null) {//SI TIENE CORREOS...
            int posicion = args.getInt("posicion");
            actualizar(posicion, vista);
        } else {//...SI NO TIENE CORREOS
            actualizar(0, vista);//ACTUALIZA LA POSICION POR DEFECTO, QUE ES 0
        }
        return vista;
    }

    private void actualizar(int pos, View v) {
        TextView asunto;
        TextView contenido;
        TextView remitente;
        TextView fechita;

        Date d = new Date();
        remitente = v.findViewById(R.id.textNomU);
        contenido = v.findViewById(R.id.textContenido);
        asunto = v.findViewById(R.id.textAsunto);
        fechita=v.findViewById(R.id.txtFecha);
        Correo misCorreos = Inicio.miUsuario.get(Login.pos).getMiCorreo().get(pos);
        asunto.setText(misCorreos.getAsunto());
        remitente.setText(Inicio.miUsuario.get(Integer.parseInt(misCorreos.getCodigo())).getNombre());
        contenido.setText(misCorreos.getTexto());
        fechita.setText(VEnvio.fecha);
        //fecha.setText((CharSequence) DateFormat.getTimeInstance().format(d.getTime()));
    }

    public void cambio(int pos){
        actualizar(pos,getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }

}
