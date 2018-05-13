package com.example.mario.mario_ej3_cicloscentro.Controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej3_cicloscentro.Modelo.Ciclo;
import com.example.mario.mario_ej3_cicloscentro.R;

import java.util.List;

/**
 * Created by MARIO on 13/11/2017..
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.CicloViewHolder> {
    private List<Ciclo> datosCiclo;
    private ClickRecycler escucha;
    private boolean tipoUsuario;

   public Adaptador(List<Ciclo> datosCiclo, ClickRecycler escucha, boolean tipoUsuario) {
        this.datosCiclo = datosCiclo;
        this.escucha = escucha;
       this.tipoUsuario=tipoUsuario;
    }

    public Adaptador(List<Ciclo> datosCiclo, boolean tipoUsuario) {
        this.datosCiclo = datosCiclo;
        this.tipoUsuario=tipoUsuario;
    }

    @Override
    public CicloViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new CicloViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CicloViewHolder holder, int position) {
        holder.getImagen().setImageResource(datosCiclo.get(position).getImagen());
        holder.getNombre().setText(datosCiclo.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return datosCiclo.size();
    }


    public class CicloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView imagen;
        private TextView nombre;

        public CicloViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.txtNombreCiclo);
           if(tipoUsuario) {//SI ES ALUMNO
                itemView.setOnClickListener(this);
            }
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(getAdapterPosition());
        }

    }

}
