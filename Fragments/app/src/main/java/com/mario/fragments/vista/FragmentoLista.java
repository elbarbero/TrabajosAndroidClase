package com.mario.fragments.vista;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mario.fragments.R;
import com.mario.fragments.controlador.AdaptadorCorreos;
import com.mario.fragments.controlador.OperacionesDatos;

public class FragmentoLista extends Fragment implements AdaptadorCorreos.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView reciclado;

    public FragmentoLista() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fragmento_lista, container, false);
        reciclado = v.findViewById(R.id.reciclador);
        LinearLayoutManager l = new LinearLayoutManager(getActivity());
        l.setOrientation(LinearLayoutManager.VERTICAL);
        reciclado.setLayoutManager(l);//ASI LE DECIMOS AL RECICLER QUE QUIERO EL DISEÑO DEL LINEAR LAYOUT
        reciclado.setAdapter(new AdaptadorCorreos(OperacionesDatos.usuario.getListaCorreos(), this));
        return v;
    }

    public void onClick(RecyclerView.ViewHolder viewHolder, int pos) {//LE PASAMOS LAS VARIABLES QUE TIENE ESTE MÉTODO EN EL ADAPTADOR DE CORREOS
        if (mListener != null) {
            mListener.onFragmentInteraction(pos);
        }
    }

    //Cuando el activity llama al fragmenet, se ejecuta ese método
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    //Cuando el fragmenet deja de ejecutarse, se ejecuta ese método
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {//escucha fragmento
        // TODO: Update argument type and name
        void onFragmentInteraction(int posicion); //onFragmentInteraction=AL SELECCIONAR UN CORREO

    }
}
