package com.example.mario.mario_ej1_correo.CONTROLADOR;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.R;
import com.example.mario.mario_ej1_correo.VISTA.Inicio;
import com.example.mario.mario_ej1_correo.VISTA.Login;

import java.util.List;

/**
 * Created by MARIO on 08/10/2017..
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.UsuarioViewHolder> {
    private List<Usuario> datos;
    private OnItemClickListener escuchaClickExterna;

    public Adaptador(List<Usuario> datos, OnItemClickListener escuchaClicksExterna) {
        this.datos = datos;
        this.escuchaClickExterna = escuchaClicksExterna;
    }

    public Adaptador(List<Usuario> datos) {
        this.datos = datos;
    }

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila, parent, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Adaptador.UsuarioViewHolder holder, int position) {
        holder.getImagen().setImageResource(datos.get(position).getFoto());
        holder.getAsunto().setText(datos.get(0).getMiCorreo().get(position).getAsunto());
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    //recupero el nombre del remitente para la relacion con el interface
    public String obtenerIdCorreo(int posicion) {
        if (posicion != RecyclerView.NO_POSITION) {
            return datos.get(posicion).getMiCorreo().get(posicion).getCodigo();
        } else {
            return null;
        }
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView asunto;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            asunto = (TextView) itemView.findViewById(R.id.txtAsunto);
            imagen = (ImageView) itemView.findViewById(R.id.imgFoto);
            itemView.setOnClickListener(this);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public TextView getAsunto() {
            return asunto;
        }

        @Override
        public void onClick(View view) {
            escuchaClickExterna.onClick(this, getAdapterPosition());
        }
    }

    //interface que comunica el recicler con el fragmento lista
    public interface OnItemClickListener {
        void onClick(RecyclerView.ViewHolder viewHolder, int posicion);
    }

}
