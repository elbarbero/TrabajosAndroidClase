package com.acme.mario_ej2_recetario_bd.controlador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.mario_ej2_recetario_bd.R;
import com.acme.mario_ej2_recetario_bd.modelo.Receta;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.RecetaViewHolder> {

    private Context mainContext;
    private List<Receta> items;
    private ClickRecycler escucha;

    public Adaptador(List<Receta> items, ClickRecycler escucha) {
        this.items = items;
        this.escucha = escucha;
    }

    @Override
    public RecetaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new RecetaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecetaViewHolder holder, int position) {
        holder.getImagen().setImageResource(items.get(position).getFoto());
        holder.getNombre().setText(items.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecetaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView nombre;

        public RecetaViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.foto);
            nombre = (TextView) itemView.findViewById(R.id.txtNomReceta);
            itemView.setOnClickListener(this);//con itemView pulsamos sobre cualquier parte de la vista y nos abre lo que le pongamos
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
