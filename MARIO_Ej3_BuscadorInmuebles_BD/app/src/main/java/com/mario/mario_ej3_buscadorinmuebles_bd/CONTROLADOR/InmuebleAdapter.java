package com.mario.mario_ej3_buscadorinmuebles_bd.CONTROLADOR;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mario.mario_ej3_buscadorinmuebles_bd.MODELO.Inmueble;
import com.mario.mario_ej3_buscadorinmuebles_bd.R;

import java.util.List;

public class InmuebleAdapter extends RecyclerView.Adapter<InmuebleAdapter.InmuebleViewHolder> {

    private Context mainContext;
    private List<Inmueble> items;
    private OnItemClickListener escuchaExterna;
    private boolean esMenu;


    public InmuebleAdapter(List<Inmueble> items, Context contexto, OnItemClickListener escuchaExterna) {
        this.mainContext = contexto;
        this.items = items;
        this.escuchaExterna = escuchaExterna;
    }

    public InmuebleAdapter(List<Inmueble> items, Context contexto, boolean esMenu) {
        this.mainContext = contexto;
        this.items = items;
        this.esMenu = esMenu;
    }

    public class InmuebleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        protected TextView ubicacion;
        protected ImageView imagen;


        public InmuebleViewHolder(View itemView) {
            super(itemView);

            ubicacion = (TextView) itemView.findViewById(R.id.txtUbicacionInmueble);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (!esMenu) {
                escuchaExterna.onClick(this, getAdapterPosition());
            }
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
    public InmuebleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fila, viewGroup, false);

        return new InmuebleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InmuebleViewHolder holder, int position) {
        Inmueble item = items.get(position);
        holder.itemView.setTag(item);
        holder.ubicacion.setText(item.getUbicacion());
        holder.imagen.setImageResource(item.getRuta_imagen());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
