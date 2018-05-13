package com.mario.mario_ej3_buscadorinmuebles_bd.VISTA;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mario.mario_ej3_buscadorinmuebles_bd.BBDD.DataBaseManagerInmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR.InmuebleAdapter;
import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentoLista extends Fragment implements InmuebleAdapter.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView reciclado;
    private DataBaseManagerInmueble managerInmueble;
    private List<Inmueble> listaItemsInmuebles = new ArrayList<>();
    private RecyclerView.LayoutManager linearManager;
    private InmuebleAdapter adapter;

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
        managerInmueble = new DataBaseManagerInmueble(this.getActivity());
        View v = inflater.inflate(R.layout.fragment_fragmento_lista, container, false);
        inicializarRecicler(v);
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

    public void inicializarRecicler(View v) {

        if (!BusquedaInmuebles.busqueda) {
            listaItemsInmuebles = managerInmueble.getInmueblesList();
        } else {
            listaItemsInmuebles = BusquedaInmuebles.listInmueble;
        }

        // Obtener el Recycler
        reciclado = (RecyclerView) v.findViewById(R.id.reciclador);
        reciclado.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        linearManager = new LinearLayoutManager(this.getActivity());
        reciclado.setLayoutManager(linearManager);

        adapter = new InmuebleAdapter(listaItemsInmuebles, this.getActivity(), this);
        reciclado.setAdapter(adapter);
        reciclado.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onDestroy() {
        managerInmueble.cerrar();
        super.onDestroy();
    }

}
