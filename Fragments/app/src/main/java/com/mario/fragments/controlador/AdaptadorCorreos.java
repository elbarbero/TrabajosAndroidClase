package com.mario.fragments.controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mario.fragments.R;
import com.mario.fragments.modelo.Correo;

import java.util.ArrayList;

/**
 * Created by adminmj on 13/12/2017.
 */

public class AdaptadorCorreos extends RecyclerView.Adapter <AdaptadorCorreos.DatosViewHolder> {

    //array en el que tenemos los datos de los correos
    private ArrayList<Correo> datos;
    private OnItemClickListener escuchaClickExterna;

    public AdaptadorCorreos(ArrayList<Correo> items, OnItemClickListener escuchaClicksExterna) {
        datos = items;
        this.escuchaClickExterna=escuchaClicksExterna;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflamos la fila solo la primera vez
        View fila= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent,false);
        //devolvemos la fila inflada
        return new DatosViewHolder(fila);
    }

    @Override
    public void onBindViewHolder(DatosViewHolder holder, int position) {
        //recupero la posicion de la fila sobre la que se ha pulsado
        holder.miItem=datos.get(position);

        //llevo los valores de el array a la fila
        holder.asunto.setText(datos.get(position).getAsunto());
        holder.foto.setImageResource(datos.get(position).getImagenRemitente());
    }

    /**
     * devuelve el numero de elementos que tiene el array de correos
     * @return
     */
    @Override
    public int getItemCount() {
        return datos.size();
    }

    //recupero el nombre del remitente para la relacion con el interface
    public String obtenerIdCorreo(int posicion)
    {
        if (posicion!=RecyclerView.NO_POSITION)
        {
            return datos.get(posicion).getIdCorreo();
        }

        else
        {
            return null;
        }
    }

    //esta clase es la que tendra los datos de escuha del Recycler
    public class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        //datos que tenemos en el layout fila
        private TextView asunto;
        private ImageView foto;
        private Correo miItem;

        public DatosViewHolder(View itemView)
        {//constructor
            super(itemView);
            itemView.setClickable(true);
            foto=(ImageView) itemView.findViewById(R.id.imageView);
            asunto=(TextView) itemView.findViewById(R.id.textAsunto);
            //escucha el clik
            itemView.setOnClickListener(this);
        }

        //necesito acceder a la informacion que tengo en cada fila
        public ImageView getFoto() {
            return foto;
        }

        public TextView getNombre() {
            return asunto;
        }

        @Override
        public void onClick(View view)
        {
            escuchaClickExterna.onClick(this, getAdapterPosition());
        }
    }

    //interface que comunica el recicler con el fragmento lista
    public interface OnItemClickListener
    {
        public void onClick(RecyclerView.ViewHolder viewHolder, int posicion);
    }


}
