package com.acme.mario_ej1_abogados_bd.controlador;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acme.mario_ej1_abogados_bd.R;
import com.acme.mario_ej1_abogados_bd.modelo.Abogado;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.AbogadoViewHolder> {

    private Context mainContext;
    private List<Abogado> items;
    private OnItemClickListener escuchaExterna;
    private boolean esMenu;


    public Adaptador(List<Abogado> items, Context contexto, OnItemClickListener escuchaExterna) {
        this.mainContext = contexto;
        this.items = items;
        this.escuchaExterna = escuchaExterna;
    }

    public Adaptador(List<Abogado> items, Context contexto, boolean esMenu) {
        this.mainContext = contexto;
        this.items = items;
        this.esMenu = esMenu;
    }

    public class AbogadoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Campos respectivos de un item
        protected TextView numcolegiado;


        public AbogadoViewHolder(View itemView) {
            super(itemView);

            numcolegiado = (TextView) itemView.findViewById(R.id.txtNumColegiado);
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
    public AbogadoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fila, viewGroup, false);

        return new AbogadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AbogadoViewHolder holder, int position) {
        Abogado item = items.get(position);
        holder.itemView.setTag(item);
        holder.numcolegiado.setText(item.getNumColegiado());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
