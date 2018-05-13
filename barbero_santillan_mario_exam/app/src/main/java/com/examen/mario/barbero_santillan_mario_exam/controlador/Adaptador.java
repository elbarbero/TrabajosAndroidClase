package com.examen.mario.barbero_santillan_mario_exam.controlador;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.examen.mario.barbero_santillan_mario_exam.R;
import com.examen.mario.barbero_santillan_mario_exam.modelo.Usuario;

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

    @Override
    public UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_recetas, parent, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UsuarioViewHolder holder, int position) {
        holder.getImagen().setImageResource(datos.get(position).getMireceta().get(position).getFoto());
        holder.getNombre().setText(datos.get(position).getMireceta().get(position).getNombre());
        holder.getIngrediente().setText(datos.get(position).getMireceta().get(position).getIngrediente());
        holder.getReciones().setText(datos.get(position).getMireceta().get(position).getRaciones());
        holder.getTiempo().setText(datos.get(position).getMireceta().get(position).getTiempo());
    }


    @Override
    public int getItemCount() {
        return datos.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;
        private TextView ingrediente;
        private TextView nombre;
        private TextView tiempo;
        private TextView reciones;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            ingrediente = (TextView) itemView.findViewById(R.id.txtIngrediente);
            nombre = (TextView) itemView.findViewById(R.id.txtNombre);
            tiempo = (TextView) itemView.findViewById(R.id.txtTiempo);
            reciones = (TextView) itemView.findViewById(R.id.txtRaciones);
            imagen = (ImageView) itemView.findViewById(R.id.imgFoto);
            itemView.setOnClickListener(this);
        }

        public ImageView getImagen() {
            return imagen;
        }

        public void setImagen(ImageView imagen) {
            this.imagen = imagen;
        }

        public TextView getIngrediente() {
            return ingrediente;
        }

        public void setIngrediente(TextView ingrediente) {
            this.ingrediente = ingrediente;
        }

        public TextView getNombre() {
            return nombre;
        }

        public void setNombre(TextView nombre) {
            this.nombre = nombre;
        }

        public TextView getTiempo() {
            return tiempo;
        }

        public void setTiempo(TextView tiempo) {
            this.tiempo = tiempo;
        }

        public TextView getReciones() {
            return reciones;
        }

        public void setReciones(TextView reciones) {
            this.reciones = reciones;
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(getAdapterPosition());
        }
    }

}
