package com.mario.fragments.vista;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario.fragments.R;
import com.mario.fragments.controlador.OperacionesDatos;
import com.mario.fragments.modelo.Correo;

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
        ImageView foto;

        remitente = v.findViewById(R.id.textNomU);
        contenido = v.findViewById(R.id.textContenido);
        asunto = v.findViewById(R.id.textAsunto);
        foto = v.findViewById(R.id.imageFoto2);
        Correo correo = OperacionesDatos.usuario.getListaCorreos().get(pos);
        asunto.setText(correo.getAsunto());
        remitente.setText(correo.getNombreR());
        contenido.setText(correo.getContenido());
        foto.setImageResource(correo.getImagenRemitente());
    }

    public void cambio(int pos){
        actualizar(pos,getView());//LE PASAMOS LA VISTA QUE ESTA INFLADA EN EL MOMENTO
    }

}
