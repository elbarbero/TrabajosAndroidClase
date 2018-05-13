package com.mbs.mario_ej4_bibliotecas.controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbs.mario_ej4_bibliotecas.R;
import com.mbs.mario_ej4_bibliotecas.modelo.Biblioteca;

import java.util.List;

/**
 * Created by MARIO on 08/10/2017..
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.BibliotecaViewHolder> {
    private List<Biblioteca> datos;
    private ClickRecycler escucha;

    public Adaptador(List<Biblioteca> datos, ClickRecycler escucha) {
        this.datos = datos;
        this.escucha = escucha;
    }

    @Override
    public BibliotecaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new BibliotecaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BibliotecaViewHolder holder, int position) {
        holder.getImagen().setImageResource(datos.get(position).getImagen());
        holder.getNombre().setText(datos.get(position).getNombre());
        holder.getDireccion().setText(String.valueOf(datos.get(position).getDireccion()));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class BibliotecaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView nombre;
        private TextView direccion;
        private ImageButton compartir;

        public BibliotecaViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            itemView.setOnClickListener(this);//con itemView pulsamos sobre cualquier parte de la vista y nos abre lo que le pongamos
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getDireccion() {
            return direccion;
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(getAdapterPosition());
        }
    }

}
