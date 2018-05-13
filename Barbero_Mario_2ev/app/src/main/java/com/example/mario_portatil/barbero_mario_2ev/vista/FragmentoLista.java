package com.example.mario_portatil.barbero_mario_2ev.vista;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.bbdd.DataBaseManagerAlumnos;
import com.example.mario_portatil.barbero_mario_2ev.controlador.Adaptador;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;

import java.util.ArrayList;
import java.util.List;


public class FragmentoLista extends Fragment implements Adaptador.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    RecyclerView reciclado;
    private DataBaseManagerAlumnos managerAlumnos;
    public static List<Alumno> listaAlumnos = new ArrayList<>();
    private RecyclerView.LayoutManager linearManager;
    private Adaptador adapter;

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
        managerAlumnos = new DataBaseManagerAlumnos(this.getActivity());
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

            listaAlumnos = managerAlumnos.obtenerAlumnos();

        // Obtener el Recycler
        reciclado = (RecyclerView) v.findViewById(R.id.reciclador);
        reciclado.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        linearManager = new LinearLayoutManager(this.getActivity());
        reciclado.setLayoutManager(linearManager);

        adapter = new Adaptador(listaAlumnos, this.getActivity(), this);
        reciclado.setAdapter(adapter);
        reciclado.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onDestroy() {
        managerAlumnos.cerrar();
        super.onDestroy();
    }

}
