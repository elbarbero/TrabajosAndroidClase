package com.example.mario.mario_ej3_cicloscentro.Controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mario.mario_ej3_cicloscentro.Modelo.Modulo;
import com.example.mario.mario_ej3_cicloscentro.R;

import java.util.List;

/**
 * Created by MARIO on 13/11/2017..
 */

public class AdaptadorCiclos extends RecyclerView.Adapter<AdaptadorCiclos.CicloViewHolder> {
    private List<Modulo> datosCiclo;
    private ClickRecycler escucha;

   public AdaptadorCiclos(List<Modulo> datosCiclo) {
        this.datosCiclo = datosCiclo;
        this.escucha = escucha;
    }

    @Override
    public CicloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_ciclos, parent, false);
        return new CicloViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CicloViewHolder holder, int position) {
        holder.getNombre().setText(datosCiclo.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return datosCiclo.size();
    }


    public class CicloViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;

        public CicloViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombreCiclo);
        }

        public TextView getNombre() {
            return nombre;
        }

    }

}
