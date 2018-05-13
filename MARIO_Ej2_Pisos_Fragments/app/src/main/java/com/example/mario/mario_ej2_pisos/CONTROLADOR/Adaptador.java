package com.example.mario.mario_ej2_pisos.CONTROLADOR;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej2_pisos.MODELO.Piso;
import com.example.mario.mario_ej2_pisos.R;

import java.util.List;

/**
 * Created by MARIO on 08/10/2017..
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.PisoViewHolder> {
    private List<Piso> datos;
    private OnItemClickListener escucha;

    public Adaptador(List<Piso> datos, OnItemClickListener escucha) {
        this.datos = datos;
        this.escucha = escucha;
    }

    @Override
    public PisoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new PisoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adaptador.PisoViewHolder holder, int position) {
        holder.getImagen().setImageResource(datos.get(position).getFoto());
        holder.getNombre().setText(datos.get(position).getNombre());
        holder.getPrecio().setText(String.valueOf(datos.get(position).getPrecio()));
        holder.getUbicacion().setText(String.valueOf(datos.get(position).getUbicación()));
    }


    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class PisoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView nombre;
        private TextView precio;
        private TextView ubicacion;
        private Button info;

        public PisoViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            precio = (TextView) itemView.findViewById(R.id.txtGaraje);
            ubicacion = (TextView) itemView.findViewById(R.id.uno);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            info = (Button) itemView.findViewById(R.id.btnInfo);
            info.setOnClickListener(this);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getUbicacion() {
            return ubicacion;
        }

        public TextView getPrecio() {
            return precio;
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, getAdapterPosition());
        }
    }

    //interface que comunica el recicler con el fragmento lista
    public interface OnItemClickListener {
        public void onClick(RecyclerView.ViewHolder viewHolder, int posicion);
    }


}
