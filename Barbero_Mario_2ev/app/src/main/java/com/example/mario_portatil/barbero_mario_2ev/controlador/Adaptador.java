package com.example.mario_portatil.barbero_mario_2ev.controlador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario_portatil.barbero_mario_2ev.R;
import com.example.mario_portatil.barbero_mario_2ev.modelo.Alumno;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.AlumnosViewHolder> {

    private Context mainContext;
    private List<Alumno> items;
    private OnItemClickListener escuchaExterna;

    public Adaptador(List<Alumno> items, Context contexto, OnItemClickListener escuchaExterna) {
        this.mainContext = contexto;
        this.items = items;
        this.escuchaExterna = escuchaExterna;
    }

    public Adaptador(List<Alumno> items, Context contexto) {
        this.mainContext = contexto;
        this.items = items;
    }

    public class AlumnosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        protected TextView nombre;

        public AlumnosViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.txtNomAlumno);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
                escuchaExterna.onClick(this, getAdapterPosition());
        }
    }

    //interface que comunica el recicler con el fragmento lista
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder viewHolder, int posicion);
    }

    /**
     * creamos la card o tarjeta
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public AlumnosViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fila, viewGroup, false);

        return new AlumnosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlumnosViewHolder holder, int position) {
        Alumno item = items.get(position);
        holder.itemView.setTag(item);
        holder.nombre.setText(item.getNombre());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
