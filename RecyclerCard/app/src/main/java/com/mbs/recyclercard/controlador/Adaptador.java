package com.mbs.recyclercard.controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mbs.recyclercard.R;
import com.mbs.recyclercard.modelo.Anime;

import java.util.List;

/**
 * Created by alumnoDAM on 25/10/2017.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.AnimeViewHolder> {
    private List<Anime> datos;
    private ClickRecycler escucha;

    public Adaptador(List<Anime> datos, ClickRecycler escucha) {
        this.datos = datos;
        this.escucha = escucha;
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder holder, int position) {
        //EL HOLDER ES LO QUE TENGO, DE TIPO ANIME
        //datos es una Lista de tipo "Anime"
        //nos cambia la imagen por la otra imagen que se encuentra en datos; y de los datos me coges la imagen de esa posicion
        holder.getImagen().setImageResource(datos.get(position).getImagen());
        holder.getNombre().setText(datos.get(position).getNombre());
        holder.getVisitas().setText(String.valueOf(datos.get(position).getNumVisitas()));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView nombre;
        private TextView visitas;
        private ImageButton compartir;

        public AnimeViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            visitas = (TextView) itemView.findViewById(R.id.visitas);
            compartir = (ImageButton) itemView.findViewById(R.id.compartir);
            compartir.setOnClickListener(this);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getNombre() {
            return nombre;
        }

        public TextView getVisitas() {
            return visitas;
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(getAdapterPosition());
        }
    }
}
