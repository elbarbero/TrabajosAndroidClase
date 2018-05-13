package com.example.mario.mario_ej1_correo.CONTROLADOR;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mario.mario_ej1_correo.MODELO.Usuario;
import com.example.mario.mario_ej1_correo.R;

import java.util.List;

/**
 * Created by MARIO on 08/10/2017..
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.UsuarioViewHolder> {
    private List<Usuario> datos;
    private ClickRecycler escucha;

    public Adaptador(List<Usuario> datos, ClickRecycler escucha) {
        this.datos = datos;
        this.escucha = escucha;
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

    public class UsuarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView asunto;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
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
            escucha.onClick(getAdapterPosition());
        }
    }

}
